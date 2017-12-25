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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import models.*;
import models.Package;

import javax.xml.soap.Text;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReportPage {

    private DBConnector customerDB = DBConnector.getSelf();

    private String username;

    private String status;

    private ReportPrinter printer;

    private String totalEach, totalAll;

    @FXML
    private TableView<ReportTotalPrice> tableView;
    @FXML
    private Label totalReport, titleLabel;

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

    public void initialize() throws SQLException, ClassNotFoundException, IOException {
        printer = new ReportPrinter();
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
        MainController mainController = loader.getController();
        mainController.setUser(this.username);
        Button loginBtn = (Button) event.getSource();
        Stage stage = (Stage) loginBtn.getScene().getWindow();
//        PackageManageController mainController = loader.getController();
        stage.setTitle("trueSA");
        stage.setScene(new Scene(root, 850, 720));
        stage.setResizable(false);
        stage.show();
    }

    public String getPriceString(double price) {
        String str = String.format("%.2f", price);
        String priceStr = str.substring(str.length()-2,str.length());
        int count = 0;
        for (int i = str.length()-3 ; i >= 0 ; i--){
            String subStr = str.substring(i,i+1);
            if(count == 3 && i != 0){
                subStr = ","+subStr;
                count = 0;
            }
            priceStr = subStr+priceStr;
            count++;
        }

        return priceStr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setStatus(String status) throws SQLException, ClassNotFoundException {
        this.status = status;
        titleLabel.setFont(Font.font ("Verdana", FontWeight.BOLD, 18));
        titleLabel.setTextFill(Color.rgb(255,94,1));
        titleLabel.setText(this.status);
        this.render();
    }

    private void render() throws SQLException, ClassNotFoundException {
        ObservableList<Package> packages = FXCollections.observableArrayList();

        ObservableList<CustomerPackage> customerPackages = FXCollections.observableArrayList();

        ObservableList<Customer> customers = FXCollections.observableArrayList();

        customers.addAll(customerDB.loadCustonerDB());

        customerPackages.addAll(customerDB.loadAllCustomerPackage());

        packages.addAll(customerDB.loadPacketDB());

        ObservableList<ReportTotalPrice> reportTotalPrices = FXCollections.observableArrayList();

        double totalPrice = 0;

        if (status.equals("Customer")){
            printer.printReport("Report Customer");
            for (CustomerPackage cp : customerPackages){
                for (Package p : packages){
                    if (cp.getIdPackage() == p.getId()){
                        for (Customer c : customers){
                            if (cp.getIdCustomer() == c.getId() && cp.getStatus().equals("Active")){
                                ReportTotalPrice reportTotalPrice = new ReportTotalPrice(c.getId(), c.getName(), Double.parseDouble(p.getPrice()));
                                if (this.contain(reportTotalPrices, reportTotalPrice) == false){
                                    reportTotalPrices.add(reportTotalPrice);
                                    totalEach = String.format("%-30s %20s Baht\n",reportTotalPrice.getName(), reportTotalPrice.getPriceString());
                                    printer.printReport(totalEach);
                                }
                            }
                        }
                    }
                }
            }
        } else {
            printer.printReport("Report Package");
            for (CustomerPackage cp : customerPackages){
                for (Package p : packages){
                    if (cp.getIdPackage() == p.getId()){
                        for (Customer c : customers){
                            if (cp.getIdCustomer() == c.getId() && cp.getStatus().equals("Active")){
                                ReportTotalPrice reportTotalPrice = new ReportTotalPrice(p.getId(), p.getName(), Double.parseDouble(p.getPrice()));
                                if (this.contain(reportTotalPrices, reportTotalPrice) == false){
                                    reportTotalPrices.add(reportTotalPrice);
                                    totalEach = String.format("%-30s %20s Baht\n",reportTotalPrice.getName(), reportTotalPrice.getPriceString());
                                    printer.printReport(totalEach);
                                }
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

        totalReport.setText(getPriceString(totalPrice));
        printer.printReport("End-report Total "+totalPrice);
//        printer.printReport(totalEach,totalPrice+"");
    }
}
