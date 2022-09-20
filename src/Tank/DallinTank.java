package Tank;

import Bullet.Bullet;
import Game.Angle;

import java.awt.Color;
import java.util.HashSet;

public class DallinTank extends ITank{
    public final String tankName = "Clifford";
    public final Color tankColor = Color.BLUE;

    public DallinTank(double xPosition, double yPosition, Angle startAngle, int gameboardWidth, int gameboardHeight) {
        this.position = new double[]{xPosition, yPosition};
        this.currentHeading = new Angle(startAngle.getValue());
        this.newHeading = new Angle(startAngle.getValue());
        this.GAMEBOARD_WIDTH = gameboardWidth;
        this.GAMEBOARD_HEIGHT = gameboardHeight;
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
    public void runTime(ITank[] gameTanks, HashSet<Bullet> bullets) {
        this.moveTank();

        if (this.position[1] < 2) {
            this.setNewHeading(225);
        }
        if (this.position[0] < 2) {
            this.setNewHeading(145);
        }

    }

    //Your methods here:

}
