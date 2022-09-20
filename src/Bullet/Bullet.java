package Bullet;

import Game.Angle;

public class Bullet {
    public static final double BULLET_SPEED = 2.0;
    public static final double BULLET_RADIUS = 1.0;
    private double[] position;
    private Angle heading;

    public Bullet(int xPosition, int yPosition, int heading) {
        this.position = new double[]{xPosition, yPosition};
        this.heading = new Angle(heading);
    }

    public double[] getPosition() {
        return this.position;
    }

    public Angle getHeading() {
        return this.heading;
    }

}
