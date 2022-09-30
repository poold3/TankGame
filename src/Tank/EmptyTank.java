package Tank;

import Bullet.Bullet;
import Game.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

public class EmptyTank extends ITank{
    //YOU MUST GIVE THIS TANK A UNIQUE NAME!!
    public final String tankName = "YOUR NAME HERE";

    //YOU MUST GIVE THIS TANK A UNIQUE COLOR!!
    public final Color tankColor = Color.pink;

    //THIS CONSTRUCTOR IS REQUIRED!
    public EmptyTank(double xPosition, double yPosition, Angle startAngle) {
        //REQUIRED!
        this.position = new double[]{xPosition, yPosition};
        double value = startAngle.getValue();
        this.currentHeading = new Angle(value);
        this.newHeading = new Angle(value);
        this.previousHeading = new Angle(value + 1);
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
        //YOUR CODE HERE:

    }

    //YOUR METHODS HERE:

}

