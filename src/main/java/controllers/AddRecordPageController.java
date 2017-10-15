package controllers;

import database.CustomerDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Customer;

import java.sql.SQLException;
import java.util.Observable;

public class AddRecordPageController extends Observable{

    private CustomerDB customerDB = CustomerDB.getSelf();

    @FXML
    private TextField nameField, aeNameField, regionField,businessIDField,capitalField,provinceField,khetField
            ,khwangField,employeeField,contaceTelNumField,contactFaxField,contactField,contactNameField,averageField
            ,voiceField,internetField,dataField,mobilesField,tvsField;
    @FXML
    private TextArea locationInstallArea;

    @FXML
    public void cancelBtn(ActionEvent event){
        this.closeThisWindow(event);
//        this.nameField.setStyle("-fx-background-color: FFC1C1;");
    }

    public void submitBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        String name = this.nameField.getText();
        String aeName = this.aeNameField.getText();
        String region = this.regionField.getText();
        String locationInstall = this.locationInstallArea.getText();
        String businessID = this.businessIDField.getText();
        String capital = this.capitalField.getText();
        String province = this.provinceField.getText();
        String khet = this.khetField.getText();
        String khwang = this.khwangField.getText();
        String employee = this.employeeField.getText();
        String contaceTelNum = this.contaceTelNumField.getText();
        String contactFax = this.contactFaxField.getText();
        String contact = this.contactField.getText();
        String contactName = this.contactNameField.getText();
        double average;
        int voice;
        int internet;
        int data;
        int mobiles;
        int tvs;
        if (this.averageField.getText().equals("")){
            average = 0;
        } else {
            average = Double.parseDouble(this.averageField.getText());
        }
        if (this.voiceField.getText().equals("")){
            voice = 0;
        } else {
            voice = Integer.parseInt(this.voiceField.getText());
        }
        if (this.internetField.getText().equals("")){
            internet = 0;
        } else {
            internet = Integer.parseInt(this.internetField.getText());
        }
        if (this.dataField.getText().equals("")){
            data = 0;
        } else {
            data = Integer.parseInt(this.dataField.getText());
        }
        if (this.mobilesField.getText().equals("")){
            mobiles= 0;
        } else {
            mobiles = Integer.parseInt(this.mobilesField.getText());
        }
        if (this.tvsField.getText().equals("")){
            tvs = 0;
        } else {
            tvs = Integer.parseInt(this.tvsField.getText());
        }

        Customer customer = new Customer(0,name,aeName,region,locationInstall,businessID,capital,province,khet,khwang,employee,contaceTelNum,contactFax,contact,contactName,
                average,voice,internet,data,mobiles,tvs);

        this.customerDB.writeCustonerDB(customer);
        this.closeThisWindow(event);

//        setChanged();
//        notifyObservers(customer);
    }

    private void closeThisWindow(ActionEvent event){
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
