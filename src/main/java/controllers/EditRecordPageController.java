package controllers;

import check.CheckData;
import database.DBConnector;
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
    private TextField nameField, aeNameField, regionField, businessIDField, capitalField, provinceField, khetField, khwangField, employeeField, contaceTelNumField,contactFaxField, contactField, contactNameField;
    @FXML
    private ComboBox<String> cmbAeName = new ComboBox<>(),cmbRegion = new ComboBox<>(),cmbProvince = new ComboBox<>(),cmbKhet = new ComboBox<>(),cmbKhwang = new ComboBox<>();
    @FXML
    private TextArea locationInstallArea;
    @FXML
    private Label subscriberNumber;
    private Customer customer;

    private String[] aeNameList = {"สมครี","สมยศ","สมหมาย","สมบัติ","สมชาย","สมหญิง"};
    private String[] regionList = {"bk01","bk02","bk03","kb01","kb02","kcb01","kcb02","kcb03"};
    private String[] provinceList = {"กรุงเทพมหานคร","กระบี่","กาญจนบุรี","กาฬสินธุ์","กำแพงเพชร","ขอนแก่น","จันทบุรี","ฉะเชิงเทรา","ชลบุรี","ชัยนาท","ชัยภูมิ","ชุมพร","เชียงราย","เชียงใหม่","ตรัง","ตราด","ตาก","นครนายก","นครปฐม","นครพนม","นครราชสีมา "
    ,"นครศรีธรรมราช","นครสวรรค์","นนทบุรี","นราธิวาส","น่าน","บึงกาฬ","บุรีรัมย์","ปทุมธานี","ประจวบคีรีขันธ์","ปราจีนบุรี","ปัตตานี","พระนครศรีอยุธยา","พังงา","พัทลุง","พิจิตร","พิษณุโลก","เพชรบุรี","เพชรบูรณ์","แพร่","พะเยา","ภูเก็ต","มหาสารคาม","มุกดาหาร","แม่ฮ่องสอน"
    ,"ยะลา","ยโสธร","ร้อยเอ็ด","ระนอง","ระยอง","ราชบุรี","ลพบุรี","ลำปาง","ลำพูน","เลย","ศรีสะเกษ","สกลนคร","สงขลา","สตูล","สมุทรปราการ","สมุทรสงคราม","สมุทรสาคร","สระแก้ว","สระบุรี","สิงห์บุรี","สุโขทัย","สุพรรณบุรี","สุราษฎร์ธานี","สุรินทร์","หนองคาย","หนองบัวลำภู","อ่างทอง"
    ,"อุดรธานี","อุทัยธานี","อุตรดิตถ์","อุบลราชธานี","อำนาจเจริญ"};

    public void initialize(){
        cmbAeName.getItems().addAll(aeNameList);
        cmbRegion.getItems().addAll(regionList);
        cmbProvince.getItems().addAll(provinceList);
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
            int id = Integer.parseInt(subscriberNumber.getText());
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

}
