package Tank;

import Bullet.Bullet;
import Game.Angle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

public class DallinTank extends ITank{
    public final String tankName = "Clifford";
    public final Color tankColor = Color.BLUE;

    public DallinTank(double xPosition, double yPosition, Angle startAngle) {
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
        double[] dummyPosition = gameTanks.get(1).getPosition();
        double xChange = this.position[0] - dummyPosition[0];
        double yChange = this.position[1] - dummyPosition[1];
        double theta = Math.toDegrees(Math.atan(yChange/xChange));
        //System.out.println(theta);

        if (yChange < 0 && xChange > 0) {
            theta += 180;
        }
        else if (yChange > 0 && xChange < 0) {
            theta += 360;
        }
        else if (yChange > 0 && xChange > 0) {
            theta += 180;
        }
        this.setNewHeading((int)theta);
        this.fireBullet();

    }

    //Your methods here:

}
