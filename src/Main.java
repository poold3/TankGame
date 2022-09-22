import Game.*;
import Tank.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Welcome to TankGame!");
            Game myTankGame = new Game();

            //Create tanks array.
            ArrayList<ITank> tanks = new ArrayList<>();
            tanks.add(new DallinTank(450.0, 400.0, new Angle(45)));
            tanks.add(new DummyTank(700.0, 500.0, new Angle(145)));
            myTankGame.runGame(tanks);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
