package controllers;

import check.CheckData;
import database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Package;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;

public class AddPackagePageController extends Observable {

    private DBConnector packageDB = DBConnector.getSelf();
    private CheckData checkData = new CheckData();

    @FXML
    private TextField packageNameField, priceField, internetField, voiceField, dataField;
    @FXML
    private TextArea mobileField, tvsField;

    @FXML
    public void submitBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isPackageNameFieldCorrect = checkData.isNull(packageNameField);
        boolean isPriceFieldCorrect = checkData.isAllNumber(priceField);
        boolean isInternetFieldCorrect = checkData.isNull(internetField);
        boolean isVoiceFieldCorrect = checkData.isNull(voiceField);
        boolean isDataFieldCorrect = checkData.isNull(dataField);
        boolean isMobileAreaCorrect = checkData.isNull(mobileField);
        boolean isTVSFieldCorrect = checkData.isNull(tvsField);

        ArrayList<Boolean> checkList = new ArrayList<Boolean>();
        checkList.add(isPackageNameFieldCorrect);
        checkList.add(isPriceFieldCorrect);
        checkList.add(isInternetFieldCorrect);
        checkList.add(isVoiceFieldCorrect);
        checkList.add(isDataFieldCorrect);
        checkList.add(isMobileAreaCorrect);
        checkList.add(isTVSFieldCorrect);

        if (checkData.isAllCorrect(checkList)){
            String name = this.packageNameField.getText();
            double price = Double.parseDouble(priceField.getText());
            String internet = internetField.getText();
            String voice = voiceField.getText();
            String data = dataField.getText();
            String mobile = mobileField.getText();
            String tvs = tvsField.getText();

            Package packages = new Package(0, name, price, internet, voice, data, mobile, tvs);

            this.packageDB.writePacketDB(packages);

            this.closeThisWindow(event);

            setChanged();
            notifyObservers();
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
