package controllers;

import database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

public class AddRecordPageController extends Observable {

    private DBConnector customerDB = DBConnector.getSelf();

    @FXML
    private TextField nameField, aeNameField, regionField, businessIDField, capitalField, provinceField, khetField, khwangField, employeeField, contaceTelNumField, contactFaxField, contactField, contactNameField;
    @FXML
    private TextArea locationInstallArea;

    @FXML
    public void cancelBtn(ActionEvent event) {
        this.closeThisWindow(event);
//        this.nameField.setStyle("-fx-background-color: FFC1C1;");
    }

    public void submitBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
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

            Customer customer = new Customer(0, name, aeName, region, locationInstall, businessID, capital, province, khet, khwang, employee, contaceTelNum, contactFax, contact, contactName,
                    packetCost);
            this.customerDB.writeCustomerDB(customer);

            this.closeThisWindow(event);

            setChanged();
            notifyObservers(customer);
        }

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

    //19
    private boolean isAllCorrect(ArrayList<Boolean> checkList) {
        for (boolean i : checkList){
            if (i == false){
                return false;
            }
        }
        return true;
    }
}
