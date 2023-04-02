package com.example.alarm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class memoitem implements Initializable {

    @FXML
    private AnchorPane hbox;

    @FXML
    private Label mname;

    @FXML
    private Label quan;

    @FXML
    private Label totla;

    @FXML
    private Label uprice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mname.setText(Memocard.ot.Product_Name);
        quan.setText(" x" + Memocard.ot.Addedtocart.toString());
        uprice.setText(Memocard.ot.Product_Price);
        totla.setText(String.valueOf(Integer.parseInt(Memocard.ot.Product_Price) * Memocard.ot.Addedtocart));

    }


}
