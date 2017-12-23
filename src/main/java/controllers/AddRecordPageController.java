package controllers;

import check.CheckData;
import database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
    private TextField nameField, businessIDField, capitalField, employeeField, contaceTelNumField, contactFaxField, contactField, contactNameField;
    @FXML
    private TextArea locationInstallArea;

    @FXML
    private ComboBox cmbAeName,cmbRegion,cmbProvince,cmbKhet,cmbKhwang;

    private String[] regionList = {"01","02"};
    private String[] provinceList;
    private String[] khetList;

    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList aeName = FXCollections.observableArrayList();
        aeName.addAll(customerDB.loadNameEmployee());
        cmbAeName.setItems(aeName);
        cmbAeName.setValue(cmbAeName.getItems().get(0));

        ObservableList region = FXCollections.observableArrayList();
        region.addAll(regionList);
        cmbRegion.setItems(region);
        cmbRegion.setValue(cmbRegion.getItems().get(0));

        ObservableList province = FXCollections.observableArrayList();
        provinceList = new String[]{"กรุงเทพมหานคร"};
        province.addAll(provinceList);
        cmbProvince.setItems(province);
        cmbProvince.setValue(cmbProvince.getItems().get(0));

        ObservableList khet = FXCollections.observableArrayList();
        khetList = new String[]{"จตุจักร", "ลาดพร้าว", "ดอนเมือง"};
        khet.addAll(khetList);
        cmbKhet.setItems(khet);
        cmbKhet.setValue(cmbKhet.getItems().get(0));
    }

    @FXML
    public void regionComboBox(){
        ObservableList province = FXCollections.observableArrayList();
        ObservableList khet = FXCollections.observableArrayList();
        if (cmbRegion.getValue().equals("01")){
            provinceList = new String[]{"กรุงเทพมหานคร"};
            province.removeAll(province);
            province.addAll(provinceList);
            cmbProvince.setItems(province);
            cmbProvince.setValue(cmbProvince.getItems().get(0));

            khetList = new String[]{"จตุจักร", "ลาดพร้าว", "ดอนเมือง"};
            khet.addAll(khetList);
            cmbKhet.setItems(khet);
            cmbKhet.setValue(cmbKhet.getItems().get(0));
        } else {
            provinceList = new String[]{"นนทบุรี","ปทุมธานี"};
            province.removeAll(province);
            province.addAll(provinceList);
            cmbProvince.setItems(province);
            cmbProvince.setValue(cmbProvince.getItems().get(0));

            khetList = new String[]{"เมืองนนทบุรี", "บางใหญ่", "บางบัวทอง", "ปากเกร็ด"};
            khet.addAll(khetList);
            cmbKhet.setItems(khet);
            cmbKhet.setValue(cmbKhet.getItems().get(0));
        }
    }

    @FXML
    public void provinceComboBox(){
        ObservableList khet = FXCollections.observableArrayList();
        if (cmbProvince.getValue().equals("กรุงเทพมหานคร")){
            khetList = new String[]{"จตุจักร", "ลาดพร้าว", "ดอนเมือง"};
            khet.addAll(khetList);
            cmbKhet.setItems(khet);
            cmbKhet.setValue(cmbKhet.getItems().get(0));

        } else if (cmbProvince.getValue().equals("นนทบุรี")){
            khetList = new String[]{"เมืองนนทบุรี", "บางใหญ่", "บางบัวทอง", "ปากเกร็ด"};
            khet.addAll(khetList);
            cmbKhet.setItems(khet);
            cmbKhet.setValue(cmbKhet.getItems().get(0));

        } else {
            khetList = new String[]{"เมืองปทุมธานี", "ธัญบุรี", "ลำลูกกา", "สามโคก"};
            khet.addAll(khetList);
            cmbKhet.setItems(khet);
            cmbKhet.setValue(cmbKhet.getItems().get(0));
        }
    }

    @FXML
    public void cancelBtn(ActionEvent event) {
        this.closeThisWindow(event);
//        this.nameField.setStyle("-fx-background-color: FFC1C1;");
    }

    public void submitBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        checkData.clearQuote(nameField);
        checkData.clearQuote(locationInstallArea);

        boolean isNameFieldCorrect = checkData.isAllCharacter(nameField);
        boolean isBussinessIDFieldCorrect = checkData.isAllNumber(businessIDField,13);
        boolean isCapitalFieldCorrect = checkData.isAllNumberSpec(capitalField, 1000000000);
        boolean isEmployeeFieldCorrect = checkData.isAllNumber(employeeField, 2000);
        boolean isContactTelNumFieldCorrect = checkData.isAllNumber(contaceTelNumField,9);
        boolean isContactFaxFieldCorrect = checkData.isAllNumber(contactFaxField,9);
        boolean isContactFieldCorrect = checkData.isAllNumberSpecLen(contactField,9,10);
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
