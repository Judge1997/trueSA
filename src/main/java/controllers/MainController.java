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

    @FXML
    public void logoutBth(ActionEvent event) throws IOException {
        Button loginBtn = (Button) event.getSource();
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
        this.goToPage(stage,loader,400,600);
    }

    public void goToPage(Stage stage,FXMLLoader loader,int height,int width) throws IOException {
        Parent root = loader.load();
        stage.setTitle("trueSA");
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void setUser(String user) {
        System.out.println(123);
        this.userLabel.setText(user);
    }

}
