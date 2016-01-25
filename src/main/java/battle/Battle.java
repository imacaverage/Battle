package battle;
/**
 * Класс "Битва"
 * Created by iMacAverage on 22.01.16.
 */

import battle.model.BattleModel;
import battle.model.FleetTableModel;
import battle.model.ShipTypeTableModel;
import battle.view.BattleViewController;
import battle.view.FleetViewController;
import battle.view.ShipTypeViewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Battle extends Application {

    private Stage stage;

    private BattleModel battleModel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.setStage(primaryStage);
        Parent parent = null;
        this.initData();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/battle/view/BattleView.fxml"));
            parent = loader.load();
            BattleViewController battleViewController = loader.getController();
            battleViewController.setBattle(this);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        primaryStage.setTitle("Battle");
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(500);
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

    public void initData() {
        ShipType shipType;
        ObservableList<ShipTypeTableModel> enemyShipTypeData = FXCollections.observableArrayList();
        ObservableList<ShipTypeTableModel> myShipTypeData = FXCollections.observableArrayList();
        ObservableList<FleetTableModel> enemyFleetData = FXCollections.observableArrayList();
        ObservableList<FleetTableModel> myFleetData = FXCollections.observableArrayList();
        shipType = new ShipType("Simple", 1, 1, 1);
        enemyShipTypeData.add(new ShipTypeTableModel(shipType));
        enemyFleetData.add(new FleetTableModel(shipType, 20));
        shipType = new ShipType("Barrel", 1, 20, 5);
        enemyShipTypeData.add(new ShipTypeTableModel(shipType));
        enemyFleetData.add(new FleetTableModel(shipType, 5));
        this.battleModel = new BattleModel(enemyShipTypeData, enemyFleetData, myShipTypeData, myFleetData, new Double(300));
    }

    public boolean showShipTypeDialog(ShipTypeTableModel shipTypeTableModel) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/battle/view/ShipTypeView.fxml"));
        try {
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Ship Type");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.stage);
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            ShipTypeViewController shipTypeViewController = loader.getController();
            shipTypeViewController.setShipTypeTableModel(shipTypeTableModel);
            shipTypeViewController.setStage(stage);
            shipTypeViewController.setBattle(this);
            stage.showAndWait();
            return shipTypeViewController.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showFleetDialog(FleetTableModel fleetTableModel) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/battle/view/FleetView.fxml"));
        try {
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Add Ships to Fleet");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.stage);
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            FleetViewController fleetViewController = loader.getController();
            fleetViewController.setFleetTableModel(fleetTableModel);
            fleetViewController.setStage(stage);
            fleetViewController.setBattle(this);
            stage.showAndWait();
            return fleetViewController.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public BattleModel getBattleModel() {
        return this.battleModel;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
