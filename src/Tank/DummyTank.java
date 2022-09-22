package Tank;

import Bullet.Bullet;
import Game.Angle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

public class DummyTank extends ITank{
    public final String tankName = "Dummy";
    public final Color tankColor = Color.RED;

    private boolean test = false;

    public DummyTank(double xPosition, double yPosition, Angle startAngle) {
        this.position = new double[]{xPosition, yPosition};
        this.currentHeading = new Angle(startAngle.getValue());
        this.newHeading = new Angle(startAngle.getValue());
    }

    @Override
    public String getTankName() {
        return this.tankName;
    }

    @Override
    public Color getTankColor() {
        return this.tankColor;
    }

    @Override
    public int hashCode() {
        return this.tankName.hashCode();
    }
    @Override
    public void runTime(ArrayList<ITank> gameTanks) {
        this.moveTank();
        if (test) {
            this.setNewHeading(this.currentHeading.getValue() + 1);
            test = false;
        }
        else {
            test = true;
        }


    }

    //Your methods here:

}

