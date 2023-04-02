package com.example.alarm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Ordersummary implements Initializable {

    @FXML
    private ImageView img;

    @FXML
    private Label mednem;

    @FXML
    private Label prcxquan;

    @FXML
    private Label total;

    orderedItem oitem = orderpagecontroller.ot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        mednem.setText(oitem.product_name);
        prcxquan.setText(oitem.product_price + " x " + oitem.product_selected);
        total.setText(String.valueOf((Integer.parseInt(oitem.product_price) * Integer.parseInt(oitem.product_selected))));
        img.setImage(new Image(oitem.product_image_url));

    }

}
