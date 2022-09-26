package Tank;

import Bullet.Bullet;
import Game.Angle;
import Game.Game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

public class DummyOne extends ITank{
    public final String tankName = "DummyOne";
    public final Color tankColor = Color.RED;

    public DummyOne(double xPosition, double yPosition, Angle startAngle) {
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
    public void runTime(ArrayList<ITank> tanks, HashSet<Bullet> bullets) {
        this.moveForward();
        if (this.position[0] > (Game.GAMEBOARD_WIDTH - 150)) {
            this.setNewHeading(180);
        }
        else if (this.position[0] < 150) {
            this.setNewHeading(0);
        }

        for (ITank tank: tanks) {
            if (tank != this) {
                double[] targetPosition = tank.getPosition();
                double xChange = this.position[0] - targetPosition[0];
                double yChange = this.position[1] - targetPosition[1];
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
                this.setNewTurretHeading(theta);
                this.fireBullet(bullets);
                break;
            }
        }
    }
}

