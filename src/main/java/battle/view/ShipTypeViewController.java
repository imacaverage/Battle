package battle.view;

import battle.Battle;
import battle.ShipType;
import battle.model.ShipTypeTableModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Locale;

/**
 * Класс "Контроллер Представления объекта 'Диалог ввода типа корабля'"
 * Created by iMacAverage on 24.01.16.
 */
public class ShipTypeViewController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField gunTextField;

    @FXML
    private TextField weaponTextField;

    @FXML
    private TextField shieldTextField;

    @FXML
    private TextField costTextField;

    private Battle battle;

    private Stage stage;

    private ShipTypeTableModel shipTypeTableModel;

    private boolean okClicked = false;

    @FXML
    private void initialize() {
        this.gunTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int gun = Integer.parseInt(newValue);
                double weapon = Double.parseDouble(this.weaponTextField.getText());
                double shield = Double.parseDouble(this.shieldTextField.getText());
                double cost = ShipType.calcCost(gun, weapon, shield);
                this.costTextField.setText(String.format(Locale.ENGLISH, "%.4f", cost));
            }
            catch (NumberFormatException e) {}
        });
        this.weaponTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int gun = Integer.parseInt(this.gunTextField.getText());
                double weapon = Double.parseDouble(newValue);
                double shield = Double.parseDouble(this.shieldTextField.getText());
                double cost = ShipType.calcCost(gun, weapon, shield);
                this.costTextField.setText(String.format(Locale.ENGLISH, "%.4f", cost));
            }
            catch (NumberFormatException e) {}
        });
        this.shieldTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int gun = Integer.parseInt(this.gunTextField.getText());
                double weapon = Double.parseDouble(this.weaponTextField.getText());
                double shield = Double.parseDouble(newValue);
                double cost = ShipType.calcCost(gun, weapon, shield);
                this.costTextField.setText(String.format(Locale.ENGLISH, "%.4f", cost));
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
        if (this.nameTextField.getText() == null || this.nameTextField.getText().length() == 0 ) {
            errorMessage += "No valid Name!\n";
        }
        if (this.battle.getBattleModel().getMyShipTypeData().stream()
                .map(ShipTypeTableModel::getName)
                .anyMatch(v -> v.equals(this.nameTextField.getText()))) {
            errorMessage += "No valid Name (this Name already exists)!\n";
        }
        if (this.gunTextField.getText() == null || this.gunTextField.getText().length() == 0 ) {
            errorMessage += "No valid Gun!\n";
        }
        else {
            try {
                Integer.parseInt(this.gunTextField.getText());
            }
            catch (NumberFormatException e) {
                errorMessage += "No valid Gun (must be an integer)!\n";
            }
        }
        if (this.weaponTextField.getText() == null || this.weaponTextField.getText().length() == 0 ) {
            errorMessage += "No valid Weapon!\n";
        }
        else {
            try {
                Double.parseDouble(this.weaponTextField.getText());
            }
            catch (NumberFormatException e) {
                errorMessage += "No valid Weapon (must be an double)!\n";
            }
        }
        if (this.shieldTextField.getText() == null || this.shieldTextField.getText().length() == 0 ) {
            errorMessage += "No valid Shield!\n";
        }
        else {
            try {
                Double.parseDouble(this.shieldTextField.getText());
            }
            catch (NumberFormatException e) {
                errorMessage += "No valid Shield (must be an double)!\n";
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
            this.shipTypeTableModel.setName(this.nameTextField.getText());
            this.shipTypeTableModel.setGun(Integer.parseInt(this.gunTextField.getText()));
            this.shipTypeTableModel.setWeapon(Double.parseDouble(this.weaponTextField.getText()));
            this.shipTypeTableModel.setShield(Double.parseDouble(this.shieldTextField.getText()));
            this.shipTypeTableModel.setCost(Double.parseDouble(this.costTextField.getText()));
            this.okClicked = true;
            this.stage.close();
        }
    }

    @FXML
    private void handleCancel() {
        this.stage.close();
    }

    public void setShipTypeTableModel(ShipTypeTableModel shipTypeTableModel) {
        this.shipTypeTableModel = shipTypeTableModel;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

}
