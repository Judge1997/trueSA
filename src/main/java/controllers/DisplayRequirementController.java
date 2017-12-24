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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Customer;
import models.CustomerRequirement;
import models.Requirement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DisplayRequirementController {

    private DBConnector requirementDB = DBConnector.getSelf();

    @FXML
    private TableView<Requirement> tableView;

    private int idCustomer;

    @FXML
    private Button deleteBtn, showBtn;

    @FXML
    public void refresh() throws SQLException, ClassNotFoundException {
        ObservableList<Requirement> requirements = FXCollections.observableArrayList();
        List<Requirement> requirementList = requirementDB.loadRequirementDB();
        List<CustomerRequirement> customerRequirements = requirementDB.loadCustomerRequirement(idCustomer);

        for (CustomerRequirement cr : customerRequirements){
            for (Requirement r : requirementList){
                if (cr.getIdRequirement() == r.getId()){
                    requirements.add(r);
                }
            }
        }

        tableView.setItems(requirements);
    }

    public void initialize() throws SQLException, ClassNotFoundException {
//        this.refresh();
        deleteBtn.setDisable(true);
        showBtn.setDisable(true);

        tableView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if(event.getButton() == MouseButton.SECONDARY) {
                    Requirement requirement = tableView.getSelectionModel().getSelectedItem();
                    if ( requirement != null ){
                        deleteBtn.setDisable(false);
                        showBtn.setDisable(false);
                }
                }else{
                    Requirement requirement = tableView.getSelectionModel().getSelectedItem();
                    if ( requirement != null ){
                        deleteBtn.setDisable(false);
                        showBtn.setDisable(false);
                    }
                }
            }
        });
    }

    @FXML
    public void showBtn(ActionEvent event) throws IOException {
        Requirement requirement = tableView.getSelectionModel().getSelectedItem();

        Button loginBtn = (Button) event.getSource();
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayEachRequirement.fxml"));
        Parent window = loader.load();

        DisplayEachRequirementController displayEachRequirementController = loader.getController();
        displayEachRequirementController.setDetail(requirement.getDetail());

        this.goToPage(stage,window,300,600);
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
                    tableView.getSelectionModel().getSelectedItem().getId() + " ?",
                    ButtonType.OK, ButtonType.CANCEL);
            Optional optional = alert.showAndWait();
            if (optional.get() == ButtonType.OK) {
                Requirement requirement = tableView.getSelectionModel().getSelectedItem();
                requirementDB.deleteRequirementDB(requirement.getId());

                this.refresh();

                deleteBtn.setDisable(true);
                showBtn.setDisable(true);
            }
        }
    }

    @FXML
    public void okBtn(ActionEvent event){
        this.closeThisWindow(event);
    }

    private void closeThisWindow(ActionEvent event) {
        Button cancelBtn = (Button) event.getSource();
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) throws SQLException, ClassNotFoundException {
        this.idCustomer = idCustomer;
        this.refresh();
    }
}
