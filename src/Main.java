import Game.*;
import Tank.*;

public class Main {

    public static final int GAMEBOARD_WIDTH = 1200;
    public static final int GAMEBOARD_HEIGHT = 800;
    public static void main(String[] args) {
        try {
            System.out.println("Welcome to TankGame!");
            Game myTankGame = new Game(GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT);

            //Create tanks array.
            ITank[] tanks = new ITank[]{new DallinTank(250.0, 250.0, new Angle(300), GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT),
                    new DummyTank(550.0, 250.0, new Angle(0), GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT)};

            myTankGame.runGame(tanks);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
