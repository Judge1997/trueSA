package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Customer;

import java.util.ArrayList;

public class DisplayCustomerPageController {

    @FXML
    private Label nameLabel, aeNameLabel, regionLabel, locationLabel, businessIDLabel, capitalLabel, provinceLabel, khetLabel, khwangLabel;
    @FXML
    private Label employeeeLabel, contactTelLabel, contactFaxLabel, contactLabel, contactNameLabel;

    private Customer customer;


    public void setValue(Customer customer) {
        this.customer = customer;
        nameLabel.setText(this.customer.getName());
        aeNameLabel.setText(this.customer.getAeName());
        regionLabel.setText(this.customer.getRegion());
        locationLabel.setText(this.customer.getLocationInstall());
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
    }
}
