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
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;

public class MainPageController implements Observer {

    private CustomerDB customerDB = CustomerDB.getSelf();
//    AddRecordPageController addRecordPageController;

    @FXML
    private TableView<Customer> tableView;

    @FXML
    public void refresh() throws SQLException, ClassNotFoundException {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        customers.addAll(customerDB.loadCustonerDB());
        tableView.setItems(customers);
    }

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        refresh();
    }

    @FXML
    public void logoutBth(ActionEvent event) throws IOException {
        Button loginBtn = (Button) event.getSource();
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
        this.goToPage(stage,loader,400,600);
    }

    public void goToPage(Stage stage,FXMLLoader loader,int height,int width) throws IOException {
        Parent root = loader.load();
//        LoginPageController loginPageController = loader.getController();
        stage.setTitle("trueSA");
        stage.setScene(new Scene(root, width, height));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void addBtn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddRecordPage.fxml"));
//        addRecordPageController = loader.getController();
//        addRecordPageController.addObserver(this);
        this.goToPage(stage,loader,720,1280);
    }

    @FXML
    public void editBtn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditRecordPage.fxml"));
        this.goToPage(stage,loader,720,1280);
    }

//    @FXML
//    public void searchBtn(ActionEvent event) throws IOException {
//        Button searchBtn = (Button) event.getSource();
//        Stage stage = (Stage) searchBtn.getScene().getWindow();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchPage.fxml"));
//        this.goToPage(stage,loader,400,600);
//    }

    @FXML
    public void reportBtn(ActionEvent event) throws IOException {
        Button loginBtn = (Button) event.getSource();
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReportAvarageTotalPage.fxml"));
        this.goToPage(stage,loader,400,600);
    }

    @Override
    public void update(Observable o, Object customer) {
//        if (o instanceof AddRecordPageController){
//            try {
//                this.customerDB.writeCustonerDB((Customer) customer);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        ObservableList<Customer> customers = FXCollections.observableArrayList();
//        try {
//            customers.addAll(customerDB.loadCustonerDB());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        tableView.setItems(customers);
    }

}
