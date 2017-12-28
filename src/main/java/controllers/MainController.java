package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    protected Label userLabel;

    public void makeHeaderWrappable(TableColumn tc){
        Label label = new Label(tc.getText());
        label.setStyle("-fx-padding: 8px;");
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        StackPane stack = new StackPane();
        stack.getChildren().add(label);
        stack.prefWidthProperty().bind(tc.widthProperty().subtract(5));
        label.prefWidthProperty().bind(stack.prefWidthProperty());
        tc.setGraphic(stack);
    }

    @FXML
    public void logoutBtn(ActionEvent event) throws IOException {
        Button loginBtn = (Button) event.getSource();
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StartProc.fxml"));
        Parent window = loader.load();
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
}
