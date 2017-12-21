package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StartProcController {

    @FXML
    private Button customerBtn;
    @FXML
    private ImageView logoView;

    public void initialize() {
        Image logoTrue = new Image("trueSALoGo.png");
        logoView.setImage(logoTrue);
    }

    @FXML
    public void customerBtn() throws IOException {
        this.goToLogIn(1);
    }

    @FXML
    public void packageBtn() throws IOException {
        this.goToLogIn(2);
    }

    @FXML
    public void goToLogIn(int Emtype) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
        LoginPageController loginPageController = new LoginPageController();
        Stage stage = new Stage();
        Parent root = loader.load();
        stage.setTitle("TrueSA");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.show();

    }
}
