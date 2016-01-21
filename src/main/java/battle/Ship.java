package battle;

import java.util.Random;

/**
 * Класс "Корабль"
 * Created by iMacAverage on 20.01.16.
 */
public class Ship {

    /**
     * количество орудий
     */
    private final int gun;

    /**
     * мощность орудия
     */
    private final int weapon;

    /**
     * мощность защиты
     */
    private final int shield;

    /**
     * Создать объект
     * @param gun количество орудий
     * @param weapon мощность орудия
     * @param shield мощность защиты
     */
    public Ship(int gun, int weapon, int shield) {
        this.gun = gun;
        this.weapon = weapon;
        this.shield = shield;
    }

    /**
     * Выстрелить
     * @param ship объект "Корабль"
     * @return результат выстрела
     */
    public boolean fire(Ship ship) {
        Random rnd = new Random(System.currentTimeMillis());
        int winEvents = 100 - ship.getShield() * 100 / (this.weapon * 4 + 1);
        int event = rnd.nextInt(100) + 1;
        return event <= winEvents;
    }

    /**
     * Получить количество орудий
     * @return количество орудий
     */
    public int getGun() {
        return gun;
    }

    /**
     * Получить мощность орудия
     * @return мощность орудия
     */
    public int getWeapon() {
        return weapon;
    }

    /**
     * Получить мощность защиты
     * @return мощность защиты
     */
    public int getShield() {
        return shield;
    }

}
