package Game;
import Tank.*;
import java.time.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

public class Game {
    private long startTime;
    private final long TICK_LENGTH_MILLI = 25;
    private final int GAMEBOARD_WIDTH = 1200;
    private final int GAMEBOARD_HEIGHT = 800;
    private boolean inGame;

    public Game() {
        this.startTime = 0;
        this.inGame = false;
    }

    public void runGame(ITank[] tanks) {
        this.startTime = System.currentTimeMillis();
        this.inGame = true;

        ITank[] gameTanks = tanks.clone();

        //Create the game board
        GameBoard gameBoard = new GameBoard("TankGame!", GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT,gameTanks);


        //Define what happens during the game tick
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                //Base cases for winning and ties
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
                    tank.autoRunTime();
                }

                //Perform each tanks runTime function
                for (ITank tank: gameTanks) {
                    tank.runTime();
                }

                //Redraw tanks/bullets
                gameBoard.tickPaint(gameTanks);

                //Time out
                if (System.currentTimeMillis() - startTime > 60000) {
                    System.out.println("Stop Game");
                    inGame = false;
                    t.cancel();
                }
            }
        };

        //Start the game ticks
        t.scheduleAtFixedRate(tt,0,this.TICK_LENGTH_MILLI);
    }

}
