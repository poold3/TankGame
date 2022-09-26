package Tank;

import Bullet.Bullet;
import Game.Angle;
import Game.Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

public class DummyTwo extends ITank{
    //YOU MUST GIVE THIS TANK A UNIQUE NAME!!
    public final String tankName = "DummyTwo";

    //YOU MUST GIVE THIS TANK A UNIQUE COLOR!!
    public final Color tankColor = Color.BLUE;

    private boolean start = true;

    //THIS CONSTRUCTOR IS REQUIRED!
    public DummyTwo(double xPosition, double yPosition, Angle startAngle) {
        //REQUIRED!
        this.position = new double[]{xPosition, yPosition};
        double value = startAngle.getValue();
        this.currentHeading = new Angle(value);
        this.newHeading = new Angle(value);
        this.currentTurretHeading = new Angle(value);
        this.newTurretHeading = new Angle(value);

        //ADD CODE HERE:
    }

    //REQUIRED!
    @Override
    public String getTankName() {
        return this.tankName;
    }

    //REQUIRED!
    @Override
    public Color getTankColor() {
        return this.tankColor;
    }

    //REQUIRED!
    @Override
    public int hashCode() {
        return this.tankName.hashCode();
    }

    //IMPLEMENT YOUR runTime METHOD HERE!
    @Override
    public void runTime(ArrayList<ITank> tanks, HashSet<Bullet> bullets) {
        //Logic for start

        if (start) {
            this.moveTank();
            this.setNewHeading(180);
            this.start = false;
        }

        //Logic for driving

        if (this.position[0] < 150 && this.position[1] > (Game.GAMEBOARD_HEIGHT - 150)) {
            this.setNewHeading(270);
            if (this.currentHeading.getValue() == 180) {
                this.setNewHeading(270);
                this.stopTank();
            }
            else if (this.currentHeading.getValue() == 270) {
                this.moveTank();
            }
        }
        else if (this.position[0] < 150 && this.position[1] < 150) {
            this.setNewHeading(0);
        }
        else if (this.position[0] > (Game.GAMEBOARD_WIDTH - 150) && this.position[1] < 150) {
            this.setNewHeading(90);
            if (this.currentHeading.getValue() == 0) {
                this.setNewHeading(90);
                this.stopTank();
            }
            else if (this.currentHeading.getValue() == 90) {
                this.moveTank();
            }
        }
        else if (this.position[0] > (Game.GAMEBOARD_WIDTH - 150) && this.position[1] > (Game.GAMEBOARD_HEIGHT - 150)) {
            this.setNewHeading(180);
        }

        //Logic for firing bullets
        for (ITank tank: tanks) {
            if (tank != this) {
                double[] targetPosition = tank.getPosition();
                double xChange = this.position[0] - targetPosition[0];
                double yChange = this.position[1] - targetPosition[1];
                double theta = Math.toDegrees(Math.atan(yChange/xChange));

                if (yChange < 0.0 && xChange > 0.0) {
                    theta += 180.0;
                }
                else if (yChange > 0.0 && xChange < 0.0) {
                    theta += 360.0;
                }
                else if (yChange > 0.0 && xChange > 0.0) {
                    theta += 180.0;
                }
                this.setNewTurretHeading(theta);

                this.fireBullet(bullets);
                break;
            }
        }

    }

    //YOUR METHODS HERE:

}
