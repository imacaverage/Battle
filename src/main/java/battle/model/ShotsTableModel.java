package battle.model;

import battle.Shot;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Locale;

/**
 * Created by iMacAverage on 25.01.16.
 */
public class ShotsTableModel {

    private final StringProperty num;

    private final StringProperty round;

    private final StringProperty raceFrom;

    private final StringProperty nameShipTypeFrom;

    private final StringProperty weapon;

    private final StringProperty raceTo;

    private final StringProperty nameShipTypeTo;

    private final StringProperty shield;

    private final StringProperty probability;

    private final StringProperty result;

    public ShotsTableModel(Shot shot) {
        this.num = new SimpleStringProperty(String.format("%06d", shot.getNum()));
        this.round = new SimpleStringProperty(String.valueOf(shot.getRound()));
        this.raceFrom = new SimpleStringProperty(shot.getFromShip().getRace().getName());
        this.nameShipTypeFrom = new SimpleStringProperty(shot.getFromShip().getShipType().getName());
        this.weapon = new SimpleStringProperty(String.format(Locale.ENGLISH, "%.4f", shot.getFromShip().getShipType().getWeapon()));
        this.raceTo = new SimpleStringProperty(shot.getToShip().getRace().getName());
        this.nameShipTypeTo = new SimpleStringProperty(shot.getToShip().getShipType().getName());
        this.shield = new SimpleStringProperty(String.format(Locale.ENGLISH, "%.4f", shot.getToShip().getShipType().getShield()));
        this.probability = new SimpleStringProperty(String.format(Locale.ENGLISH, "%.4f", shot.getProbability()));
        this.result = new SimpleStringProperty(String.format(Locale.ENGLISH, "%b", shot.isResult()));
    }

    public StringProperty getNum() {
        return num;
    }

    public StringProperty getRound() {
        return round;
    }

    public StringProperty getRaceFrom() {
        return raceFrom;
    }

    public StringProperty getNameShipTypeFrom() {
        return nameShipTypeFrom;
    }

    public StringProperty getWeapon() {
        return weapon;
    }

    public StringProperty getRaceTo() {
        return raceTo;
    }

    public StringProperty getNameShipTypeTo() {
        return nameShipTypeTo;
    }

    public StringProperty getShield() {
        return shield;
    }

    public StringProperty getProbability() {
        return probability;
    }

    public StringProperty getResult() {
        return result;
    }

}
