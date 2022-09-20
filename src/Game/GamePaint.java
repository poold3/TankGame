package Game;

import Tank.ITank;
import Game.Angle;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class GamePaint extends JPanel {
    private final int tankWidth;
    private final int tankHeight;
    private ITank[] gameTanks;
    public GamePaint(int tankWidth, int tankHeight) {
        setBackground(Color.WHITE);
        this.tankWidth = tankWidth;
        this.tankHeight = tankHeight;
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (ITank tank: this.gameTanks) {
            //Set the paint color
            g2d.setColor(tank.getTankColor());

            //Get tank position
            double[] position = tank.getPosition();
            //Make tank body
            double topLeft = position[0] - this.tankWidth/2.0;
            double topRight = position[1] - this.tankHeight/2.0;
            Rectangle2D body = new Rectangle2D.Double(topLeft, topRight, this.tankWidth, this.tankHeight);
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

    public void paintTick(ITank[] gameTanks) {
        this.gameTanks = gameTanks;
        repaint();
    }
}
