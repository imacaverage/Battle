package battle;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Класс для тестирования класса "Бой"
 * Created by iMacAverage on 21.01.16.
 */
public class FightTest {

    @Test
    public void testExecute() throws Exception {
        ArrayList<Ship> ships1 = new ArrayList<>();
        ArrayList<Ship> ships2 = new ArrayList<>();
        ArrayList<Ship> ships3 = new ArrayList<>();
        Race race1 = new Race(1, "Race1");
        Race race2 = new Race(2, "Race2");
        Race race3 = new Race(3, "Race3");
        ShipType shipSimple = new ShipType("Meet", 0, 0, 1);
        ShipType shipDulo = new ShipType("Dulo", 1, 4, 20);
        ShipType shipPerf = new ShipType("Perf", 10, 1, 1);
        Ship ship;
        for(int i = 0; i < 500; i++) {
            ship = new Ship(race1, shipSimple);
            ships1.add(ship);
        }
        ship = new Ship(race1, shipDulo);
        ships1.add(ship);
        ship = new Ship(race2, shipDulo);
        ships2.add(ship);
        ship = new Ship(race3, shipPerf);
        ships3.add(ship);
        Fleet fleet1 = new Fleet(ships1);
        Fleet fleet2 = new Fleet(ships2);
        Fleet fleet3 = new Fleet(ships3);
        ArrayList<Fleet> fleets = new ArrayList<>();
        fleets.add(fleet1);
        fleets.add(fleet2);
        fleets.add(fleet3);
        Fight fight = new Fight(fleets);
        ArrayList<Shot> shots = fight.execute();
        shots.stream().forEach(System.out::println);
        if (fleets.stream().filter(Fleet::notEmpty).count() == 1)
            System.out.println("Победитель:");
        else
            System.out.println("Ничья, оставшиеся расы:");
        fleets.stream()
                .filter(Fleet::notEmpty)
                .flatMap(Fleet::getShips)
                .map(Ship::getRace)
                .distinct()
                .forEach(System.out::println);
    }

}