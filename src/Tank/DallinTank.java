package Tank;

import Bullet.Bullet;
import Game.Angle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

public class DallinTank extends ITank{
    public final String tankName = "Clifford";
    public final Color tankColor = Color.BLUE;

    private boolean start = true;

    public DallinTank(double xPosition, double yPosition, Angle startAngle) {
        this.position = new double[]{xPosition, yPosition};
        int value = startAngle.getValue();
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
    public void runTime(ArrayList<ITank> gameTanks) {
        //Logic for start

        if (start) {
            this.moveTank();
            this.setNewHeading(180);
            this.start = false;
        }

        //Logic for driving
        if (this.position[0] < 150 && this.position[1] > 650) {
                this.setNewHeading(270);
        }
        else if (this.position[0] < 150 && this.position[1] < 150) {
            this.setNewHeading(0);
        }
        else if (this.position[0] > 1050 && this.position[1] < 150) {
            this.setNewHeading(90);
        }
        else if (this.position[0] > 1050 && this.position[1] > 650) {
            this.setNewHeading(180);
        }

        //Logic for firing bullets
        double[] dummyPosition = gameTanks.get(1).getPosition();
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
        this.fireBullet();
    }

    //Your methods here:

}
