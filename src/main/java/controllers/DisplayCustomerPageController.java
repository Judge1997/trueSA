package controllers;

import database.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import models.Customer;
import models.CustomerPackage;
import models.Package;
import models.PackageForCustomer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class DisplayCustomerPageController implements Observer{

    private DBConnector customerDB = DBConnector.getSelf();

    @FXML
    private Label nameLabel, aeNameLabel, regionLabel, businessIDLabel, capitalLabel, provinceLabel, khetLabel, khwangLabel;
    @FXML
    private Label employeeeLabel, contactTelLabel, contactFaxLabel, contactLabel, contactNameLabel;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableView<PackageForCustomer> tableView;
    @FXML
    private TextArea locationArea;

    private Customer customer;
    private int idCustomer;
    @FXML
    private TableColumn mobileQuantityHeader,mobileSpeedHeader,mobileTimesHeader;

    ObservableList<CustomerPackage> customerPackages = FXCollections.observableArrayList();

    @FXML
    public void refresh() throws SQLException, ClassNotFoundException {
        ObservableList<PackageForCustomer> packages = FXCollections.observableArrayList();

        customerPackages = FXCollections.observableArrayList();

        customerPackages.addAll(customerDB.loadCustomerPackage(idCustomer));

        packages.addAll(customerDB.loadPackageFromCustomerPackage(customerPackages));
//        packages.addAll(customerDB.loadPackageFromCustomerPackage(idCustomer));

        tableView.setItems(packages);
    }

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

    public void initialize() throws SQLException, ClassNotFoundException {
//        this.refresh();
        deleteBtn.setDisable(true);

        this.makeHeaderWrappable(mobileQuantityHeader);
        this.makeHeaderWrappable(mobileSpeedHeader);
        this.makeHeaderWrappable(mobileTimesHeader);

        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY) {
                    Package packages = tableView.getSelectionModel().getSelectedItem();
                    if ( packages != null && packages.getStatus().equals("Active")){
                        deleteBtn.setDisable(false);
                    } else {
                        deleteBtn.setDisable(true);
                    }
                }else{
                    Package packages = tableView.getSelectionModel().getSelectedItem();
                    if ( packages != null && packages.getStatus().equals("Active")){
                        deleteBtn.setDisable(false);
                    } else {
                        deleteBtn.setDisable(true);
                    }
                }
            }
        });

    }

    @FXML
    public void addBtn() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RegistPackageToCustomerPage.fxml"));
        Parent window = loader.load();

        RegistPackageToCustomerController registPackageToCustomerController = loader.getController();
        registPackageToCustomerController.setValue(customer);
        registPackageToCustomerController.addObserver(this);

        this.goToPage(stage,window,400,600);
    }

    public void goToPage(Stage stage, Parent loader, int height, int width) throws IOException {
        stage.setTitle("trueSA");
        stage.setScene(new Scene(loader, width, height));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void deleteBtn() throws SQLException, ClassNotFoundException {
        if (tableView.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete " +
                    tableView.getSelectionModel().getSelectedItem().getName() + " ?",
                    ButtonType.OK, ButtonType.CANCEL);
            Optional optional = alert.showAndWait();
            if (optional.get() == ButtonType.OK) {
                deleteBtn.setDisable(true);
                int index = tableView.getSelectionModel().getSelectedIndex();
                System.out.println(index);

                CustomerPackage customerPackage = customerPackages.get(index);
                System.out.println(customerPackage.getId());
                customerDB.inActiveCustomerPackage(customerPackage.getId());
                this.refresh();
            }
        }
    }

    @FXML
    public void cancelBtn(ActionEvent event){
        this.closeThisWindow(event);
    }

    private void closeThisWindow(ActionEvent event) {
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void setValue(Customer customer) throws SQLException, ClassNotFoundException {
        this.customer = customer;
        this.idCustomer = customer.getId();
        nameLabel.setText(this.customer.getName());
        aeNameLabel.setText(this.customer.getAeName());
        regionLabel.setText(this.customer.getRegion());
        locationArea.setText(this.customer.getLocationInstall());
        businessIDLabel.setText(this.customer.getBusinessId());
        capitalLabel.setText(this.customer.getCapital());
        provinceLabel.setText(this.customer.getProvince());
        khetLabel.setText(this.customer.getKhet());
        khwangLabel.setText(this.customer.getKhwang());
        employeeeLabel.setText(this.customer.getEmployee());
        contactTelLabel.setText(this.customer.getContactTelNum());
        contactFaxLabel.setText(this.customer.getContactFax());
        contactLabel.setText(this.customer.getContact());
        contactNameLabel.setText(this.customer.getContactName());
        this.refresh();
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            this.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addRequirementBtn() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddRequirementPage.fxml"));
        Parent window = loader.load();
        AddRequirementPageController addRequirementPageController = loader.getController();
        addRequirementPageController.setIdCustomer(customer.getId());
        this.goToPage(stage,window,300,400);
    }

    @FXML
    public void showRequirementBtn() throws IOException, SQLException, ClassNotFoundException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayRequirement.fxml"));
        Parent window = loader.load();
        DisplayRequirementController displayRequirementController = loader.getController();
        displayRequirementController.setIdCustomer(customer.getId());
        this.goToPage(stage,window,400,600);
    }
}
