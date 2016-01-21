package battle;

import java.util.ArrayList;

/**
 * Класс "Бой"
 * Created by iMacAverage on 21.01.16.
 */
public class Fight {

    private final ArrayList<Ship> fleet1;

    private final ArrayList<Ship> fleet2;

    /**
     * Создать объект
     * @param fleet1 флот 1
     * @param fleet2 флот 2
     */
    public Fight(ArrayList<Ship> fleet1, ArrayList<Ship> fleet2) {
        this.fleet1 = fleet1;
        this.fleet2 = fleet2;
    }

    public ArrayList<Ship> getFleet1() {
        return fleet1;
    }

    public ArrayList<Ship> getFleet2() {
        return fleet2;
    }
}
