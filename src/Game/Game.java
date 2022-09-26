/*
***************** *DO NOT MODIFY THIS FILE! ******************************
This is the Game class. This class handles the running of the TankGame.
*YOU MAY ONLY MODIFY TICK_LENGTH_MILLI, GAMEBOARD_WIDTH, AND GAMEBOARD_HEIGHT
 */
package Game;

import Tank.*;
import Bullet.*;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class Game {
    private long startTime;
    private final long TICK_LENGTH_MILLI = 15;
    public static final int GAMEBOARD_WIDTH = 800;
    public static final int GAMEBOARD_HEIGHT = 600;
    private boolean inGame;

    public Game() {
        System.out.println("Welcome to TankGame!");
        this.startTime = 0;
        this.inGame = false;
    }

    public void runGame(ArrayList<ITank> tanks) {
        System.out.println("Running TankGame!");
        this.startTime = System.currentTimeMillis();
        this.inGame = true;

        //Make bullets
        HashSet<Bullet> bullets = new HashSet<>();

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
                Bullet.updatePositions(bullets);

                //Update all tank positions and angles
                for (Iterator<ITank> i = tanks.iterator(); i.hasNext();) {
                    ITank tank = i.next();
                    tank.autoRunTime(bullets);
                    //Check tank health. Destroy tank if necessary
                    if (tank.getHealth() <= 0) {
                        System.out.println(tank.getTankName() + " has been destroyed!");
                        i.remove();
                    }
                }

                //Perform each tank's personal runTime function
                for (ITank tank: tanks) {
                    tank.runTime(tanks, bullets);
                }

                //Redraw tanks/bullets
                gamePaint.paintTick(tanks, bullets);
            }
        };

        //Start the game ticks
        t.scheduleAtFixedRate(tt,0,this.TICK_LENGTH_MILLI);
    }

}
