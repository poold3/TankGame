package Game;
import Tank.*;
import Bullet.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private long startTime;
    private final long TICK_LENGTH_MILLI = 15;
    public static final int GAMEBOARD_WIDTH = 1200;
    public static final int GAMEBOARD_HEIGHT = 800;
    private boolean inGame;

    public Game() {
        this.startTime = 0;
        this.inGame = false;
    }

    public void runGame(ArrayList<ITank> tanks) {
        this.startTime = System.currentTimeMillis();
        this.inGame = true;

        //Clear Bullets
        Bullet.bullets.clear();

        //Create the game board
        JFrame jFrame = new JFrame("TankGame!");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(Game.GAMEBOARD_WIDTH + 25, Game.GAMEBOARD_HEIGHT + 50);
        GamePaint gamePaint = new GamePaint();
        jFrame.add(gamePaint);
        jFrame.setVisible(true);


        //Define what happens during the game tick
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                //Base cases for end of game.
                if (tanks.size() == 1) {
                    System.out.println(tanks.get(0).getTankName() + " won the game! Congrats!");
                    inGame = false;
                    t.cancel();
                }
                else if (tanks.size() == 0) {
                    System.out.println("Looks like a draw!");
                    inGame = false;
                    t.cancel();
                }

                //Update all bullet positions
                Bullet.updatePositions();

                //Update all tank positions and angles
                for (ITank tank: tanks) {
                    tank.autoRunTime(tanks);
                }

                //Perform each tank's personal runTime function
                for (ITank tank: tanks) {
                    tank.runTime(tanks);
                }

                //Redraw tanks/bullets
                gamePaint.paintTick(tanks);
            }
        };

        //Start the game ticks
        t.scheduleAtFixedRate(tt,0,this.TICK_LENGTH_MILLI);
    }

}
