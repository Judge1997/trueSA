package controllers;

import database.DBConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import models.Requirement;
import tools.GenTime;

import java.sql.SQLException;

public class AddRequirementPageController {

    private DBConnector requirementDB = DBConnector.getSelf();

    private int idCustomer;

    @FXML
    private TextArea area;

    @FXML
    public void submitBtn(ActionEvent event) throws SQLException, ClassNotFoundException {
        GenTime time = new GenTime();
        if (!area.getText().equals("")){
            Requirement requirement = new Requirement(0,idCustomer,area.getText(),time.getTime());
            requirementDB.writeRequirementDB(requirement);
            this.closeThisWindow(event);
        } else {
            area.setStyle("-fx-border-color: red");
        }
    }

    public void clearBtn(){
        area.setText("");
    }

    public void cancelBtn(ActionEvent event){
        this.closeThisWindow(event);
    }

    private void closeThisWindow(ActionEvent event) {
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }
}
