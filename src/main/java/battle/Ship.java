package battle;

import java.util.Random;

/**
 * Класс "Корабль"
 * Created by iMacAverage on 20.01.16.
 */
public class Ship {

    /**
     * объект "Раса"
     */
    private final Race race;

    /**
     * объект "Тип корабля"
     */
    private final ShipType shipType;

    /**
     * Создать объект
     * @param race объект "Раса"
     * @param shipType объект "Тип корабля"
     */
    public Ship(Race race, ShipType shipType) {
        this.race = race;
        this.shipType = shipType;
    }

    /**
     * Выстрелить
     * @param ship объект "Корабль"
     * @param round раудн боя
     * @return объект "Выстрел"
     */
    public Shot fire(Ship ship, int round) {
        double probability = 1 - ship.getShipType().getShield() / (this.getShipType().getWeapon() * 4 + 1);
        if (probability < 0)
            probability = 0;
        Random rnd = new Random();
        int winEvents = (int) (1000 * probability);
        int event = rnd.nextInt(1000) + 1;
        return new Shot(round, this, ship, probability, event <= winEvents);
    }

    /**
     * Получить признак наличия орудий
     * @return признак наличия орудий
     */
    public boolean isGun() {
        return this.getShipType().getGun() > 0;
    }

    /**
     * Получить объект "Раса"
     * @return объект "Раса"
     */
    public Race getRace() {
        return this.race;
    }

    /**
     * Проверить объект "Корабль" на враждебность
     * @param ship объект "Корабль"
     * @return true в случае успеха, иначе false
     */
    public boolean isEnemy(Ship ship) {
        return this.getRace().getId() != ship.getRace().getId();
    }

    /**
     * Получить объект "Тип корабля"
     * @return объект "Тип корабля"
     */
    public ShipType getShipType() {
        return this.shipType;
    }

    @Override
    public String toString() {
        return String.format("%s, %s",
                this.getRace().toString(),
                this.getShipType().toString());
    }

}
