package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageController {

    @FXML
    public void logoutBth(ActionEvent event) throws IOException {
        Button loginBtn = (Button) event.getSource();
        Stage loginStage = (Stage) loginBtn.getScene().getWindow();
        this.mainPageToLoginPage(loginStage);
    }

    public void mainPageToLoginPage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
        Parent root = loader.load();
//        LoginPageController loginPageController = loader.getController();
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.show();
    }

}
