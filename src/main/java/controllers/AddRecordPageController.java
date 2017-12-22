package controllers;

import check.CheckData;
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
    private CheckData checkData = new CheckData();

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
        boolean isNameFieldCorrect = checkData.isAllCharacter(nameField);
        boolean isAeNameFieldCorrect = checkData.isAllCharacter(aeNameField);
        boolean isRegionFieldCorrect = checkData.isNull(regionField);
        boolean isBussinessIDFieldCorrect = checkData.isAllNumber(businessIDField,13);
        boolean isCapitalFieldCorrect = checkData.isAllNumber(capitalField);
        boolean isProvinceFieldCorrect = checkData.isAllCharacter(provinceField);
        boolean isKhetFieldCorrect = checkData.isAllCharacter(khetField);
        boolean isKhwangFieldCorrect = checkData.isAllCharacter(khwangField);
        boolean isEmployeeFieldCorrect = checkData.isAllNumber(employeeField);
        boolean isContactTelNumFieldCorrect = checkData.isAllNumber(contaceTelNumField);
        boolean isContactFaxFieldCorrect = checkData.isAllNumber(contactFaxField);
        boolean isContactFieldCorrect = checkData.isAllNumber(contactField);
        boolean isContactNameFieldCorrect = checkData.isAllCharacter(contactNameField);

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

        if (checkData.isAllCorrect(checkList)){
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

            boolean checkDup = checkData.checkDupCustomer(nameField);

            if (checkDup == false){
                Customer customer = new Customer(0, name, aeName, region, locationInstall, businessID, capital, province, khet, khwang, employee, contaceTelNum, contactFax, contact, contactName,
                        packetCost);
                this.customerDB.writeCustomerDB(customer);

                this.closeThisWindow(event);

                setChanged();
                notifyObservers();
            } else {
                nameField.setStyle("-fx-border-color: red");
            }

        }

    }



    private void closeThisWindow(ActionEvent event) {
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
