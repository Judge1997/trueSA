package controllers;


import database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Customer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

public class CustomerManageController extends MainController implements Observer{

    private DBConnector customerDB = DBConnector.getSelf();

    @FXML
    private TableView<Customer> tableView;
    @FXML
    private TextField searchField;
    @FXML
    private Button editBtn, deleteBtn, displayBtn;

    @FXML
    public void refresh() throws SQLException, ClassNotFoundException {
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        customers.addAll(customerDB.loadCustonerDB());
        tableView.setItems(customers);
    }

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {
        this.refresh();
        editBtn.setDisable(true);
        deleteBtn.setDisable(true);
        displayBtn.setDisable(true);

        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY) {
                    Customer customer = (Customer) tableView.getSelectionModel().getSelectedItem();
                    if ( customer != null ){
                        editBtn.setDisable(false);
                        deleteBtn.setDisable(false);
                        displayBtn.setDisable(false);
                    }
                }else{
                    Customer customer = (Customer) tableView.getSelectionModel().getSelectedItem();
                    if ( customer != null ){
                        editBtn.setDisable(false);
                        deleteBtn.setDisable(false);
                        displayBtn.setDisable(false);
                    }
                }
            }
        });
    }

    @FXML
    public void addBtn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddRecordPage.fxml"));
        Parent window = loader.load();
        AddRecordPageController addRecordPageController = loader.getController();
        addRecordPageController.addObserver(this);

        this.goToPage(stage,window,720,850);
    }

    @FXML
    public void editBtn(ActionEvent event) throws IOException {
        Customer customer = tableView.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/EditRecordPage.fxml"));
        Parent window = loader.load();
        EditRecordPageController editRecordPageController = loader.getController();
        editRecordPageController.setValue(customer);
        editRecordPageController.addObserver(this);
        this.goToPage(stage,window,720,850);
        this.editBtn.setDisable(true);
        this.deleteBtn.setDisable(true);
        displayBtn.setDisable(true);
    }

    @FXML
    public void deleteBtn() throws SQLException, ClassNotFoundException {
        if (tableView.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " +
                    tableView.getSelectionModel().getSelectedItem().getName() + " ?",
                    ButtonType.OK, ButtonType.CANCEL);
            Optional optional = alert.showAndWait();
            if (optional.get() == ButtonType.OK) {
                Customer customer = tableView.getSelectionModel().getSelectedItem();
                customerDB.deleteCustomerDB(customer.getId());
                this.refresh();
                this.editBtn.setDisable(true);
                this.deleteBtn.setDisable(true);
                displayBtn.setDisable(true);
            }
        }
    }

    @FXML
    public void displayBtn(ActionEvent event) throws IOException {
        Customer customer = tableView.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayCustomer.fxml"));
        Parent window = loader.load();
        DisplayCustomerPageController displayCustomerPageController = loader.getController();
        displayCustomerPageController.setValue(customer);

        this.goToPage(stage,window,600,571);
    }

    @FXML
    public void addRequirementBtn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddRequirementPage.fxml"));
        Parent window = loader.load();

        this.goToPage(stage,window,300,400);
    }


    @FXML
    public void reportBtn(ActionEvent event) throws IOException {
        Button loginBtn = (Button) event.getSource();
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReportAvarageTotalPage.fxml"));
        Parent window = loader.load();
        this.goToPage(stage,window,400,600);
    }

    @FXML
    public void displayRequirementBtn(ActionEvent event){

    }

    @FXML
    public void searchBtn(ActionEvent event) throws IOException {
        String name = searchField.getText();
        for (Customer i : tableView.getItems()){
            if (i.getName().equals(name)){
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayCustomer.fxml"));
                Parent window = loader.load();
                DisplayCustomerPageController displayCustomerPageController = loader.getController();
                displayCustomerPageController.setValue(i);

                this.goToPage(stage,window,600,571);
            }
        }
        searchField.setText("");
    }

    @Override
    public void update(Observable o, Object customer) {
        try {
            this.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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