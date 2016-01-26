package battle;
/**
 * Класс "Битва"
 * Created by iMacAverage on 22.01.16.
 */

import battle.model.*;
import battle.view.BattleResultViewController;
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
import java.util.ArrayList;
import java.util.Optional;

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
        shipType = new ShipType("Simple", 1, 1.5, 0);
        enemyShipTypeData.add(new ShipTypeTableModel(shipType));
        enemyFleetData.add(new FleetTableModel(shipType, 200));
        shipType = new ShipType("Barrel", 1, 40, 8);
        enemyShipTypeData.add(new ShipTypeTableModel(shipType));
        enemyFleetData.add(new FleetTableModel(shipType, 1));
        this.battleModel = new BattleModel(enemyShipTypeData, enemyFleetData, myShipTypeData, myFleetData, 300.);
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

    public void showBattleResultDialog() {
        BattleResultModel battleResultModel = this.runBattle();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/battle/view/BattleResultView.fxml"));
        try {
            Parent parent = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Battle Result");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(this.stage);
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            BattleResultViewController battleResultViewController = loader.getController();
            battleResultViewController.setModel(battleResultModel);
            battleResultViewController.setStage(stage);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BattleResultModel runBattle() {
        int result;
        Race enemyRace = new Race(0, "Enemy");
        Race myRace = new Race(1, "MyRace");
        ArrayList<Ship> enemyShips = new ArrayList<>();
        this.getBattleModel().getEnemyFleetData().stream()
                .forEach(v -> {
                    int count = Integer.parseInt(v.getCount());
                    Optional<ShipType> shipType = this.getBattleModel().getEnemyShipTypeData().stream()
                            .filter(type -> type.getName().equals(v.getName()))
                            .findFirst()
                            .map(ShipTypeTableModel::getShipType);
                    if (shipType.isPresent()) {
                        Ship ship = new Ship(enemyRace, shipType.get());
                        for (int i = 0; i < count; i++)
                            enemyShips.add(ship);
                    }
                });
        ArrayList<Ship> myShips = new ArrayList<>();
        this.getBattleModel().getMyFleetData().stream()
                .forEach(v -> {
                    int count = Integer.parseInt(v.getCount());
                    Optional<ShipType> shipType = this.getBattleModel().getMyShipTypeData().stream()
                            .filter(type -> type.getName().equals(v.getName()))
                            .findFirst()
                            .map(ShipTypeTableModel::getShipType);
                    if (shipType.isPresent()) {
                        Ship ship = new Ship(myRace, shipType.get());
                        for (int i = 0; i < count; i++)
                            myShips.add(ship);
                    }
                });
        ArrayList<Fleet> fleets = new ArrayList<>();
        fleets.add(new Fleet(enemyShips));
        fleets.add(new Fleet(myShips));
        Fight fight = new Fight(fleets);
        ArrayList<Shot> shots = fight.execute();
        if (fleets.stream().filter(Fleet::notEmpty).count() == 1) {
            Optional<Race> race = fleets.stream()
                    .filter(Fleet::notEmpty)
                    .flatMap(Fleet::getShips)
                    .map(Ship::getRace)
                    .distinct()
                    .findFirst();
            result = race.get().getId() == 0? -1: 1;
        }
        else
            result = 0;
        ObservableList<ShotsTableModel> shotsTableModels = FXCollections.observableArrayList();
        shots.stream()
                .map(ShotsTableModel::new)
                .forEach(shotsTableModels::add);
        ObservableList<SurvivingShipsTableModel> survivingShipsTableModels = FXCollections.observableArrayList();
        fleets.stream()
                .filter(Fleet::notEmpty)
                .flatMap(Fleet::getShips)
                .distinct()
                .forEach(v -> {
                    int count = (int) fleets.stream()
                            .flatMap(Fleet::getShips)
                            .map(Ship::getShipType)
                            .map(ShipType::getName)
                            .filter(v.getShipType().getName()::equals)
                            .count();
                    survivingShipsTableModels.add(new SurvivingShipsTableModel(v, count));
                });
        return new BattleResultModel(survivingShipsTableModels, shotsTableModels, result);
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
