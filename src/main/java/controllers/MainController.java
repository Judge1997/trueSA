package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    protected Label userLabel;

    protected int emty;

    @FXML
    public void logoutBth(ActionEvent event) throws IOException {
        Button loginBtn = (Button) event.getSource();
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
        Parent window = loader.load();
        LoginPageController loginPageController = loader.getController();
        loginPageController.setEmty(emty);
        this.goToPage(stage,window,400,600);
    }

    public void goToPage(Stage stage, Parent loader, int height, int width) throws IOException {
        stage.setTitle("trueSA");
        stage.setScene(new Scene(loader, width, height));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void setUser(String user) {
        this.userLabel.setText(user);
    }

    public void setEmty(int emty) {
        this.emty = emty;
    }
}
