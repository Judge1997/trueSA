package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class AddRequirementPageController {
    @FXML
    private TextArea area;

    public void submitBtn(){

    }

    public void clearBtn(){
        area.setText(null);
    }

    public void cancelBtn(){
        
    }
}
