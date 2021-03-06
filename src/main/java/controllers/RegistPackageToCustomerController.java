package controllers;

import database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.Customer;
import models.Package;

import java.sql.SQLException;
import java.util.Observable;

public class RegistPackageToCustomerController extends Observable {

    private DBConnector packageDB = DBConnector.getSelf();

    private Customer customer;

    @FXML
    private ComboBox comboBox;

    @FXML
    private Button addBtn;

    @FXML
    private Label nameLabel, priceLabel, internetLabel, dataLabel, voiceLabel, mobileUserLabel, mobileSpeedLabel, mobileTimesLabel, tvsLabel;

    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<Package> packages = FXCollections.observableArrayList();
        packages.addAll(packageDB.loadPackageDB());
        comboBox.setItems(packages);
        addBtn.setDisable(true);
    }

    @FXML
    public void comboBox(){
        Package packages = (Package) comboBox.getSelectionModel().getSelectedItem();

        nameLabel.setText(packages.getName());
        priceLabel.setText(String.valueOf(packages.getPriceString())+" Baht");
        internetLabel.setText(String.valueOf(packages.getNetString())+" Mbps.");
        voiceLabel.setText(String.valueOf(packages.getVoiceString())+" Minute");
        dataLabel.setText(String.valueOf(packages.getDataString())+" GB.");
//        mobileLabel.setText(packages.getMobile());
        mobileUserLabel.setText(String.valueOf(packages.getMobileQuantityString())+" Users");
        mobileSpeedLabel.setText(String.valueOf(packages.getMobileSpeedString())+" Mbps.");
        mobileTimesLabel.setText(String.valueOf(packages.getMobileTimesString())+" Minute");
        tvsLabel.setText(String.valueOf(packages.getTvsString())+" Channels");

        addBtn.setDisable(false);
    }

    @FXML
    public void addBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        Package packages = (Package) comboBox.getSelectionModel().getSelectedItem();

        packageDB.writeCustomerPackage(customer.getId(),packages.getId());

        this.closeThisWindow(event);

        setChanged();
        notifyObservers();
    }

    @FXML
    public void cancelBtn(ActionEvent event){
        this.closeThisWindow(event);
    }

    private void closeThisWindow(ActionEvent event) {
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void setValue(Customer customer) {
        this.customer = customer;
    }
}
