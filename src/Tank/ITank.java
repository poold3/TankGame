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
import java.util.Iterator;

import Game.*;
import Bullet.Bullet;

public abstract class ITank {
    public enum driveDirection {
        Forward,
        Backward,
        None
    }

    public static final int TANK_WIDTH = 25;
    public static final int TANK_HEIGHT = 40;
    public static final double TANK_SPEED = 2.0;
    public static final int TURRET_COOLDOWN_MILLI = 2000;
    protected int health = 5;
    protected driveDirection moving = driveDirection.None;
    private final Angle previousHeading = new Angle(-1);
    protected double[] position;
    private double deltaX;
    private double deltaY;
    protected long lastBulletFired = 0;
    protected Angle currentHeading;
    protected Angle newHeading;
    protected Angle currentTurretHeading;
    protected Angle newTurretHeading;


    /*
    Returns the health of the tank.
     */
    public int getHealth() {
        return this.health;
    }

    /*
    Decreases the tank's health. This function will be called when the tank is shot.
     */
    private void decrementHealth() {
        this.health -= 1;
    }

    /*
    Returns the current position of the tank in an array of doubles.
    this.position[0] = X Coordinate
    this.position[1] = Y Coordinate
     */
    public double[] getPosition() {
        return this.position.clone();
    }

    /*
    Returns the current angle/heading of the tank.
    The return value is of class type Angle. See Game.Angle.java.
     */
    public double getCurrentHeading() {
        return this.currentHeading.getValue();
    }

    /*
    Returns the current angle/heading of the turret.
    The return value is of class type Angle. See Game.Angle.java.
     */
    public double getCurrentTurretHeading() {
        return this.currentTurretHeading.getValue();
    }

    /*
    Returns a true/false value regarding whether the tank is currently moving forward.
     */
    public driveDirection howMoving() {
        return this.moving;
    }

    /*
    Call this method to start moving the tank forward.
     */
    protected void moveForward() {
        this.moving = driveDirection.Forward;
    }

    /*
    Call this method to start moving the tank forward.
     */
    protected void moveBackward() {
        this.moving = driveDirection.Backward;
    }

    /*
    Call this method to stop the tank from moving.
     */
    protected void stopTank() {
        this.moving = driveDirection.None;
    }

    /*
    Use this method to set the desired heading for the tank. With each game tick, the tank will rotate towards
    the new heading. Note that the tank will rotate regardless if it is moving or not.
     */
    protected void setNewHeading(double value) {
        if (value >= 360.0) {
            value -= 360.0;
        }
        else if (value < 0.0) {
            value += 360.0;
        }
        this.newHeading.setValue(value);
    }

    /*
    Use this method to set the desired heading for the turret. With each game tick, the turret will rotate towards
    the new heading. Note that the turret will rotate regardless if the tank is moving or not.
     */
    protected void setNewTurretHeading(double value) {
        if (value >= 360.0) {
            value -= 360.0;
        }
        else if (value < 0.0) {
            value += 360.0;
        }
        this.newTurretHeading.setValue(value);
    }

    /*
    Fires a bullet from the tank. 2 second cool down.
     */
    protected void fireBullet(HashSet<Bullet> bullets) {
        long curTime = System.currentTimeMillis();
        if (curTime - this.lastBulletFired > TURRET_COOLDOWN_MILLI) {
            double curAngleValue = this.currentTurretHeading.getValue();
            double curAngleRad = Math.toRadians(curAngleValue);
            double startY = ITank.TANK_HEIGHT * Math.sin(curAngleRad);
            double startX = ITank.TANK_HEIGHT * Math.cos(curAngleRad);
            bullets.add(new Bullet(this.position[0] + startX, this.position[1] + startY, curAngleValue));
            this.lastBulletFired = curTime;
        }
    }

    /*
    This method is called every game tick. YOU CANNOT CALL THIS METHOD!
     It handles moving, rotating and being shot for the tank.
     */
    public void autoRunTime(HashSet<Bullet> bullets) {
        double curAngleValue = this.currentHeading.getValue();
        double curAngleRad = Math.toRadians(curAngleValue);

        //Check if any bullets are hitting the tank
        for (Iterator<Bullet> i = bullets.iterator(); i.hasNext();) {
            Bullet bullet = i.next();
            double[] bulletPosition = bullet.getPosition();

            //Is this bullet in the near vicinity of this tank?
            if (Math.abs(bulletPosition[0] - this.position[0]) < ITank.TANK_HEIGHT &&
                    Math.abs(bulletPosition[1] - this.position[1]) < ITank.TANK_HEIGHT) {

                //Calculate new bullet position after rotation around center of tank by currentHeading
                double bulletRotationRad = Math.toRadians(-1 * (90 - curAngleValue));
                double xRot = (Math.cos(bulletRotationRad) * (bulletPosition[0] - this.position[0])) - (Math.sin(bulletRotationRad)
                        * (this.position[1] - bulletPosition[1])) + this.position[0];
                double yRot = (Math.sin(bulletRotationRad) * (bulletPosition[0] - this.position[0])) + (Math.cos(bulletRotationRad)
                        * (this.position[1] - bulletPosition[1])) + this.position[1];
                yRot = (this.position[1] - yRot) + this.position[1];

                if ((this.position[0] - ITank.TANK_WIDTH/2.0) < xRot && xRot < (this.position[0] + ITank.TANK_WIDTH/2.0)
                        && (this.position[1] - ITank.TANK_HEIGHT/2.0) < yRot && yRot < (this.position[1] + ITank.TANK_HEIGHT/2.0)) {
                    this.decrementHealth();
                    i.remove();
                    System.out.println(this.getTankName() + " has been hit!");
                }
            }
        }

        //Update currentAngle if not equal with newAngle
        this.currentHeading.update(this.newHeading);
        this.currentTurretHeading.update(this.newTurretHeading);

        //Move tank if moving
        if (this.moving != driveDirection.None) {
            //Turn tank if this.previousAngle != this.currentAngle
            if (!this.previousHeading.compare(this.currentHeading)) {
                this.previousHeading.setValue(curAngleValue);
                this.deltaY = TANK_SPEED * Math.sin(curAngleRad);
                this.deltaX = TANK_SPEED * Math.cos(curAngleRad);
            }

            //Do not add if against walls
            if (this.moving == driveDirection.Forward) {
                if (this.position[0] + this.deltaX > 0 && this.position[0] + this.deltaX < Game.GAMEBOARD_WIDTH) {
                    this.position[0] += this.deltaX;
                }
                if (this.position[1] + this.deltaY > 0 && this.position[1] + this.deltaY < Game.GAMEBOARD_HEIGHT) {
                    this.position[1] += this.deltaY;
                }
            }
            else if (this.moving == driveDirection.Backward) {
                if (this.position[0] - this.deltaX > 0 && this.position[0] - this.deltaX < Game.GAMEBOARD_WIDTH) {
                    this.position[0] -= this.deltaX;
                }
                if (this.position[1] - this.deltaY > 0 && this.position[1] - this.deltaY < Game.GAMEBOARD_HEIGHT) {
                    this.position[1] -= this.deltaY;
                }
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
        2. A set of all bullets on the field. Use this to access bullet positions and headings.
     */
    public abstract void runTime(ArrayList<ITank> gameTanks, HashSet<Bullet> bullets);

}
