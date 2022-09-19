package Game;

import Tank.ITank;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;

public class GameBoard extends JFrame {
    private final String TITLE;
    private final int WIDTH;
    private final int HEIGHT;

    private ITank[] gameTanks;
    public GameBoard(String title, int width, int height, ITank[] gameTanks) {
        super(title);
        this.TITLE = title;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.gameTanks = gameTanks;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setVisible(true);
    }

    public void paint (Graphics g) {
        super.paint(g);
        g.setColor(Color.green);
        for (ITank tank: this.gameTanks) {
            g.fillRect((int)tank.getPosition()[0],(int)tank.getPosition()[1],50,100);
        }
    }

    public void tickPaint(ITank[] gameTanks) {
        this.gameTanks = gameTanks;
        repaint();
    }
}
