package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddRecordPageController {

    @FXML
    private TextField nameField,aeNameField,regionField,locationField,businessIDField;

    @FXML
    public void cancelBtn(ActionEvent event){
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void submitBtn(){

    }

}
