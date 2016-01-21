package battle;

import org.junit.Test;

/**
 * Класс "Тестирование класса Корабль"
 * Created by iMacAverage on 20.01.16.
 */
public class ShipTest {

    @Test
    public void testFire() throws Exception {
        Ship ship = new Ship(1, 1, 1);
        Ship shipEnemy = new Ship(1, 1, 3);
        System.out.printf("Результат выстрела: %b\n", ship.fire(shipEnemy));
    }
}