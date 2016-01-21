package battle;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Класс "Флот"
 * Created by iMacAverage on 21.01.16.
 */
public class Fleet {

    /**
     * список объектов "Корабль"
     */
    private final ArrayList<Ship> ships;

    /**
     * Создать объект
     * @param ships список объектов "Корабль"
     */
    public Fleet(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    /**
     * Проверить, что флот не пустой
     * @return true в случае успеха, иначе false
     */
    public boolean notEmpty() {
        return this.ships.size() != 0;
    }

    /**
     * Получить поток кораблей
     * @return поток кораблей
     */
    public Stream<Ship> getShips() {
        return this.ships.stream();
    }

    /**
     * Удалять корабль из флота
     * @param ship объект "Корабль"
     */
    public void remove(Ship ship) {
        this.ships.remove(ship);
    }

}
