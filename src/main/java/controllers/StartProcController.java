package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StartProcController {

    @FXML
    private Button customerBtn;
    @FXML
    private ImageView logoView;

    public void initialize() {
        Image logoTrue = new Image("trueSALoGo.png");
        logoView.setImage(logoTrue);
    }
}
