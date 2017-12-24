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
    public void customerBtn(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        this.goToLogIn(stage, 1, "Customer Manager");
    }

    @FXML
    public void packageBtn(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        this.goToLogIn(stage, 2, "Package Manager");
    }

    @FXML
    public void goToLogIn(Stage stage, int empType, String role) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
        Parent root = loader.load();
        LoginPageController loginPageController = loader.getController();
        loginPageController.setRoleLabel(role);
        loginPageController.setEmty(empType);
        stage.setTitle("TrueSA");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.show();

    }
}
