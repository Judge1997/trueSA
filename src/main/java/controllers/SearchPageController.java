package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SearchPageController {

    @FXML
    private ObservableList<String> allInfo = FXCollections.observableArrayList("SUBSCRIBER No.","CUSTOMER_NAME","BU_2017","AE2017_NAME","SEGMENT_2017","REGION"
            ,"Locaation Install","CUSTOMER_TYPE","APR_2017","BUS_ID_DBD","CAPITAL","PROVINCE","KHET","KHWANG","EMPLOYEE","CONTACT_TELNUM1","CONTACT_FAX","CONTACT1","CONTACT NAME1"
            ,"AVERAGE","VOICE","INTERNET","DATA","MOBILE","TVS");

    @FXML
    private ComboBox searchComboBox;

    @FXML
    private void initialize(){
        searchComboBox.setItems(allInfo);
    }

    @FXML
    public void backBtn(ActionEvent event) throws IOException {
        Button backBtn = (Button) event.getSource();
        Stage stage = (Stage) backBtn.getScene().getWindow();
        this.goToPage(stage, "/CustomerManage.fxml",400,600);
    }

    public void goToPage(Stage stage,String namePage,int height,int width) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(namePage));
        Parent root = loader.load();
        stage.setTitle("trueSA");
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(false);
        stage.show();
    }
}
