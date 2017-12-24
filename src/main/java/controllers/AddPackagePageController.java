package controllers;

import check.CheckData;
import database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Package;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

public class AddPackagePageController extends Observable {

    private DBConnector packageDB = DBConnector.getSelf();
    private CheckData checkData = new CheckData();
    private boolean isPackageNameFieldCorrect = false;

    @FXML
    private TextField packageNameField, priceField, internetField, voiceField, dataField, mobileQuantityField, mobileSpeedField, mobileTimesField;
    @FXML
    private ComboBox tvsComboBox;

    public void initialize(){
        ObservableList observableList = FXCollections.observableArrayList();
        observableList.add("100");
        observableList.add("200");
        observableList.add("300");
        tvsComboBox.setItems(observableList);
        tvsComboBox.setValue(tvsComboBox.getItems().get(0));
    }

    @FXML
    public void submitBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        this.checkBtn();
        boolean isPriceFieldCorrect = checkData.isAllNumber(priceField);
        boolean isInternetFieldCorrect = checkData.isAllNumber(internetField);
        boolean isVoiceFieldCorrect = checkData.isAllNumber(voiceField);
        boolean isDataFieldCorrect = checkData.isAllNumber(dataField);
        boolean isMobileQuantityFieldCorrect = checkData.isAllNumber(mobileQuantityField);
        boolean isMobileSpeedFieldCorrect = checkData.isAllNumber(mobileSpeedField);
        boolean isMobileTimesFieldCorrect = checkData.isAllNumber(mobileTimesField);

        ArrayList<Boolean> checkList = new ArrayList<Boolean>();
        checkList.add(isPackageNameFieldCorrect);
        checkList.add(isPriceFieldCorrect);
        checkList.add(isInternetFieldCorrect);
        checkList.add(isVoiceFieldCorrect);
        checkList.add(isDataFieldCorrect);
        checkList.add(isMobileQuantityFieldCorrect);
        checkList.add(isMobileSpeedFieldCorrect);
        checkList.add(isMobileTimesFieldCorrect);

        if (checkData.isAllCorrect(checkList)){
            String name = this.packageNameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int internet = Integer.parseInt(internetField.getText());
            int voice = Integer.parseInt(voiceField.getText());
            int data = Integer.parseInt(dataField.getText());
            int mobileQuantity = Integer.parseInt(mobileQuantityField.getText());
            int mobileSpeed = Integer.parseInt(mobileSpeedField.getText());
            int mobileTimes = Integer.parseInt(mobileTimesField.getText());
            int tvs = Integer.parseInt(String.valueOf(tvsComboBox.getValue()));

            Package packages = new Package(0, name, price, internet, voice, data, mobileQuantity, mobileSpeed, mobileTimes, tvs,"Active");

            this.packageDB.writePacketDB(packages);

            this.closeThisWindow(event);

            setChanged();
            notifyObservers();
        }
    }

    @FXML
    public void checkBtn() throws SQLException, ClassNotFoundException {
        checkData.clearQuote(this.packageNameField);
        if (checkData.checkDupPackage(packageNameField) || checkData.isNull(packageNameField)){
            isPackageNameFieldCorrect = false;
        } else {
            isPackageNameFieldCorrect = true;
        }
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

}
