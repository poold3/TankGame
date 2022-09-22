/*
*****************DO NOT MODIFY THIS FILE!******************************
This is the parent Tank class. You are not allowed to alter any code on this page. However, you will find the variables
and methods defined here useful when implementing your own tanks.
 */

package Tank;

import java.awt.Color;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashSet;

import Game.*;
import Bullet.Bullet;

public abstract class ITank {
    protected int health = 5;
    protected boolean moving = false;
    private final Angle previousHeading = new Angle(-1);
    public static final int TANK_WIDTH = 25;
    public static final int TANK_HEIGHT = 40;
    public static final double TANK_SPEED = 1.0;
    protected double[] position;
    private double deltaX;
    private double deltaY;
    protected long lastBulletFired = 0;
    protected Angle currentHeading;
    protected Angle newHeading;


    /*
    Returns the health of your tank.
     */
    public int getHealth() {
        return this.health;
    }

    /*
    Decreases your tank's health. This function will be called when your tank is shot.
     */
    private void decrementHealth() {
        this.health -= 1;
    }

    /*
    Returns the current position of your tank in an array of doubles.
    this.position[0] = X Coordinate
    this.position[1] = Y Coordinate
     */
    public double[] getPosition() {
        return this.position;
    }

    /*
    Returns the current angle/heading of your tank.
    The return value is of class type Angle. See Tank/Angle.java.
     */
    public Angle getCurrentHeading() {
        return this.currentHeading;
    }

    /*
    Returns a true/false value regarding whether your tank is currently moving forward.
     */
    public boolean isMoving() {
        return this.moving;
    }

    /*
    Call this method to start moving your tank forward.
     */
    protected void moveTank() {
        this.moving = true;
    }

    /*
    Call this method to stop your tank from moving.
     */
    protected void stopTank() {
        this.moving = false;
    }

    /*
    Use this method to set the desired heading for your tank. With each game tick, your tank will rotate towards
    the new heading. Note that your tank will rotate regardless if it is moving or not.
     */
    protected void setNewHeading(int value) {
        if (value >= 360) {
            value -= 360;
        }
        else if (value < 0) {
            value += 360;
        }
        this.newHeading.setValue(value);
    }

    /*
    Fires a bullet from the tank. 2 second cool down.
     */
    protected void fireBullet() {
        long curTime = System.currentTimeMillis();
        if (curTime - this.lastBulletFired > 2000) {
            int curAngleValue = this.currentHeading.getValue();
            double curAngleRad = Math.toRadians(curAngleValue);
            double startY = ITank.TANK_HEIGHT * Math.sin(curAngleRad);
            double startX = ITank.TANK_HEIGHT * Math.cos(curAngleRad);
            Bullet.bullets.add(new Bullet(this.position[0] + startX, this.position[1] + startY, curAngleValue));
            this.lastBulletFired = curTime;
        }
    }

    /*
    This function is called every game tick. It handles moving, rotating, being shot, dying, etc.
     */
    public void autoRunTime(ArrayList<ITank> gameTanks) {
        //Update currentAngle if not equal with newAngle
        this.currentHeading.update(this.newHeading);

        //Move tank if moving
        if (this.moving) {
            //Turn tank if this.previousAngle != this.currentAngle
            if (!this.previousHeading.compare(this.currentHeading)) {
                int curAngleValue = this.currentHeading.getValue();
                this.previousHeading.setValue(curAngleValue);
                double curAngleRad = Math.toRadians(curAngleValue);
                this.deltaY = TANK_SPEED * Math.sin(curAngleRad);
                this.deltaX = TANK_SPEED * Math.cos(curAngleRad);
            }

            //Do not add if against walls
            if (this.position[0] + this.deltaX > 0 && this.position[0] + this.deltaX < Game.GAMEBOARD_WIDTH) {
                this.position[0] += this.deltaX;
            }
            if (this.position[1] + this.deltaY > 0 && this.position[1] + this.deltaY < Game.GAMEBOARD_HEIGHT) {
                this.position[1] += this.deltaY;
            }
        }

    }

    @Override
    public abstract int hashCode();
    /*
    This method will return the name of your tank as defined in your own tank class.
     */
    public abstract String getTankName();

    /*
    This method will return the color of your tank as defined in your own tank class.
     */
    public abstract Color getTankColor();

    /*
    This function is also called every game tick. You must implement this method in your own tank class.
    Within this function you should drive your tank to avoid bullets and shoot other tanks.
    Parameters:
        1. An array of all the tanks on the field. Use this to access other tank positions and headings.
        2. An array of all bullets on the field. Use this to access bullet positions and headings.
     */
    public abstract void runTime(ArrayList<ITank> gameTanks);

}
