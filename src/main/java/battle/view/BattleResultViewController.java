package battle.view;

import battle.model.BattleResultModel;
import battle.model.ShotsTableModel;
import battle.model.SurvivingShipsTableModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * Created by iMacAverage on 25.01.16.
 */
public class BattleResultViewController {

    @FXML
    private Label result;

    @FXML
    private TableView<ShotsTableModel> shotsTable;

    @FXML
    private TableView<SurvivingShipsTableModel> shipsTable;

    @FXML
    private TableColumn<ShotsTableModel, String> numShotsTable;

    @FXML
    private TableColumn<ShotsTableModel, String> roundShotsTable;

    @FXML
    private TableColumn<ShotsTableModel, String> raceFromShotsTable;

    @FXML
    private TableColumn<ShotsTableModel, String> nameFromShotsTable;

    @FXML
    private TableColumn<ShotsTableModel, String> weaponShotsTable;

    @FXML
    private TableColumn<ShotsTableModel, String> raceToShotsTable;

    @FXML
    private TableColumn<ShotsTableModel, String> nameToShotsTable;

    @FXML
    private TableColumn<ShotsTableModel, String> shieldShotsTable;

    @FXML
    private TableColumn<ShotsTableModel, String> probabilityShotsTable;

    @FXML
    private TableColumn<ShotsTableModel, String> resultShotsTable;

    @FXML
    private TableColumn<SurvivingShipsTableModel, String> raceShipsTable;

    @FXML
    private TableColumn<SurvivingShipsTableModel, String> nameShipsTable;

    @FXML
    private TableColumn<SurvivingShipsTableModel, String> weaponShipsTable;

    @FXML
    private TableColumn<SurvivingShipsTableModel, String> shieldShipsTable;

    @FXML
    private TableColumn<SurvivingShipsTableModel, String> countShipsTable;

    private Stage stage;

    private BattleResultModel battleResultModel;

    @FXML
    private void initialize() {
        // столбцы таблицы ShipsTable
        this.raceShipsTable.setCellValueFactory(cell -> cell.getValue().getRace());
        this.nameShipsTable.setCellValueFactory(cell -> cell.getValue().getName());
        this.weaponShipsTable.setCellValueFactory(cell -> cell.getValue().getWeapon());
        this.shieldShipsTable.setCellValueFactory(cell -> cell.getValue().getShield());
        this.countShipsTable.setCellValueFactory(cell -> cell.getValue().getCount());
        // столбцы таблицы ShotsTable
        this.numShotsTable.setCellValueFactory(cell -> cell.getValue().getNum());
        this.roundShotsTable.setCellValueFactory(cell -> cell.getValue().getRound());
        this.raceFromShotsTable.setCellValueFactory(cell -> cell.getValue().getRaceFrom());
        this.nameFromShotsTable.setCellValueFactory(cell -> cell.getValue().getNameShipTypeFrom());
        this.weaponShotsTable.setCellValueFactory(cell -> cell.getValue().getWeapon());
        this.raceToShotsTable.setCellValueFactory(cell -> cell.getValue().getRaceTo());
        this.nameToShotsTable.setCellValueFactory(cell -> cell.getValue().getNameShipTypeTo());
        this.shieldShotsTable.setCellValueFactory(cell -> cell.getValue().getShield());
        this.probabilityShotsTable.setCellValueFactory(cell -> cell.getValue().getProbability());
        this.resultShotsTable.setCellValueFactory(cell -> cell.getValue().getResult());
        // надписи в таблицах, в случае если они пустые
        this.shotsTable.setPlaceholder(new Label(""));
        this.shipsTable.setPlaceholder(new Label(""));
    }

    @FXML
    private void handleOk() {
        this.stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setModel(BattleResultModel battleResultModel) {
        this.battleResultModel = battleResultModel;
        this.shotsTable.setItems(this.battleResultModel.getShots());
        this.shipsTable.setItems(this.battleResultModel.getShips());
        switch (this.battleResultModel.getResult()) {
            case 0:
                this.result.setText("Draw");
                break;
            case 1:
                this.result.setText("Win");
                break;
            case -1:
                this.result.setText("Loss");
                break;
        }
    }

}
