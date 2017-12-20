package controllers;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class StartProcController {

    private Button CustomerBtn;

    public void initialize() {
        Image customer = new Image(getClass().getResourceAsStream("Customer.png"));
        CustomerBtn.setGraphic(new ImageView(customer));
    }
}
