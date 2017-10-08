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
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        this.goToPage(stage,"/LoginPage.fxml",400,600);
    }

    public void goToPage(Stage stage,String namePage,int height,int width) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(namePage));
        Parent root = loader.load();
//        LoginPageController loginPageController = loader.getController();
        stage.setTitle("trueSA");
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void addBtn(ActionEvent event) throws IOException {
        Stage addStage = new Stage();
        this.goToPage(addStage,"/AddRecordPage.fxml",720,1280);
    }

    @FXML
    public void editBtn(ActionEvent event) throws IOException {
        Stage addStage = new Stage();
        this.goToPage(addStage,"/EditRecordPage.fxml",720,1280);
    }

    @FXML
    public void searchBtn(ActionEvent event) throws IOException {
        Button searchBtn = (Button) event.getSource();
        Stage stage = (Stage) searchBtn.getScene().getWindow();
        this.goToPage(stage,"/SearchPage.fxml",400,600);
    }

}
