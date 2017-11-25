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
                    if (id.getText().equals("customer") && password.getText().equals("customer")){
                        Button loginBtn = (Button) keyEvent.getSource();
                        Stage mainStage = (Stage) loginBtn.getScene().getWindow();
                        try {
                            loginPageToMainPage(mainStage,"/CustomerManage.fxml","customer",600,400);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (id.getText().equals("package") && password.getText().equals("package")){
                        Button loginBtn = (Button) keyEvent.getSource();
                        Stage mainStage = (Stage) loginBtn.getScene().getWindow();
                        try {
                            loginPageToMainPage(mainStage,"/PackageManage.fxml","package",400,400);
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
        if (this.id.getText().equals("customer") && this.password.getText().equals("customer")){
            Button loginBtn = (Button) event.getSource();
            Stage mainStage = (Stage) loginBtn.getScene().getWindow();
            this.loginPageToMainPage(mainStage,"/CustomerManage.fxml", "customer",600,400);
        } else if (this.id.getText().equals("package") && this.password.getText().equals("package")){
            Button loginBtn = (Button) event.getSource();
            Stage mainStage = (Stage) loginBtn.getScene().getWindow();
            this.loginPageToMainPage(mainStage,"/PackageManage.fxml", "package",400,400);
        }
    }

    @FXML
    public void loginPageToMainPage(Stage stage,String fxml,String user,int width,int height) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.setUser(user);
        stage.setTitle("trueSA");
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(false);
        stage.show();
    }
}
