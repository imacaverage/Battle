package battle.model;

import battle.Ship;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Locale;

/**
 * Created by iMacAverage on 25.01.16.
 */
public class SurvivingShipsTableModel {

    private final StringProperty race;

    private final StringProperty name;

    private final StringProperty weapon;

    private final StringProperty shield;

    private final StringProperty count;

    public SurvivingShipsTableModel(Ship ship, int count) {
        this.race = new SimpleStringProperty(ship.getRace().getName());
        this.name = new SimpleStringProperty(ship.getShipType().getName());
        this.weapon = new SimpleStringProperty(String.format(Locale.ENGLISH, "%.4f", ship.getShipType().getWeapon()));
        this.shield = new SimpleStringProperty(String.format(Locale.ENGLISH, "%.4f", ship.getShipType().getShield()));
        this.count = new SimpleStringProperty(String.valueOf(count));
    }

    public StringProperty getRace() {
        return race;
    }

    public StringProperty getName() {
        return name;
    }

    public StringProperty getWeapon() {
        return weapon;
    }

    public StringProperty getShield() {
        return shield;
    }

    public StringProperty getCount() {
        return count;
    }

}
