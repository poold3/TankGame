package Bullet;

import Game.*;

import java.util.HashSet;
import java.util.Iterator;

public class Bullet {
    public static int id = 0;
    public static final double BULLET_SPEED = 5.0;
    public static final double BULLET_RADIUS = 2.0;
    public static final HashSet<Bullet> bullets = new HashSet<>();
    private final double[] position;
    private final Angle heading;
    private final double deltaY;
    private final double deltaX;

    public int bulletId;

    public Bullet(double xPosition, double yPosition, int heading) {
        this.position = new double[]{xPosition, yPosition};
        this.heading = new Angle(heading);
        this.bulletId = ++id;
        double curAngleRad = Math.toRadians(heading);
        this.deltaY = Bullet.BULLET_SPEED * Math.sin(curAngleRad);
        this.deltaX = Bullet.BULLET_SPEED * Math.cos(curAngleRad);
    }

    public static void updatePositions() {
        for (Iterator<Bullet> i = Bullet.bullets.iterator(); i.hasNext();) {
            Bullet bullet = i.next();
            //Do not add if against walls
            if (bullet.position[0] + bullet.deltaX > 0 && bullet.position[0] + bullet.deltaX < Game.GAMEBOARD_WIDTH &&
                    bullet.position[1] + bullet.deltaY > 0 && bullet.position[1] + bullet.deltaY < Game.GAMEBOARD_HEIGHT) {
                bullet.position[0] += bullet.deltaX;
                bullet.position[1] += bullet.deltaY;
            }
            else {
                i.remove();
            }
        }
    }

    public double[] getPosition() {
        return this.position;
    }

    public Angle getHeading() {
        return this.heading;
    }

    public int getBulletId() {
        return this.bulletId;
    }
    @Override
    public int hashCode() {
        return bulletId;
    }

}
