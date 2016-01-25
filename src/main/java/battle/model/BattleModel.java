package battle.model;

import javafx.collections.ObservableList;

/**
 * Класс "Модель объекта 'Битва'"
 * Created by iMacAverage on 24.01.16.
 */
public class BattleModel {

    private final ObservableList<ShipTypeTableModel> enemyShipTypeData;

    private final ObservableList<FleetTableModel> enemyFleetData;

    private final ObservableList<ShipTypeTableModel> myShipTypeData;

    private final ObservableList<FleetTableModel> myFleetData;

    private double balance;

    public BattleModel(ObservableList<ShipTypeTableModel> enemyShipTypeData,
                       ObservableList<FleetTableModel> enemyFleetData,
                       ObservableList<ShipTypeTableModel> myShipTypeData,
                       ObservableList<FleetTableModel> myFleetData,
                       Double balance) {

        this.enemyShipTypeData = enemyShipTypeData;
        this.enemyFleetData = enemyFleetData;
        this.myShipTypeData = myShipTypeData;
        this.myFleetData = myFleetData;
        this.balance = balance;

    }

    public ObservableList<ShipTypeTableModel> getEnemyShipTypeData() {
        return this.enemyShipTypeData;
    }

    public ObservableList<FleetTableModel> getEnemyFleetData() {
        return this.enemyFleetData;
    }

    public ObservableList<ShipTypeTableModel> getMyShipTypeData() {
        return this.myShipTypeData;
    }

    public ObservableList<FleetTableModel> getMyFleetData() {
        return this.myFleetData;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
