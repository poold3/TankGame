/*
***************** DO NOT MODIFY THIS FILE! ******************************
This is the angle class. Use the methods in this class to interact with angles. Angle run from 0.0 to 359.99.
 */
package Game;

public class Angle {
    public static final double MAX_TURN_SPEED = 1.0;
    private double value;

    public Angle() {
        this.value = 0.0;
    }

    public Angle(double value) {
        while (value < 0.0 || value >= 360.0) {
            if (value < 0.0) {
                value += 360.0;
            }
            else {
                value -= 360.0;
            }
        }
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        while (value < 0.0 || value >= 360.0) {
            if (value < 0.0) {
                value += 360.0;
            }
            else {
                value -= 360.0;
            }
        }
        this.value = value;
    }

    //Return true if the this.value == a.value
    public boolean compare(Angle a) {
        return this.value == a.getValue();
    }

    //Make this 1 degree closer to Angle a
    public void update(Angle a) {
        double aValue = a.getValue();
        //If difference between angles < MAX_TURN_SPEED, set this.value = aValue
        if (Math.abs(this.value - aValue) < MAX_TURN_SPEED) {
            this.value = aValue;
            if (this.value >= 360.0) {
                this.value -= 360.0;
            }
            else if (this.value < 0.0) {
                this.value += 360.0;
            }
        }
        //Else, increment or decrement angle by 1.0
        else if (this.value > aValue) {
            if (this.value - aValue >= 180.0) {
                this.increment();
            }
            else {
                this.decrement();
            }
        }
        else if (this.value < aValue) {
            if (aValue - this.value > 180.0) {
                this.decrement();
            }
            else {
                this.increment();
            }
        }
    }

    //Rotate tank counter-clockwise by 1 degree
    private void increment() {
        this.value = this.value + MAX_TURN_SPEED;
        if (this.value >= 360.0) {
            this.value -= 360.0;
        }
    }

    //Rotate tank clockwise by 1 degree
    private void decrement() {
        this.value = this.value - MAX_TURN_SPEED;
        if (this.value < 0.0) {
            this.value += 360.0;
        }
    }
}
