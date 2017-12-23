package controllers;

import check.CheckData;
import database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

public class EditRecordPageController extends Observable{

    private DBConnector customerDB = DBConnector.getSelf();
    private CheckData checkData = new CheckData();

    @FXML
    private TextField nameField, businessIDField, capitalField, employeeField, contaceTelNumField,contactFaxField, contactField, contactNameField;
    @FXML
    private ComboBox cmbAeName,cmbRegion,cmbProvince,cmbKhet,cmbKhwang;
    @FXML
    private TextArea locationInstallArea;
    @FXML
    private Label subscriberNumber;
    private Customer customer;

    private String[] regionList = {"01","02"};
    private String[] provinceList;

    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList aeName = FXCollections.observableArrayList();
        aeName.addAll(customerDB.loadNameEmployee());
        cmbAeName.setItems(aeName);

        ObservableList region = FXCollections.observableArrayList();
        region.addAll(regionList);
        cmbRegion.setItems(region);

        ObservableList province = FXCollections.observableArrayList();
        province.addAll(provinceList);
        cmbProvince.setItems(province);
    }

    @FXML
    public void cancelBtn(ActionEvent event){
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void editBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isNameFieldCorrect = checkData.isAllCharacter(nameField);
        boolean isBussinessIDFieldCorrect = checkData.isAllNumber(businessIDField,13);
        boolean isCapitalFieldCorrect = checkData.isAllNumber(capitalField);
        boolean isEmployeeFieldCorrect = checkData.isAllNumber(employeeField);
        boolean isContactTelNumFieldCorrect = checkData.isAllNumber(contaceTelNumField);
        boolean isContactFaxFieldCorrect = checkData.isAllNumber(contactFaxField);
        boolean isContactFieldCorrect = checkData.isAllNumber(contactField);
        boolean isContactNameFieldCorrect = checkData.isAllCharacter(contactNameField);

        ArrayList<Boolean> checkList = new ArrayList<Boolean>();
        checkList.add(isNameFieldCorrect);
        checkList.add(isBussinessIDFieldCorrect);
        checkList.add(isCapitalFieldCorrect);
        checkList.add(isEmployeeFieldCorrect);
        checkList.add(isContactTelNumFieldCorrect);
        checkList.add(isContactFaxFieldCorrect);
        checkList.add(isContactFieldCorrect);
        checkList.add(isContactNameFieldCorrect);

        if (checkData.isAllCorrect(checkList)){
            int id = Integer.parseInt(subscriberNumber.getText());
            String name = this.nameField.getText();
            String aeName = String.valueOf(this.cmbAeName.getValue());
            String region = String.valueOf(this.cmbRegion.getValue());
            String businessID = this.businessIDField.getText();
            String locationInstall = this.locationInstallArea.getText();//ไม่ต้องเช็ค
            String capital = this.capitalField.getText();
            String province = String.valueOf(this.cmbProvince.getValue());
            String khet = String.valueOf(this.cmbKhet.getValue());
            String khwang = String.valueOf(this.cmbKhwang.getValue());
            String employee = this.employeeField.getText();
            String contaceTelNum = this.contaceTelNumField.getText();
            String contactFax = this.contactFaxField.getText();
            String contact = this.contactField.getText();
            String contactName = this.contactNameField.getText();
            double packetCost = 0;

            Customer customer = new Customer(id, name, aeName, region, locationInstall, businessID, capital, province, khet, khwang, employee, contaceTelNum, contactFax, contact, contactName,
                        packetCost);

            this.customerDB.editCustomerDB(customer);

            this.closeThisWindow(event);

            setChanged();
            notifyObservers();

        }
    }

    public void setValue(Customer customer) {
        this.customer = customer;

        subscriberNumber.setText(String.valueOf(this.customer.getId()));
        nameField.setText(this.customer.getName());
        cmbAeName.setValue(this.customer.getAeName());
        cmbRegion.setValue(this.customer.getRegion());
        locationInstallArea.setText(this.customer.getLocationInstall());
        businessIDField.setText(this.customer.getBusinessId());
        capitalField.setText(this.customer.getCapital());
        cmbProvince.setValue(this.customer.getProvince());
        cmbKhet.setValue(this.customer.getKhet());
        cmbKhwang.setValue(this.customer.getKhwang());
        employeeField.setText(this.customer.getEmployee());
        contaceTelNumField.setText(this.customer.getContactTelNum());
        contactFaxField.setText(this.customer.getContactFax());
        contactField.setText(this.customer.getContact());
        contactNameField.setText(this.customer.getContactName());
    }

    private void closeThisWindow(ActionEvent event) {
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
