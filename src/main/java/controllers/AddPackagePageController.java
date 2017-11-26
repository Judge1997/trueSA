package controllers;

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

    @FXML
    private TextField packageNameField, priceField, internetField, voiceField, dataField;
    @FXML
    private TextArea mobileField, tvsField;

    @FXML
    public void submitBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isPackageNameField = isAllCharacter(packageNameField);

        ArrayList<Boolean> checkList = new ArrayList<Boolean>();
        checkList.add(isPackageNameField);

        if (isAllCorrect(checkList)){
            String name = packageNameField.getText();
            if (priceField.getText().equals("")){
                priceField.setText("0");
            }
            double price = Double.parseDouble(String.format("%.2f",priceField.getText()));
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
