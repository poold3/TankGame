package Game;

import Bullet.Bullet;
import Tank.ITank;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GamePaint extends JPanel {
    private ArrayList<ITank> gameTanks;
    public GamePaint() {
        setBackground(Color.WHITE);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Draw bullets
        for (Bullet bullet: Bullet.bullets) {
            g2d.setColor(Color.BLACK);
            //Get bullet position
            double[] position = bullet.getPosition();
            //Make bullet body
            g2d.fillOval((int)(position[0] - Bullet.BULLET_RADIUS), (int)position[1], (int)(Bullet.BULLET_RADIUS * 2), (int)(Bullet.BULLET_RADIUS * 2));
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
            int curAngle = tank.getCurrentHeading().getValue();
            g2d.rotate(Math.toRadians(curAngle - 270), position[0], position[1]);
            g2d.fill(body);
            g2d.fill(turret);
            g2d.rotate(-1 * Math.toRadians(curAngle - 270), position[0], position[1]);
        }
    }

    public void paintTick(ArrayList<ITank> gameTanks) {
        this.gameTanks = gameTanks;
        repaint();
    }
}
