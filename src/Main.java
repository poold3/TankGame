import Game.*;
import Tank.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Welcome to TankGame!");
            Game myTankGame = new Game();

            //Create tanks array.
            ITank[] tanks = new ITank[]{new DallinTank(850.0, 550.0, new Angle(90)),
                    new DallinTank(250.0, 250.0, new Angle(90))};

            myTankGame.runGame(tanks);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
