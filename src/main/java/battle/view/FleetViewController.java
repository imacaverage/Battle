package battle.view;

import battle.Battle;
import battle.model.FleetTableModel;
import battle.model.ShipTypeTableModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Locale;

/**
 * Класс "Контроллер Представления объекта 'Диалог добавления кораблей во флот'"
 * Created by iMacAverage on 24.01.16.
 */
public class FleetViewController {

    @FXML
    private ComboBox<String> nameShipTypeComboBox;

    @FXML
    private TextField countTextField;

    @FXML
    private TextField costTextField;

    private Battle battle;

    private Stage stage;

    private FleetTableModel fleetTableModel;

    private boolean okClicked = false;

    @FXML
    private void initialize() {
        this.nameShipTypeComboBox.setPromptText("Select Ship Type");
        this.nameShipTypeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            Double cost = battle.getBattleModel().getMyShipTypeData().stream()
                    .filter(v -> v.getName().equals(newValue))
                    .map(ShipTypeTableModel::getCost)
                    .map(Double::parseDouble)
                    .findFirst()
                    .orElse(0.);
            try {
                int count = Integer.parseInt(countTextField.getText());
                this.costTextField.setText(String.format(Locale.ENGLISH, "%.4f", cost * count));
            }
            catch (NumberFormatException e) {}
        });
        this.countTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            Double cost = battle.getBattleModel().getMyShipTypeData().stream()
                    .filter(v -> v.getName().equals(this.nameShipTypeComboBox.getValue()))
                    .map(ShipTypeTableModel::getCost)
                    .map(Double::parseDouble)
                    .findFirst()
                    .orElse(0.);
            try {
                int count = Integer.parseInt(newValue);
                this.costTextField.setText(String.format(Locale.ENGLISH, "%.4f", cost * count));
            }
            catch (NumberFormatException e) {}
        });
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isOkClicked() {
        return this.okClicked;
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (this.nameShipTypeComboBox.getValue() == null || this.nameShipTypeComboBox.getValue().length() == 0 ) {
            errorMessage += "No select Ship Type!\n";
        }
        if (this.battle.getBattleModel().getMyFleetData().stream()
                .map(FleetTableModel::getName)
                .anyMatch(v -> v.equals(this.nameShipTypeComboBox.getValue()))) {
            errorMessage += "No valid Ship Type (this Ship Type already exists)!\n";
        }
        if (this.countTextField.getText() == null || this.countTextField.getText().length() == 0 ) {
            errorMessage += "No valid Count!\n";
        }
        else {
            try {
                Integer.parseInt(this.countTextField.getText());
                if (Double.parseDouble(this.costTextField.getText()) > this.battle.getBattleModel().getBalance()) {
                    errorMessage += "Going beyond the Balance!\n";
                }
            }
            catch (NumberFormatException e) {
                errorMessage += "No valid Count (must be an integer)!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void handleOk() {
        if (this.isInputValid()) {
            this.fleetTableModel.setName(this.nameShipTypeComboBox.getValue());
            this.fleetTableModel.setCount(Integer.parseInt(this.countTextField.getText()));
            this.okClicked = true;
            double balance = this.battle.getBattleModel().getBalance();
            double cost = Double.parseDouble(this.costTextField.getText());
            this.battle.getBattleModel().setBalance(balance - cost);
            this.stage.close();
        }
    }

    @FXML
    private void handleCancel() {
        this.stage.close();
    }

    public void setFleetTableModel(FleetTableModel fleetTableModel) {
        this.fleetTableModel = fleetTableModel;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
        this.battle.getBattleModel().getMyShipTypeData().stream()
                .map(ShipTypeTableModel::getName)
                .forEach(this.nameShipTypeComboBox.getItems()::add);
    }

}
