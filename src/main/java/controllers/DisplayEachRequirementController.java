package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class DisplayEachRequirementController {

    @FXML
    private TextArea detailArea;

    public void setDetail(String detail){
        detailArea.setText(detail);
    }

    @FXML
    public void okBtn(ActionEvent event){
        this.closeThisWindow(event);
    }

    private void closeThisWindow(ActionEvent event) {
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

}
