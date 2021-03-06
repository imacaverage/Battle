package battle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Класс "Бой"
 * Created by iMacAverage on 21.01.16.
 */
public class Fight {

    /**
     * список объектов "Флот"
     */
    private final ArrayList<Fleet> fleets;

    /**
     * Создать объект
     * @param fleets коллекция объектов "Флот"
     */
    public Fight(ArrayList<Fleet> fleets) {
        this.fleets = fleets;
    }

    /**
     * Выполнить
     */
    public ArrayList<Shot> execute() {

        long num = 0;
        Random rnd = new Random();
        ArrayList<Shot> shots = new ArrayList<>();

        // выполняю не более заданного числа итераций пока есть более одного непустого флота
        for(int i = 0; this.fleets.stream().filter(Fleet::notEmpty).count() > 1; i++) {

            // формирую список кораблей с орудиями
            List<Ship> shipsGun = this.fleets.stream()
                    .flatMap(Fleet::getShips)
                    .filter(Ship::isGun)
                    .collect(Collectors.toList());

            // случайным образом обрабатываю все корабли с орудиями
            while(shipsGun.size() > 0) {

                // получаю случайный корабль с орудием
                Ship fromShip = shipsGun.get(rnd.nextInt(shipsGun.size()));

                // выполняю выстрелы последовательно со всех орудий корабля
                for (int j = 0; j < fromShip.getShipType().getGun(); j++) {

                    // формирую список вражеских кораблей
                    List<Ship> shipsEnemy = this.fleets.stream()
                            .flatMap(Fleet::getShips)
                            .filter(fromShip::isEnemy)
                            .collect(Collectors.toList());

                    // если не осталось вражеских кораблей
                    if (shipsEnemy.size() == 0)
                        break;

                    // получаю случайный вражеский корабль
                    Ship toShip = shipsEnemy.get(rnd.nextInt(shipsEnemy.size()));

                    // выполняю выстрел по выбранному вражескому кораблю
                    Shot shot = fromShip.fire(toShip);
                    shot.setRound(i);
                    shot.setNum(++num);
                    shots.add(shot);

                    // если выстрел успешный
                    if (shot.isResult()) {

                        // удаляю пораженный корабль из флота
                        this.fleets.stream()
                                .forEach(v -> v.remove(toShip));

                        // если пораженный корабль был с орудием, удаляю его из списка кораблей с орудием
                        if (toShip.isGun())
                            shipsGun.remove(toShip);

                    }

                }

                // удаляю обработанный корабль с орудием
                shipsGun.remove(fromShip);

            }

            // если ни один из оставшихся кораблей с орудием не может поразить ни один корабль (вероятность < 1%) противника - ничья
            if (this.fleets.stream()
                    .flatMap(Fleet::getShips)
                    .filter(Ship::isGun)
                    .allMatch(gun -> this.fleets.stream()
                                .flatMap(Fleet::getShips)
                                .filter(gun::isEnemy)
                                .map(gun::fire)
                                .allMatch(shot -> shot.getProbability() < 0.01)))
                break;

        }

        return shots;

    }

}
