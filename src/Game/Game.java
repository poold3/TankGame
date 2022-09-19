package Game;
import Tank.*;
import java.time.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private long startTime;
    private final long TICK_LENGTH_MILLI = 50;
    private boolean inGame;

    public Game() {
        this.startTime = 0;
        this.inGame = false;
    }

    public void startGame() {
        this.startTime = System.currentTimeMillis();
        this.inGame = true;

        ITank[] tanks = new ITank[]{new DallinTank()};
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                for (ITank tank: tanks) {
                    //System.out.println(tank.getClass());
                    tank.autoRunTime();
                    tank.runTime();
                }
                if (System.currentTimeMillis() - startTime > 20000) {
                    System.out.println("Stop Game");
                    inGame = false;
                    t.cancel();
                }
            }
        };

        t.scheduleAtFixedRate(tt,0,this.TICK_LENGTH_MILLI);
    }

}
