package controllers;

import database.DBConnector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class LoginPageController {

    private DBConnector customerDB = DBConnector.getSelf();
    @FXML
    private TextField id;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginBtn;
    @FXML
    private Label responseLabel;

    private int emty;

    @FXML
    public void initialize(){
        loginBtn.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    try {
                        String result = customerDB.checkUserAndPassword(id.getText(), password.getText(), emty);
                        String[] resultSplit = result.split(" ");
                        if (!result.equals("Id or password is wrong")){
                            if (resultSplit[1].equals("CustomerManager")){
                                Button loginBtn = (Button) keyEvent.getSource();
                                Stage mainStage = (Stage) loginBtn.getScene().getWindow();
                                try {
                                    loginPageToMainPage(mainStage,"/CustomerManage.fxml",resultSplit[0],850,720);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else if (resultSplit[1].equals("PackageManager")){
                                Button loginBtn = (Button) keyEvent.getSource();
                                Stage mainStage = (Stage) loginBtn.getScene().getWindow();
                                try {
                                    loginPageToMainPage(mainStage,"/PackageManage.fxml",resultSplit[0],400,450);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            responseLabel.setText(result);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    public void login(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String result = customerDB.checkUserAndPassword(id.getText(), password.getText(), emty);
        String[] resultSplit = result.split(" ");
        if (!result.equals("Id or password is wrong")){
            if (resultSplit[1].equals("CustomerManager")){
                Button loginBtn = (Button) event.getSource();
                Stage mainStage = (Stage) loginBtn.getScene().getWindow();
                loginPageToMainPage(mainStage,"/CustomerManage.fxml",resultSplit[0],850,720);
            } else if (resultSplit[1].equals("PackageManager")){
                Button loginBtn = (Button) event.getSource();
                Stage mainStage = (Stage) loginBtn.getScene().getWindow();
                loginPageToMainPage(mainStage,"/PackageManage.fxml",resultSplit[0],400,450);
            }
        } else {
            responseLabel.setText(result);
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

    @FXML
    public void backBtn(ActionEvent event) throws IOException {
        Button loginBtn = (Button) event.getSource();
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StartProc.fxml"));
        Parent window = loader.load();
        stage.setTitle("trueSA");
        stage.setScene(new Scene(window, 600, 400));
        stage.setResizable(false);
        stage.show();
    }

    public void setEmty(int emty) {
        this.emty = emty;
    }
}
