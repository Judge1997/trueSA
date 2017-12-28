package controllers;

import tools.CheckData;
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
    private TextField nameField, businessIDField, capitalField, khwangField, employeeField, contaceTelNumField, contactFaxField, contactField, contactNameField;
    @FXML
    private TextArea locationInstallArea;

    private boolean isCustomerNameFieldCorrect = false;
    private boolean isBussinessIDFieldCorrect = false;

    @FXML
    private ComboBox cmbAeName,cmbRegion,cmbProvince,cmbKhet;

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
        if (cmbRegion.getValue().equals("01")){
            provinceList = new String[]{"กรุงเทพมหานคร"};
        } else {
            provinceList = new String[]{"นนทบุรี","ปทุมธานี"};
        }
        province.addAll(provinceList);
        cmbProvince.setValue(province.get(0));
        cmbProvince.setItems(province);
    }

    @FXML
    public void provinceComboBox(){
        ObservableList khet = FXCollections.observableArrayList();
        if (cmbProvince.getValue().equals("กรุงเทพมหานคร")){
            khetList = new String[]{"จตุจักร", "ลาดพร้าว", "ดอนเมือง"};
        } else if (cmbProvince.getValue().equals("นนทบุรี")){
            khetList = new String[]{"เมืองนนทบุรี", "บางใหญ่", "บางบัวทอง", "ปากเกร็ด"};
        } else {
            khetList = new String[]{"เมืองปทุมธานี", "ธัญบุรี", "ลำลูกกา", "สามโคก"};
        }
        khet.addAll(khetList);
        cmbKhet.setValue(khet.get(0));
        cmbKhet.setItems(khet);
    }

    @FXML
    public void cancelBtn(ActionEvent event) {
        this.closeThisWindow(event);
//        this.nameField.setStyle("-fx-background-color: FFC1C1;");
    }

    public void submitBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        checkData.clearQuote(nameField);
        checkData.clearQuote(locationInstallArea);
        checkData.clearQuote(khwangField);

        boolean isCapitalFieldCorrect = checkData.isAllNumberIntegerSpecLen(capitalField,1, 9);
        boolean isKhwangFieldCorrect = checkData.isAllCharacter(khwangField);
        boolean isEmployeeFieldCorrect = checkData.isAllNumberIntegerSpecLen(employeeField, 1,5);
        boolean isContactTelNumFieldCorrect = checkData.isAllNumberIntegerSpecLen(contaceTelNumField,9,10);
        boolean isContactFaxFieldCorrect = checkData.isAllNumberIntegerSpecLen(contactFaxField,9,9);
        boolean isContactFieldCorrect = checkData.isAllNumberSpecLen(contactField,9,10);
        boolean isContactNameFieldCorrect = checkData.isAllCharacter(contactNameField);
        checkDupBussinessID();
        checkBtn();

        ArrayList<Boolean> checkList = new ArrayList<Boolean>();
        checkList.add(isCustomerNameFieldCorrect);
        checkList.add(isBussinessIDFieldCorrect);
        checkList.add(isCapitalFieldCorrect);
        checkList.add(isKhwangFieldCorrect);
        checkList.add(isEmployeeFieldCorrect);
        checkList.add(isContactTelNumFieldCorrect);
        checkList.add(isContactFaxFieldCorrect);
        checkList.add(isContactFieldCorrect);
        checkList.add(isContactNameFieldCorrect);

        if (checkData.isAllCorrect(checkList)){
            System.out.println("Save");
            String name = this.nameField.getText();
            String aeName = String.valueOf(this.cmbAeName.getValue());
            String region = String.valueOf(this.cmbRegion.getValue());
            String businessID = this.businessIDField.getText();
            String locationInstall = this.locationInstallArea.getText();//ไม่ต้องเช็ค
            String capital = this.capitalField.getText();
            String province = String.valueOf(this.cmbProvince.getValue());
            String khet = String.valueOf(this.cmbKhet.getValue());
            String khwang = String.valueOf(this.khwangField.getText());
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
            notifyObservers();

        }

    }

    public void checkDupBussinessID() throws SQLException, ClassNotFoundException {
        if (checkData.isAllNumber(businessIDField,13) && checkData.checkDupCustomerBussinessID(businessIDField)){
            isBussinessIDFieldCorrect = true;
        } else {
            isCustomerNameFieldCorrect = false;
        }
    }

    @FXML
    public void checkBtn() throws SQLException, ClassNotFoundException {
        checkData.clearQuote(this.nameField);
        if (checkData.checkDupCustomerName(nameField) && checkData.isNull(nameField)){
            isCustomerNameFieldCorrect = true;
        } else {
            isCustomerNameFieldCorrect = false;
        }
    }

    private void closeThisWindow(ActionEvent event) {
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
