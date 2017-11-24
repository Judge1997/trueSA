package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginPageController {
    @FXML
    private TextField id;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;

    @FXML
    public void initialize(){
        loginBtn.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    if (id.getText().equals("admin") && password.getText().equals("admin")){
                        Button loginBtn = (Button) keyEvent.getSource();
                        Stage mainStage = (Stage) loginBtn.getScene().getWindow();
                        try {
                            loginPageToMainPage(mainStage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        if (this.id.getText().equals("admin") && this.password.getText().equals("admin")){
            Button loginBtn = (Button) event.getSource();
            Stage mainStage = (Stage) loginBtn.getScene().getWindow();
            this.loginPageToMainPage(mainStage);
        }
    }

    public void loginPageToMainPage(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CustomerManage.fxml"));
        Parent root = loader.load();
//        PackageManageController mainController = loader.getController();
        stage.setTitle("trueSA");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.show();
    }
}
