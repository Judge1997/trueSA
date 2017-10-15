package controllers;

import database.CustomerDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReportAvarageTotalPageController {

    private CustomerDB customerDB = CustomerDB.getSelf();

    @FXML
    private TableView<Customer> tableView;
    @FXML
    private Label totalReport;

    public void initialize() throws SQLException, ClassNotFoundException {
        List<Customer> listFromCustomerDB = customerDB.loadCustonerDB();
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        double totalAvarage = 0;
        for (Customer c : listFromCustomerDB){
            if (c.getAverage() > 0){
                totalAvarage += c.getAverage();
                customers.add(c);
            }
        }
        tableView.setItems(customers);
        totalReport.setText(String.format("%.2f",totalAvarage));
    }

    @FXML
    public void backBtn(ActionEvent event) throws IOException {
        this.backToMainPage(event);
    }

    private void backToMainPage(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainPage.fxml"));
        Parent root = loader.load();
        Button loginBtn = (Button) event.getSource();
        Stage stage = (Stage) loginBtn.getScene().getWindow();
//        MainPageController mainController = loader.getController();
        stage.setTitle("trueSA");
        stage.setScene(new Scene(root, 600, 400));
        stage.setResizable(false);
        stage.show();
    }

}
