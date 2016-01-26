package battle.model;

import battle.ShipType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.util.Locale;

/**
 * Класс "Модель объекта 'Таблица типов кораблей'"
 * Created by iMacAverage on 24.01.16.
 */
public class ShipTypeTableModel {

    private final StringProperty name;

    private final StringProperty gun;

    private final StringProperty weapon;

    private final StringProperty shield;

    private final StringProperty cost;

    public ShipTypeTableModel(ShipType shipType) {
        this.name = new SimpleStringProperty(shipType.getName());
        this.gun = new SimpleStringProperty(String.valueOf(shipType.getGun()));
        this.weapon = new SimpleStringProperty(String.format(Locale.ENGLISH, "%.4f", shipType.getWeapon()));
        this.shield = new SimpleStringProperty(String.format(Locale.ENGLISH, "%.4f", shipType.getShield()));
        this.cost = new SimpleStringProperty(String.format(Locale.ENGLISH, "%.4f", shipType.getCost()));
    }

    public ShipType getShipType() {
        return new ShipType(this.name.get(),
                Integer.parseInt(this.gun.get()),
                Double.parseDouble(this.weapon.get()),
                Double.parseDouble(this.shield.get()));
    }

    public StringProperty getNameProperty() {
        return this.name;
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty getGunProperty() {
        return this.gun;
    }

    public String getGun() {
        return this.gun.get();
    }

    public void setGun(int gun) {
        this.gun.set(String.valueOf(gun));
    }

    public StringProperty getWeaponProperty() {
        return this.weapon;
    }

    public String getWeapon() {
        return this.weapon.get();
    }

    public void setWeapon(double weapon) {
        this.weapon.set(String.format(Locale.ENGLISH, "%.4f", weapon));
    }

    public StringProperty getShieldProperty() {
        return this.shield;
    }

    public String getShield() {
        return this.shield.get();
    }

    public void setShield(double shield) {
        this.shield.set(String.format(Locale.ENGLISH, "%.4f", shield));
    }

    public StringProperty getCostProperty() {
        return this.cost;
    }

    public String getCost() {
        return this.cost.get();
    }

    public void setCost(double cost) {
        this.cost.set(String.format(Locale.ENGLISH, "%.4f", cost));
    }

}
