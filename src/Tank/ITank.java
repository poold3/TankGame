/*
*****************DO NOT MODIFY THIS FILE!******************************
This is the parent Tank class. You are not allowed to alter any code on this page. However, you will find the variables
and methods defined here useful when implementing your own tanks.
 */

package Tank;

import java.lang.Math;

public abstract class ITank {
    protected int health;
    protected boolean moving;
    protected double[] position;
    protected Angle currentAngle;
    protected Angle newAngle;
    private final double TANK_SPEED = 2.0;

    /*
    Default constructor for your tank.
     */
    public ITank() {
        this.health = 10;
        this.moving = false;
        this.position = new double[]{0.0,0.0};
        this.currentAngle = new Angle();
        this.newAngle = new Angle();
    }

    /*
    Secondary constructor with parameters.
     */
    public ITank(double xPosition, double yPosition, Angle startAngle) {
        this.health = 5;
        this.moving = false;
        this.position = new double[]{xPosition, yPosition};
        this.currentAngle = new Angle(startAngle.getValue());
        this.newAngle = new Angle(startAngle.getValue());
    }

    /*
    Returns the health of your tank.
     */
    public int getHealth() {
        return this.health;
    }

    /*
    Decreases your tank's health. This function will be called when your tank is shot.
     */
    public void decrementHealth() {
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
    public Angle getCurrentAngle() {
        return this.currentAngle;
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
    protected void setNewAngle(int value) {
        if (value >= 360) {
            value -= 360;
        }
        else if (value < 0) {
            value += 360;
        }
        this.newAngle.setValue(value);
    }

    /*
    This function is called every game tick. It handles moving, rotating, being shot, dying, etc.
     */
    public void autoRunTime() {
        //Update newAngle if not equal with currentAngle
        this.currentAngle.update(this.newAngle);

        //Move tank if moving
        if (this.moving) {
            double curAngleValue = this.currentAngle.getValue();
            boolean reflectAcrossOrigin = false;
            if (curAngleValue > 180.0) {
                curAngleValue -= 180.0;
                reflectAcrossOrigin = true;
            }
            curAngleValue = curAngleValue * Math.PI / 180.0;

            double deltaY = this.TANK_SPEED * Math.sin(curAngleValue);
            double deltaX = this.TANK_SPEED * Math.cos(curAngleValue);

            if (reflectAcrossOrigin) {
                deltaY *= -1.0;
                deltaX *= -1.0;
            }

            this.position[0] += deltaX;
            this.position[1] += deltaY;
        }

    }

    /*
    This method will return the name of your tank as defined in your own tank class.
     */
    public abstract String getTankName();

    /*
    This method will return the color of your tank as defined in your own tank class.
     */
    public abstract String getTankColor();

    /*
    This function is also called every game tick. You must implement this method in your own tank class.
    Within this function you should drive your tank to avoid bullets and shoot other tanks.
    Parameters:
        1. An array of all the tanks on the field. Use this to access other tank positions and headings.
        2. An array of all bullets on the field. Use this to access bullet positions and headings.
     */
    public abstract void runTime();

}
