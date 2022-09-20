package Tank;

import Bullet.Bullet;
import Game.Angle;

import java.awt.Color;
import java.util.HashSet;

public class DummyTank extends ITank{
    public final String tankName = "Dummy";
    public final Color tankColor = Color.RED;

    public DummyTank(double xPosition, double yPosition, Angle startAngle, int gameboardWidth, int gameboardHeight) {
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
        this.setNewHeading(225);

    }

    //Your methods here:

}

