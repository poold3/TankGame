package Tank;

import java.lang.Math;

//DO NOT MODIFY THIS FILE!

public abstract class ITank {
    //Required class variables
    protected int health;
    protected boolean moving;
    protected double[] position;
    protected Angle currentAngle;
    protected Angle newAngle;
    public final String color;
    private final double TANK_SPEED = 2.0;

    public ITank() {
        this.health = 10;
        this.moving = false;
        this.position = new double[]{0.0,0.0};
        this.currentAngle = new Angle();
        this.newAngle = new Angle();
        this.color = "black";
    }

    public ITank(double xPosition, double yPosition, Angle startAngle, String color) {
        this.health = 10;
        this.moving = false;
        this.position = new double[]{xPosition, yPosition};
        this.currentAngle = new Angle(startAngle.getValue());
        this.newAngle = new Angle(startAngle.getValue());
        this.color = color;
    }

    public int getHealth() {
        return this.health;
    }

    public void decrementHealth() {
        this.health -= 1;
    }

    public double[] getPosition() {
        return this.position;
    }

    public Angle getCurrentAngle() {
        return this.currentAngle;
    }

    protected void moveTank() {
        this.moving = true;
    }

    protected void stopTank() {
        this.moving = false;
    }

    protected void setNewAngle(int value) {
        if (value >= 360) {
            value -= 360;
        }
        else if (value < 0) {
            value += 360;
        }
        this.newAngle.setValue(value);
    }

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
    //Required method
    public abstract void runTime();

}
