package tools;

import database.DBConnector;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Customer;
import models.Package;

import java.sql.SQLException;
import java.util.ArrayList;

public class CheckData {

    private DBConnector customerDB = DBConnector.getSelf();

    public boolean isAllNumberIntegerSpecLen(TextField field,int start,int end){
        boolean isCorrect = true;
        if (field.getText().equals("") || !(field.getText().length() >= start && field.getText().length() <= end) || Double.parseDouble(field.getText()) <= 0){
            isCorrect = false;
            field.setStyle("-fx-background-color: pink");
            return isCorrect;
        } else {
            for (int i = 0; i < field.getText().length(); i++) {
                if (isCorrect) {
                    if ((field.getText().charAt(i) + "").matches("[0-9]")) {
                    } else {
                        isCorrect = false;
                        field.setStyle("-fx-background-color: pink");
                        return isCorrect;
                    }
                }
            }
        }
        field.setStyle("-fx-background-color: limegreen");
        return isCorrect;
    }

    public boolean isAllNumber(TextField field) {
        boolean isCorrect = true;
        if (field.getText().equals("") || Integer.parseInt(field.getText()) <= 0){
            isCorrect = false;
            field.setStyle("-fx-background-color: pink");
            return isCorrect;
        } else {
            for (int i = 0; i < field.getText().length(); i++) {
                if (isCorrect) {
                    if ((field.getText().charAt(i) + "").matches("[0-9.]")) {
                    } else {
                        isCorrect = false;
                        field.setStyle("-fx-background-color: pink");
                        return isCorrect;
                    }
                }
            }
        }
        field.setStyle("-fx-background-color: limegreen");
        return isCorrect;
    }

    public boolean isAllNumberSpec(TextField field, double len){
        boolean isCorrect = true;
        if (field.getText().equals("") || Integer.parseInt(field.getText()) <= 0 || Integer.parseInt(field.getText()) > len){
            isCorrect = false;
            field.setStyle("-fx-background-color: pink");
            return isCorrect;
        } else {
            for (int i = 0; i < field.getText().length(); i++) {
                if (isCorrect) {
                    if ((field.getText().charAt(i) + "").matches("[0-9.]")) {
                    } else {
                        isCorrect = false;
                        field.setStyle("-fx-background-color: pink");
                        return isCorrect;
                    }
                }
            }
        }
        field.setStyle("-fx-background-color: limegreen");
        return isCorrect;
    }

    public boolean isAllNumber(TextField field, int len) {
        boolean isCorrect = true;
        if (field.getText().equals("") || field.getText().length() != len){
            isCorrect = false;
            field.setStyle("-fx-background-color: pink");
            return isCorrect;
        } else {
            for (int i = 0; i < field.getText().length(); i++) {
                if (isCorrect) {
                    if ((field.getText().charAt(i) + "").matches("[0-9.]")) {
                    } else {
                        isCorrect = false;
                        field.setStyle("-fx-background-color: pink");
                        return isCorrect;
                    }
                }
            }
        }
        field.setStyle("-fx-background-color: limegreen");
        return isCorrect;
    }

    public boolean isAllNumberSpecLen(TextField field, int start , int end){
        boolean isCorrect = true;
        if (field.getText().equals("") || !(field.getText().length() >= start && field.getText().length() <= end) || Double.parseDouble(field.getText()) <= 0){
            isCorrect = false;
            field.setStyle("-fx-background-color: pink");
            return isCorrect;
        } else {
            for (int i = 0; i < field.getText().length(); i++) {
                if (isCorrect) {
                    if ((field.getText().charAt(i) + "").matches("[0-9.]")) {
                    } else {
                        isCorrect = false;
                        field.setStyle("-fx-background-color: pink");
                        return isCorrect;
                    }
                }
            }
        }
        field.setStyle("-fx-background-color: limegreen");
        return isCorrect;
    }

    public boolean isAllCharacter(TextField field) {
        boolean isCorrect = true;
        if (field.getText().equals("")){
            isCorrect = false;
            field.setStyle("-fx-background-color: pink");
            return isCorrect;
        } else {
            for (int i = 0; i < field.getText().length(); i++) {
                if (isCorrect) {
                    if (!field.getText().substring(i, i + 1).matches("[a-zA-Z ก-ฮะา *่ *้ *๊ *๋ *็ *ั *ิ *ี *ึ *ื *ุ *ูเฤฦๅ]")){
                        isCorrect = false;
                        field.setStyle("-fx-background-color: pink");
                        return isCorrect;
                    }
                }
            }
        }
        field.setStyle("-fx-background-color: limegreen");
        return isCorrect;
    }

    public boolean isAllCharacter(TextArea area) {
        boolean isCorrect = true;
        for (int i = 0; i < area.getText().length(); i++) {
            if (isCorrect) {
                if ((area.getText().charAt(i) + "").matches("[a-zA-Z]")) {
                } else {
                    isCorrect = false;
                    area.setStyle("-fx-background-color: pink");
                    return isCorrect;
                }
            }
        }
        area.setStyle("-fx-background-color: limegreen");
        return isCorrect;
    }

    public boolean isAllCorrect(ArrayList<Boolean> checkList) {
        for (boolean i : checkList){
            System.out.println(i);
            if (i == false){
                return false;
            }
        }
        return true;
    }

    public boolean checkDupCustomerName(TextField field) throws SQLException, ClassNotFoundException {
        for (Customer i : this.customerDB.loadCustonerDB()){
            if (i.getName().equals(field.getText())){
                field.setStyle("-fx-background-color: pink");
                return false;
            }
        }

        field.setStyle("-fx-background-color: limegreen");
        return true;
    }

    public boolean checkDupCustomerBussinessID(TextField field) throws SQLException, ClassNotFoundException {
        for (Customer i : this.customerDB.loadCustonerDB()){
            if (i.getBusinessId().equals(field.getText())){
                field.setStyle("-fx-background-color: pink");
                return false;
            }
        }

        field.setStyle("-fx-background-color: limegreen");
        return true;
    }

    public boolean checkDupPackageName(TextField field) throws SQLException, ClassNotFoundException {
        for (Package i : this.customerDB.loadPackageDB()){
            if (i.getName().equals(field.getText())){
                field.setStyle("-fx-background-color: pink");
                return false;
            }
        }

        field.setStyle("-fx-background-color: green");
        return true;
    }

    public boolean isNull(TextField field){
        if (field.getText().equals("")){
            field.setStyle("-fx-background-color: pink");
            return false;
        }
        field.setStyle("-fx-background-color: limegreen");
        return true;
    }

    public boolean isNull(TextArea area){
        boolean isCorrect = false;
        if (area.getText().equals("")){
            isCorrect = true;
            area.setStyle("-fx-background-color: pink");
            return isCorrect;
        }
        area.setStyle("-fx-background-color: limegreen");
        return isCorrect;
    }

    public void clearQuote(TextField field){
        field.setText(field.getText().replaceAll("'",""));
        field.setText(field.getText().replaceAll("\"",""));
    }

    public void clearQuote(TextArea area) {
        area.setText(area.getText().replaceAll("'",""));
        area.setText(area.getText().replaceAll("\"",""));
    }
}
