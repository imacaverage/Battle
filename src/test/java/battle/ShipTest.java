package battle;

import org.junit.Test;

/**
 * Класс для тестирования класса "Корабль"
 * Created by iMacAverage on 20.01.16.
 */
public class ShipTest {

    @Test
    public void testFire() throws Exception {
        ShipType shipType1 = new ShipType("meet", 1, 1, 1);
        ShipType shipType2 = new ShipType("shield4", 1, 1, 4);
        Race race1 = new Race(1, "Race1");
        Race race2 = new Race(2, "Race2");
        Ship ship = new Ship(race1, shipType1);
        Ship shipEnemy = new Ship(race2, shipType2);
        Shot shot = ship.fire(shipEnemy, 1);
        System.out.println(shot.toString());
    }
}