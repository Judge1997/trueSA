package controllers;

import database.DBConnector;
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
import models.CustomerPackage;
import models.Package;
import models.ReportTotalPrice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReportAvarageTotalPageController {

    private DBConnector customerDB = DBConnector.getSelf();

    @FXML
    private TableView<ReportTotalPrice> tableView;
    @FXML
    private Label totalReport;

//    public void initialize() throws SQLException, ClassNotFoundException {
//        List<Customer> listFromCustomerDB = customerDB.loadCustonerDB();
//        ObservableList<Customer> customers = FXCollections.observableArrayList();
//        double totalCost = 0;
//        for (Customer c : listFromCustomerDB){
//            if (c.getPacketCost() > 0){
//                totalCost += c.getPacketCost();
//                customers.add(c);
//            }
//        }
//        tableView.setItems(customers);
//        totalReport.setText(String.format("%.2f",totalCost));
//    }

    public void initialize() throws SQLException, ClassNotFoundException {
        ObservableList<Package> packages = FXCollections.observableArrayList();

        ObservableList<CustomerPackage> customerPackages = FXCollections.observableArrayList();

        ObservableList<Customer> customers = FXCollections.observableArrayList();

        customers.addAll(customerDB.loadCustonerDB());

        customerPackages.addAll(customerDB.loadAllCustomerPackage());

        packages.addAll(customerDB.loadPacketDB());

        ObservableList<ReportTotalPrice> reportTotalPrices = FXCollections.observableArrayList();

        double totalPrice = 0;

        for (CustomerPackage cp : customerPackages){
            for (Package p : packages){
                if (cp.getIdPackage() == p.getId()){
                    for (Customer c : customers){
                        if (cp.getIdCustomer() == c.getId()){
                            ReportTotalPrice reportTotalPrice = new ReportTotalPrice(c.getId(), c.getName(), Double.parseDouble(p.getPrice()));
                            if (this.contain(reportTotalPrices, reportTotalPrice) == false){
                                reportTotalPrices.add(reportTotalPrice);
                            }
                        }
                    }
                }
            }
        }

        tableView.setItems(reportTotalPrices);

        for (ReportTotalPrice r : tableView.getItems()){
            totalPrice += r.getPrice();
        }

        totalReport.setText(String.format("%.2f",totalPrice));
    }

    private boolean contain(ObservableList<ReportTotalPrice> reportTotalPrices, ReportTotalPrice reportTotalPrice){
        for (ReportTotalPrice r : reportTotalPrices){
            if (r.getName().equals(reportTotalPrice.getName())){
                r.addPrice(reportTotalPrice.getPrice());
                return true;
            }
        }
        return false;
    }

    @FXML
    public void backBtn(ActionEvent event) throws IOException {
        this.backToMainPage(event);
    }

    private void backToMainPage(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CustomerManage.fxml"));
        Parent root = loader.load();
        Button loginBtn = (Button) event.getSource();
        Stage stage = (Stage) loginBtn.getScene().getWindow();
//        PackageManageController mainController = loader.getController();
        stage.setTitle("trueSA");
        stage.setScene(new Scene(root, 850, 720));
        stage.setResizable(false);
        stage.show();
    }

}
