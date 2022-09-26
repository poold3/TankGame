import Game.*;
import Tank.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            //Create Game
            Game myTankGame = new Game();

            //Create tanks array.
            ArrayList<ITank> tanks = new ArrayList<>();
            //Add tanks here! ONLY ONE TANK OF EACH TANK CLASS IS ALLOWED!
            tanks.add(new DummyOne(400.0, 350.0, new Angle(0.0)));
            tanks.add(new DummyTwo(250.0, 600.0, new Angle(180.0)));

            //Run TankGame!
            myTankGame.runGame(tanks);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
