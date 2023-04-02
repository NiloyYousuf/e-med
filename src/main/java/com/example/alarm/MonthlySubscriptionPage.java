package com.example.alarm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MonthlySubscriptionPage implements Initializable {

    @FXML
    private VBox monthlyItmContainer;

    @FXML
    private Label total_order_value;
    private List<orderedItem> ordereditemList = new ArrayList<>();
    public static orderedItem ot;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the item list
        cart_monthly Cart= new cart_monthly();
        Cart.removeduplicatearraylist();
        for (int i=0;i<cart.Products.size();i++)
        {ordereditemList.add(new orderedItem(Cart.Products.get(i).Product_ID,Cart.Products.get(i).Product_Name,Cart.Products.get(i).Product_Price,Cart.Products.get(i).Product_Total_Available,Cart.Products.get(i).Product_Description,Cart.Products.get(i).Product_Image_URL));
            ordereditemList.get(i).product_selected=String.valueOf(cart.Products.get(i).Addedtocart);
        }

        int i=0;
        Node[] nodes = new Node[ordereditemList.size()];

        for(orderedItem orderedItem : ordereditemList)
        {
            try {
                ot = orderedItem;

                final int j = i;

                nodes[i] = FXMLLoader.load(getClass().getResource("ordersummary.fxml"));

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : rgba(162,132,162,0.76)");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #fdfdff");
                });
                monthlyItmContainer.getChildren().add(nodes[i]);
                i++;

            } catch (IOException e) {
                e.printStackTrace();
            }

            total_order_value.setText(String.valueOf(cart.Total_Amount));

        }
    }
}
