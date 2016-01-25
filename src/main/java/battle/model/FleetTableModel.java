package battle.model;

import battle.ShipType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by iMacAverage on 24.01.16.
 */
public class FleetTableModel {

    private final StringProperty name;

    private final StringProperty count;

    public FleetTableModel(ShipType shipType, int count) {
        this.name = new SimpleStringProperty(shipType.getName());
        this.count = new SimpleStringProperty(String.valueOf(count));
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

    public StringProperty getCountProperty() {
        return this.count;
    }

    public String getCount() {
        return this.count.get();
    }

    public void setCount(int count) {
        this.count.set(String.valueOf(count));
    }

}
