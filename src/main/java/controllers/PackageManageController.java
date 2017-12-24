package controllers;


import database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Package;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class PackageManageController extends MainController implements Observer{

    private DBConnector packageDB = DBConnector.getSelf();

    @FXML
    private TableView<Package> tableView;
    @FXML
    private Button deleteBtn;

    @FXML
    public void refresh() throws SQLException, ClassNotFoundException {
        ObservableList<Package> packages = FXCollections.observableArrayList();
        packages.addAll(packageDB.loadPacketDB());
        tableView.setItems(packages);
    }

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        this.refresh();
        deleteBtn.setDisable(true);

        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY) {
                    Package packages = tableView.getSelectionModel().getSelectedItem();
                    if ( packages != null ){
                        deleteBtn.setDisable(false);
                    }
                }else{
                    Package packages = tableView.getSelectionModel().getSelectedItem();
                    if ( packages != null ){
                        deleteBtn.setDisable(false);
                    }
                }
            }
        });
    }

    @FXML
    public void addBtn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddPackage.fxml"));
        Parent window = loader.load();

        AddPackagePageController addPackagePageController = loader.getController();
        addPackagePageController.addObserver(this);

        this.goToPage(stage,window,450,400);
    }

    @FXML
    public void deleteBtn() throws SQLException, ClassNotFoundException {
        if (tableView.getSelectionModel().getSelectedItem() != null){
                int numberUsePackage = packageDB.checkCustomerPackage(tableView.getSelectionModel().getSelectedItem().getId());
                if (numberUsePackage == 0){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " +
                            tableView.getSelectionModel().getSelectedItem().getName() + " ?",
                            ButtonType.OK, ButtonType.CANCEL);
                    Optional optional = alert.showAndWait();
                    if (optional.get() == ButtonType.OK) {
                        Package packages = tableView.getSelectionModel().getSelectedItem();
                        packageDB.inActivePackageDB(packages.getId());
                        this.refresh();
                    }
                } else {
                    String user = "user";
                    if (numberUsePackage > 1){
                        user = "users";
                    }
                    Alert alert = new Alert(Alert.AlertType.WARNING, "There is " + numberUsePackage + " " + user +
                            " subscribed this package", ButtonType.OK);
                    Optional optional = alert.showAndWait();
                }
        }
    }

    @FXML
    public void reportBtn(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReportTotalPricePage.fxml"));
        Parent window = loader.load();
        ReportTotalPricePageController reportTotalPricePageController = loader.getController();
        reportTotalPricePageController.setUsername(this.userLabel.getText());
        reportTotalPricePageController.setStatus("Package");
        this.goToPage(stage,window,400,600);
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            this.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
