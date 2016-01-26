package battle.model;

import javafx.collections.ObservableList;

/**
 * Created by iMacAverage on 25.01.16.
 */
public class BattleResultModel {

    private final ObservableList<SurvivingShipsTableModel> ships;

    private final ObservableList<ShotsTableModel> shots;

    private final int result;

    public BattleResultModel(ObservableList<SurvivingShipsTableModel> ships,
                             ObservableList<ShotsTableModel> shots,
                             int result) {
        this.ships = ships;
        this.shots = shots;
        this.result = result;
    }

    public ObservableList<SurvivingShipsTableModel> getShips() {
        return this.ships;
    }

    public ObservableList<ShotsTableModel> getShots() {
        return this.shots;
    }

    public int getResult() {
        return result;
    }

}
