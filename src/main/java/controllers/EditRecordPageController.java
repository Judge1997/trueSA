package controllers;

import database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

public class EditRecordPageController extends Observable{

    private DBConnector customerDB = DBConnector.getSelf();

    @FXML
    private TextField nameField, aeNameField, regionField, businessIDField, capitalField, provinceField, khetField, khwangField, employeeField, contaceTelNumField;
    @FXML
    private TextField contactFaxField, contactField, contactNameField;
    @FXML
    private TextArea locationInstallArea;
    @FXML
    private Label subscriberNumber;
    private Customer customer;

    @FXML
    public void cancelBtn(ActionEvent event){
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void editBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isNameFieldCorrect = isAllCharacter(nameField);
        boolean isAeNameFieldCorrect = isAllCharacter(aeNameField);
        boolean isRegionFieldCorrect = isAllCharacter(regionField);
        boolean isBussinessIDFieldCorrect = isAllNumber(businessIDField);
        boolean isCapitalFieldCorrect = isAllNumber(capitalField);
        boolean isProvinceFieldCorrect = isAllCharacter(provinceField);
        boolean isKhetFieldCorrect = isAllCharacter(khetField);
        boolean isKhwangFieldCorrect = isAllCharacter(khwangField);
        boolean isEmployeeFieldCorrect = isAllNumber(employeeField);
        boolean isContactTelNumFieldCorrect = isAllNumber(contaceTelNumField);
        boolean isContactFaxFieldCorrect = isAllNumber(contactFaxField);
        boolean isContactFieldCorrect = isAllNumber(contactField);
        boolean isContactNameFieldCorrect = isAllCharacter(contactNameField);

        ArrayList<Boolean> checkList = new ArrayList<Boolean>();
        checkList.add(isNameFieldCorrect);
        checkList.add(isAeNameFieldCorrect);
        checkList.add(isRegionFieldCorrect);
        checkList.add(isBussinessIDFieldCorrect);
        checkList.add(isCapitalFieldCorrect);
        checkList.add(isProvinceFieldCorrect);
        checkList.add(isKhetFieldCorrect);
        checkList.add(isKhwangFieldCorrect);
        checkList.add(isEmployeeFieldCorrect);
        checkList.add(isContactTelNumFieldCorrect);
        checkList.add(isContactFaxFieldCorrect);
        checkList.add(isContactFieldCorrect);
        checkList.add(isContactNameFieldCorrect);

        if (isAllCorrect(checkList)){
            String name = this.nameField.getText();
            String aeName = this.aeNameField.getText();
            String region = this.regionField.getText();
            String businessID = this.businessIDField.getText();
            String locationInstall = this.locationInstallArea.getText();//ไม่ต้องเช็ค
            String capital = this.capitalField.getText();
            String province = this.provinceField.getText();
            String khet = this.khetField.getText();
            String khwang = this.khwangField.getText();
            String employee = this.employeeField.getText();
            String contaceTelNum = this.contaceTelNumField.getText();
            String contactFax = this.contactFaxField.getText();
            String contact = this.contactField.getText();
            String contactName = this.contactNameField.getText();
            double packetCost = 0;

            Customer customer = new Customer(this.customer.getId(), name, aeName, region, locationInstall, businessID, capital, province, khet, khwang, employee, contaceTelNum, contactFax, contact, contactName,
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
        aeNameField.setText(this.customer.getAeName());
        regionField.setText(this.customer.getRegion());
        locationInstallArea.setText(this.customer.getLocationInstall());
        businessIDField.setText(this.customer.getBusinessId());
        capitalField.setText(this.customer.getCapital());
        provinceField.setText(this.customer.getProvince());
        khetField.setText(this.customer.getKhet());
        khwangField.setText(this.customer.getKhwang());
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

    private boolean isAllNumber(TextField field) {
        boolean isCorrect = true;
        for (int i = 0; i < field.getText().length(); i++) {
            if (isCorrect) {
                if ((field.getText().charAt(i) + "").matches("[0-9]")) {
                } else {
                    isCorrect = false;
                    field.setStyle("-fx-border-color: red");
                    return isCorrect;
                }
            }
        }
        field.setStyle("");
        return isCorrect;
    }

    private boolean isAllCharacter(TextField field) {
        boolean isCorrect = true;
        for (int i = 0; i < field.getText().length(); i++) {
            if (isCorrect) {
                if ((field.getText().charAt(i) + "").matches("[a-zA-Z]")) {
                } else {
                    isCorrect = false;
                    field.setStyle("-fx-border-color: red");
                    return isCorrect;
                }
            }
        }
        field.setStyle("");
        return isCorrect;
    }

    private boolean isAllCharacter(TextArea area) {
        boolean isCorrect = true;
        for (int i = 0; i < area.getText().length(); i++) {
            if (isCorrect) {
                if ((area.getText().charAt(i) + "").matches("[a-zA-Z]")) {
                } else {
                    isCorrect = false;
                    area.setStyle("-fx-border-color: red");
                    return isCorrect;
                }
            }
        }
        area.setStyle("");
        return isCorrect;
    }

    private boolean isAllCorrect(ArrayList<Boolean> checkList) {
        for (boolean i : checkList){
            if (i == false){
                return false;
            }
        }
        return true;
    }
}
