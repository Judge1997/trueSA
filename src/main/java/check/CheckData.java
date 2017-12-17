package check;

import database.DBConnector;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Customer;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class CheckData {

    private DBConnector customerDB = DBConnector.getSelf();

    public boolean isAllNumber(TextField field) {
        boolean isCorrect = true;
        if (field.getText().equals("")){
            isCorrect = false;
            field.setStyle("-fx-border-color: red");
            return isCorrect;
        } else {
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
        }
        field.setStyle("");
        return isCorrect;
    }

    public boolean isAllNumber(TextField field, int len) {
        boolean isCorrect = true;
        if (field.getText().equals("") || field.getText().length() != len){
            isCorrect = false;
            field.setStyle("-fx-border-color: red");
            return isCorrect;
        } else {
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
        }
        field.setStyle("");
        return isCorrect;
    }

    public boolean isAllCharacter(TextField field) {
        boolean isCorrect = true;
        if (field.getText().equals("")){
            isCorrect = false;
            field.setStyle("-fx-border-color: red");
            return isCorrect;
        } else {
            for (int i = 0; i < field.getText().length(); i++) {
                if (isCorrect) {
                    if (!field.getText().substring(i, i + 1).matches("[a-zA-Z ก-ฮะา *่ *้ *๊ *๋ *็ *ั *ิ *ี *ึ *ื *ุ *ูเฤฦๅ]")){
                        isCorrect = false;
                        field.setStyle("-fx-border-color: red");
                        return isCorrect;
                    }
                }
            }
        }
        field.setStyle("");
        return isCorrect;
    }

    public boolean isAllCharacter(TextArea area) {
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

    public boolean isAllCorrect(ArrayList<Boolean> checkList) {
        for (boolean i : checkList){
            if (i == false){
                return false;
            }
        }
        return true;
    }

    public boolean checkDup(String name) throws SQLException, ClassNotFoundException {
        for (Customer i : this.customerDB.loadCustonerDB()){
            if (i.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean isNull(TextField field){
        boolean isCorrect = true;
        if (field.getText().equals("")){
            isCorrect = false;
            field.setStyle("-fx-border-color: red");
            return isCorrect;
        }
        field.setStyle("");
        return isCorrect;
    }

    public boolean isNull(TextArea area){
        boolean isCorrect = true;
        if (area.getText().equals("")){
            isCorrect = false;
            area.setStyle("-fx-border-color: red");
            return isCorrect;
        }
        area.setStyle("");
        return isCorrect;
    }
}
