package controllers;

import database.CustomerDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Customer;
import java.sql.SQLException;
import java.util.Observable;

public class AddRecordPageController extends Observable {

    private CustomerDB customerDB = CustomerDB.getSelf();

    @FXML
    private TextField nameField, aeNameField, regionField, businessIDField, capitalField, provinceField, khetField, khwangField, employeeField, contaceTelNumField, contactFaxField, contactField, contactNameField, averageField;
    @FXML
    private TextArea locationInstallArea;

    @FXML
    public void cancelBtn(ActionEvent event) {
        this.closeThisWindow(event);
//        this.nameField.setStyle("-fx-background-color: FFC1C1;");
    }

    public void submitBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isNameFieldCorrect = true;
        boolean isAeNameFieldCorrect = true;
        boolean isRegionFieldCorrect = true;
        boolean isBussinessIDFieldCorrect = true;
        boolean isCapitalFieldCorrect = true;
        boolean isProvinceFieldCorrect = true;
        boolean isKhetFieldCorrect = true;
        boolean isKhwangFieldCorrect = true;
        boolean isEmployeeFieldCorrect = true;
        boolean isContactTelNumFieldCorrect = true;
        boolean isContactFaxFieldCorrect = true;
        boolean isContactFieldCorrect = true;
        boolean isContactNameFieldCorrect = true;
        boolean isPacketCostFieldCorrect = true;

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
        double packetCost;

        isAllCharacter(nameField, isNameFieldCorrect);
        isAllCharacter(aeNameField, isAeNameFieldCorrect);
        isAllCharacter(regionField, isRegionFieldCorrect);
        isAllNumber(businessIDField, isBussinessIDFieldCorrect);
        isAllNumber(capitalField, isCapitalFieldCorrect);
        isAllCharacter(provinceField, isProvinceFieldCorrect);
        isAllCharacter(khetField, isKhetFieldCorrect);
        isAllCharacter(khwangField, isKhwangFieldCorrect);
        isAllNumber(employeeField, isEmployeeFieldCorrect);
        isAllNumber(contaceTelNumField, isContactTelNumFieldCorrect);
        isAllNumber(contactFaxField, isContactFaxFieldCorrect);
        isAllNumber(contactField, isContactFieldCorrect);
        isAllCharacter(contactNameField, isContactNameFieldCorrect);
        isAllNumber(averageField, isPacketCostFieldCorrect);

        if (this.averageField.getText().equals("")) {
            packetCost = 0;
        } else {
            packetCost = Double.parseDouble(this.averageField.getText());
        }

        if (isAllCorrect(isNameFieldCorrect,isAeNameFieldCorrect,isRegionFieldCorrect,isBussinessIDFieldCorrect,isCapitalFieldCorrect,isProvinceFieldCorrect,isKhetFieldCorrect,isKhwangFieldCorrect,isEmployeeFieldCorrect,
                isContactTelNumFieldCorrect,isContactFaxFieldCorrect,isContactFieldCorrect,isContactNameFieldCorrect,isPacketCostFieldCorrect)){
            Customer customer = new Customer(0, name, aeName, region, locationInstall, businessID, capital, province, khet, khwang, employee, contaceTelNum, contactFax, contact, contactName,
                    packetCost);
            this.customerDB.writeCustonerDB(customer);
            this.closeThisWindow(event);
        }

//        this.customerDB.writeCustonerDB(customer);
//        this.closeThisWindow(event);

//        setChanged();
//        notifyObservers(customer);
    }

    private void closeThisWindow(ActionEvent event) {
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    private boolean isAllNumber(TextField field, boolean isCorrect) {
        for (int i = 0; i < field.getText().length(); i++) {
            if (isCorrect) {
                if ((field.getText().charAt(i) + "").matches("[0-9]")) {
                } else {
                    isCorrect = false;
                    return isCorrect;
                }
            }
        }
        return isCorrect;
    }

    private boolean isAllCharacter(TextField field, boolean isCorrect) {
        for (int i = 0; i < field.getText().length(); i++) {
            if (isCorrect) {
                if ((field.getText().charAt(i) + "").matches("[a-zA-Z]")) {
                } else {
                    isCorrect = false;
                    return isCorrect;
                }
            }
        }
        return isCorrect;
    }

    private boolean isAllCharacter(TextArea Area, boolean isCorrect) {
        for (int i = 0; i < Area.getText().length(); i++) {
            if (isCorrect) {
                if ((Area.getText().charAt(i) + "").matches("[a-zA-Z]")) {
                } else {
                    isCorrect = false;
                    return isCorrect;
                }
            }
        }
        return isCorrect;
    }

    //19
    private boolean isAllCorrect(boolean correct1, boolean correct2, boolean correct3, boolean correct4, boolean correct5, boolean correct6, boolean correct7, boolean correct8, boolean correct9
            , boolean correct10, boolean correct11, boolean correct12, boolean correct13, boolean correct14) {
        boolean allCorrect=false;
        if (correct1 && correct2 && correct3 && correct4 && correct5 && correct6 && correct7 && correct8 && correct9 && correct10 && correct11 && correct12 && correct13 && correct14) {
            allCorrect = true;
        } else {
            allCorrect = false;
        }
        return allCorrect;
    }
}
