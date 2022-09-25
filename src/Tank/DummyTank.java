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
        double value = startAngle.getValue();
        this.currentHeading = new Angle(value);
        this.newHeading = new Angle(value);
        this.currentTurretHeading = new Angle(value);
        this.newTurretHeading = new Angle(value);
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
    public void runTime(ArrayList<ITank> gameTanks, HashSet<Bullet> bullets) {
        this.moveTank();
        if (test) {
            this.setNewHeading(this.currentHeading.getValue() + 1);
            test = false;
        }
        else {
            test = true;
        }

        double[] dummyPosition = gameTanks.get(0).getPosition();
        double xChange = this.position[0] - dummyPosition[0];
        double yChange = this.position[1] - dummyPosition[1];
        double theta = Math.toDegrees(Math.atan(yChange/xChange));

        if (yChange < 0 && xChange > 0) {
            theta += 180;
        }
        else if (yChange > 0 && xChange < 0) {
            theta += 360;
        }
        else if (yChange > 0 && xChange > 0) {
            theta += 180;
        }
        this.setNewTurretHeading((int)Math.round(theta));
        this.fireBullet(bullets);


    }

    //Your methods here:

}

