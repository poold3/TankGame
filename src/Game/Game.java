package Game;
import Tank.*;
import Bullet.*;

import javax.swing.*;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private long startTime;
    private final int TANK_WIDTH = 25;
    private final int TANK_HEIGHT = 40;
    private final long TICK_LENGTH_MILLI = 10;
    private final int GAMEBOARD_WIDTH;
    private final int GAMEBOARD_HEIGHT;
    private boolean inGame;

    public Game(int width, int height) {
        this.startTime = 0;
        this.inGame = false;
        this.GAMEBOARD_WIDTH = width;
        this.GAMEBOARD_HEIGHT = height;
    }

    public void runGame(ITank[] tanks) {
        this.startTime = System.currentTimeMillis();
        this.inGame = true;

        ITank[] gameTanks = tanks.clone();
        HashSet<Bullet> gameBullets = new HashSet<>();

        //Create the game board
        JFrame jFrame = new JFrame("TankGame!");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(this.GAMEBOARD_WIDTH + 25, this.GAMEBOARD_HEIGHT + 50);
        GamePaint gamePaint = new GamePaint(this.TANK_WIDTH, this.TANK_HEIGHT);
        jFrame.add(gamePaint);
        jFrame.setVisible(true);


        //Define what happens during the game tick
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                //Base cases for end of game.
                if (gameTanks.length == 1) {
                    System.out.println(gameTanks[0].getTankName() + " won the game! Congrats!");
                    inGame = false;
                    t.cancel();
                }
                else if (gameTanks.length == 0) {
                    System.out.println("Looks like a draw!");
                    inGame = false;
                    t.cancel();
                }

                //Update all tank positions and angles
                for (ITank tank: gameTanks) {
                    tank.autoRunTime(gameTanks, gameBullets);
                }

                //Perform each tank's personal runTime function
                for (ITank tank: gameTanks) {
                    tank.runTime(gameTanks, gameBullets);
                }

                //Redraw tanks/bullets
                gamePaint.paintTick(gameTanks);
            }
        };

        //Start the game ticks
        t.scheduleAtFixedRate(tt,0,this.TICK_LENGTH_MILLI);
    }

}
