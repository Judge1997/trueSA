package controllers;


import database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Customer;

import java.io.IOException;
import java.sql.SQLException;
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
                    Customer customer = tableView.getSelectionModel().getSelectedItem();
                    if ( customer != null ){
                        editBtn.setDisable(false);
                        deleteBtn.setDisable(false);
                        displayBtn.setDisable(false);
                    }
                }else{
                    Customer customer = tableView.getSelectionModel().getSelectedItem();
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
    public void displayBtn(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        Customer customer = tableView.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayCustomer.fxml"));
        Parent window = loader.load();
        DisplayCustomerPageController displayCustomerPageController = loader.getController();
        displayCustomerPageController.setValue(customer);

        this.goToPage(stage,window,600,571);
    }

    @FXML
    public void reportBtn(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReportPage.fxml"));
        Parent window = loader.load();
        ReportPage reportTotalPricePageController = loader.getController();
        reportTotalPricePageController.setUsername(this.userLabel.getText());
        reportTotalPricePageController.setStatus("Customer");
        this.goToPage(stage,window,400,600);
    }

    @FXML
    public void searchBtn(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String name = searchField.getText();

        ObservableList lst = FXCollections.observableArrayList();
        lst.addAll(customerDB.loadSearchName(name));
        tableView.setItems(lst);

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