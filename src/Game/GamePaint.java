/*
***************** DO NOT MODIFY THIS FILE! ******************************
This is the GamePaint class. This class handles the visualisation of the TankGame. You do not need anything from this class.
 */
package Game;

import Bullet.Bullet;
import Tank.ITank;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashSet;

public class GamePaint extends JPanel {
    private ArrayList<ITank> gameTanks;
    private HashSet<Bullet> bullets;
    public GamePaint() {
        setBackground(Color.WHITE);
        this.gameTanks = new ArrayList<>();
        this.bullets = new HashSet<>();
    }

    @Override
    public void paintComponent (Graphics g) {
        setBackground(Color.lightGray);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Draw bullets
        for (Bullet bullet: this.bullets) {
            g2d.setColor(Color.BLACK);
            //Get bullet position
            double[] position = bullet.getPosition();
            //Make bullet body
            g2d.fillOval((int)Math.round(position[0] - Bullet.BULLET_RADIUS), (int)Math.round(position[1]), (int)Math.round(Bullet.BULLET_RADIUS * 2), (int)Math.round(Bullet.BULLET_RADIUS * 2));
        }

        //Draw tanks
        for (ITank tank: this.gameTanks) {
            //Set the paint color
            g2d.setColor(tank.getTankColor());

            //Get tank position
            double[] position = tank.getPosition();

            //Make tank body
            double topLeft = position[0] - ITank.TANK_WIDTH/2.0;
            double topRight = position[1] - ITank.TANK_HEIGHT/2.0;
            Rectangle2D body = new Rectangle2D.Double(topLeft, topRight, ITank.TANK_WIDTH, ITank.TANK_HEIGHT);
            //Make tank turret
            Rectangle2D turret = new Rectangle2D.Double(position[0] - 2.0, topRight - 8.0, 4.0, 8.0);

            //Rotate Graphics. Draw Tank. Rotate Back.
            double curAngle = tank.getCurrentHeading().getValue();
            g2d.rotate(Math.toRadians(curAngle - 270.0), position[0], position[1]);
            g2d.fill(body);
            g2d.fill(turret);
            g2d.setColor(Color.BLACK);
            g2d.drawString(String.format("%d", tank.getHealth()), (int)Math.round(position[0]) - 3, (int)Math.round(position[1]));
            g2d.rotate(-1 * Math.toRadians(curAngle + 90.0), position[0], position[1]);

            double curTurretAngle = tank.getCurrentTurretHeading().getValue();
            double curAngleRad = Math.toRadians(curTurretAngle);
            double deltaY = 50.0 * Math.sin(curAngleRad);
            double deltaX = 50.0 * Math.cos(curAngleRad);
            Line2D line = new Line2D.Double(position[0], position[1],position[0]  + deltaX, position[1]  + deltaY);
            g2d.draw(line);
        }
    }

    public void paintTick(ArrayList<ITank> gameTanks, HashSet<Bullet> bullets) {
        this.gameTanks = gameTanks;
        this.bullets = bullets;
        repaint();
    }
}
