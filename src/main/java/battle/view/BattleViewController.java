package battle.view;

import battle.Battle;
import battle.ShipType;
import battle.model.FleetTableModel;
import battle.model.ShipTypeTableModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Locale;

/**
 * Класс "Контроллер Представления класса 'Битва'"
 * Created by iMacAverage on 24.01.16.
 */
public class BattleViewController {

    @FXML
    private TableView<ShipTypeTableModel> enemyShipTypeTable;

    @FXML
    private TableColumn<ShipTypeTableModel, String> nameColumnEnemyShipTypeTable;

    @FXML
    private TableColumn<ShipTypeTableModel, String> gunColumnEnemyShipTypeTable;

    @FXML
    private TableColumn<ShipTypeTableModel, String> weaponColumnEnemyShipTypeTable;

    @FXML
    private TableColumn<ShipTypeTableModel, String> shieldColumnEnemyShipTypeTable;

    @FXML
    private TableColumn<ShipTypeTableModel, String> costColumnEnemyShipTypeTable;

    @FXML
    private TableView<FleetTableModel> enemyFleetTable;

    @FXML
    private TableColumn<FleetTableModel, String> nameColumnEnemyFleetTable;

    @FXML
    private TableColumn<FleetTableModel, String> countColumnEnemyFleetTable;

    @FXML
    private TableView<ShipTypeTableModel> myShipTypeTable;

    @FXML
    private TableColumn<ShipTypeTableModel, String> nameColumnMyShipTypeTable;

    @FXML
    private TableColumn<ShipTypeTableModel, String> gunColumnMyShipTypeTable;

    @FXML
    private TableColumn<ShipTypeTableModel, String> weaponColumnMyShipTypeTable;

    @FXML
    private TableColumn<ShipTypeTableModel, String> shieldColumnMyShipTypeTable;

    @FXML
    private TableColumn<ShipTypeTableModel, String> costColumnMyShipTypeTable;

    @FXML
    private TableView<FleetTableModel> myFleetTable;

    @FXML
    private TableColumn<FleetTableModel, String> nameColumnMyFleetTable;

    @FXML
    private TableColumn<FleetTableModel, String> countColumnMyFleetTable;

    @FXML
    private TextField balanceTextField;

    @FXML
    private Button runButton;

    private Battle battle;

    public BattleViewController() {
    }

    @FXML
    private void initialize() {
        // столбцы таблицы enemyShipTypeTable
        this.nameColumnEnemyShipTypeTable.setCellValueFactory(cell -> cell.getValue().getNameProperty());
        this.gunColumnEnemyShipTypeTable.setCellValueFactory(cell -> cell.getValue().getGunProperty());
        this.weaponColumnEnemyShipTypeTable.setCellValueFactory(cell -> cell.getValue().getWeaponProperty());
        this.shieldColumnEnemyShipTypeTable.setCellValueFactory(cell -> cell.getValue().getShieldProperty());
        this.costColumnEnemyShipTypeTable.setCellValueFactory(cell -> cell.getValue().getCostProperty());
        // столбцы таблицы enemyFleetTable
        this.nameColumnEnemyFleetTable.setCellValueFactory(cell -> cell.getValue().getNameProperty());
        this.countColumnEnemyFleetTable.setCellValueFactory(cell -> cell.getValue().getCountProperty());
        // столбцы таблицы myShipTypeTable
        this.nameColumnMyShipTypeTable.setCellValueFactory(cell -> cell.getValue().getNameProperty());
        this.gunColumnMyShipTypeTable.setCellValueFactory(cell -> cell.getValue().getGunProperty());
        this.weaponColumnMyShipTypeTable.setCellValueFactory(cell -> cell.getValue().getWeaponProperty());
        this.shieldColumnMyShipTypeTable.setCellValueFactory(cell -> cell.getValue().getShieldProperty());
        this.costColumnMyShipTypeTable.setCellValueFactory(cell -> cell.getValue().getCostProperty());
        // столбцы таблицы enemyFleetTable
        this.nameColumnMyFleetTable.setCellValueFactory(cell -> cell.getValue().getNameProperty());
        this.countColumnMyFleetTable.setCellValueFactory(cell -> cell.getValue().getCountProperty());
        // надписи в таблицах, в случае если они пустые
        this.enemyShipTypeTable.setPlaceholder(new Label(""));
        this.enemyFleetTable.setPlaceholder(new Label(""));
        this.myShipTypeTable.setPlaceholder(new Label(""));
        this.myFleetTable.setPlaceholder(new Label(""));
    }

    @FXML
    private void handleDeleteMyShipType() {

        int selectedIndex = this.myShipTypeTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {

            ShipTypeTableModel shipTypeTableModel = this.myShipTypeTable.getItems().get(selectedIndex);

            if (this.battle.getBattleModel().getMyFleetData().stream()
                    .map(FleetTableModel::getName)
                    .anyMatch(v -> v.equals(shipTypeTableModel.getName()))) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(this.battle.getStage());
                alert.setTitle("Error");
                alert.setHeaderText("Fleet includes Ships of the Type");
                alert.setContentText("First remove Ships from Fleet");
                alert.showAndWait();
                return;

            }

            this.myShipTypeTable.getItems().remove(selectedIndex);

        }
        else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(this.battle.getStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Ship Type Selected");
            alert.setContentText("Please select a Ship Type in the table");
            alert.showAndWait();

        }

    }

    @FXML
    private void handleDeleteMyFleet() {

        int selectedIndex = this.myFleetTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {

            FleetTableModel fleetTableModel = this.myFleetTable.getItems().get(selectedIndex);
            double cost = this.battle.getBattleModel().getMyShipTypeData().stream()
                                        .filter(v -> v.getName().equals(fleetTableModel.getName()))
                                        .map(ShipTypeTableModel::getCost)
                                        .map(Double::parseDouble)
                                        .findFirst()
                                        .orElse(0.);
            int count = Integer.parseInt(fleetTableModel.getCount());
            double balance = this.battle.getBattleModel().getBalance();
            this.battle.getBattleModel().setBalance(balance + cost * count);
            this.balanceTextField.setText(String.format(Locale.ENGLISH, "%.4f", this.battle.getBattleModel().getBalance()));
            this.myFleetTable.getItems().remove(selectedIndex);

        }
        else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(this.battle.getStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Ship Type Selected");
            alert.setContentText("Please select a Ship Type in the table");
            alert.showAndWait();

        }

    }

    @FXML
    private void handleNewShipType() {
        ShipType shipType = new ShipType("", 0, 0, 0);
        ShipTypeTableModel shipTypeTableModel = new ShipTypeTableModel(shipType);
        if (this.battle.showShipTypeDialog(shipTypeTableModel))
            this.battle.getBattleModel().getMyShipTypeData().add(shipTypeTableModel);
    }

    @FXML
    private void handleNewFleet() {
        ShipType shipType = new ShipType("", 0, 0, 0);
        FleetTableModel fleetTableModel = new FleetTableModel(shipType, 0);
        if (this.battle.showFleetDialog(fleetTableModel)) {
            this.battle.getBattleModel().getMyFleetData().add(fleetTableModel);
            this.balanceTextField.setText(String.format(Locale.ENGLISH, "%.4f", this.battle.getBattleModel().getBalance()));
        }
    }

    @FXML
    private void handleRun() {
        this.runButton.setDisable(true);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                battle.runBattle();
                return null;
            }
        };
        task.stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                battle.showBattleResultDialog();
                runButton.setDisable(false);
            }
        });
        new Thread(task).start();
    }

    @FXML
    private void handleExit() {
        this.battle.getStage().close();
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
        this.enemyShipTypeTable.setItems(this.battle.getBattleModel().getEnemyShipTypeData());
        this.myShipTypeTable.setItems(this.battle.getBattleModel().getMyShipTypeData());
        this.enemyFleetTable.setItems(this.battle.getBattleModel().getEnemyFleetData());
        this.myFleetTable.setItems(this.battle.getBattleModel().getMyFleetData());
        this.balanceTextField.setText(String.format(Locale.ENGLISH, "%.4f", this.battle.getBattleModel().getBalance()));
    }

}
