package controllers;

import database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddRequirementPageController {

    private DBConnector requirementDB = DBConnector.getSelf();

    @FXML
    private TextArea area;

    public void submitBtn() throws SQLException, ClassNotFoundException {
        if (area.getText() != null){
//            requirementDB.writeRequirementDB(area.getText());
        }
        area.setStyle("-fx-border-color: red");
    }

    public void clearBtn(){
        area.setText(null);
    }

    public void cancelBtn(ActionEvent event){
        this.closeThisWindow(event);
    }

    private void closeThisWindow(ActionEvent event) {
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
}