package com.example.alarm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Admin_Menu_Controller {
    @FXML
    private Button Show_Products;
    @FXML
    private  Button Add_Products;
    @FXML
    private  Button Show_orders;


    public void switch_to_add_product_page(ActionEvent event) throws IOException {
        String s1="Admin_add_new_product.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }


    public void switch_to_show_orders_page(ActionEvent event) throws IOException {
        String s1="Admin_Show_all_Products.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }

    public void switch_to_show_products(ActionEvent event) throws IOException {
        String s1="Admin_view_all_products.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }

    public void switch_to_show_monthly_subscriptions(ActionEvent event) throws IOException {
        String s1="Admin_monthly_Subscription.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }

}
