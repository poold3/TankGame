package Tank;

//DO NOT MODIFY THIS FILE!

public class Angle {
    private int value;

    public Angle() {
        this.value = 0;
    }

    public Angle(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        if (0 <= value && value <= 359) {
            this.value = value;
        }
    }

    public boolean compare(Angle a) {
        return this.value == a.getValue();
    }

    public void update(Angle a) {
        if (this.value > a.getValue()) {
            if (this.value - a.getValue() >= 180) {
                this.increment();
            }
            else {
                this.decrement();
            }
        }
        else if (this.value < a.getValue()) {
            if (a.getValue() - this.value >= 180) {
                this.decrement();
            }
            else {
                this.increment();
            }
        }
    }

    //Rotate tank counter-clockwise by 1 degree
    public void increment() {
        this.value = this.value + 1 >= 360 ? 0 : this.value + 1;
    }

    //Rotate tank clockwise by 1 degree
    public void decrement() {
        this.value = this.value - 1 < 0 ? 359 : this.value - 1;
    }
}
