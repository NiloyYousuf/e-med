package com.example.alarm;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainPage implements Initializable {

    @FXML
    private Pane showPane;


    @Override
    public void initialize(URL u, ResourceBundle rb)  {
        String s1="Admin_view_all_Products.fxml";
        Pane newLoadPane = null;
        try {
            newLoadPane = FXMLLoader.load(getClass().getResource(s1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        showPane.getChildren().clear();
        showPane.getChildren().add(newLoadPane);
    }

    @FXML
    public void switch_to_add_product_page(ActionEvent event) throws IOException {
        String s1="Admin_add_new_product.fxml";
        //FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Pane newLoadPane = FXMLLoader.load(getClass().getResource(s1));
        showPane.getChildren().clear();
        showPane.getChildren().add(newLoadPane);
        /*Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();*/
    }


    @FXML public void switch_to_show_orders_page(ActionEvent event) throws IOException {
        String s1="Admin_Show_all_Products.fxml";
        Pane newLoadPane = FXMLLoader.load(getClass().getResource(s1));
        showPane.getChildren().clear();
        showPane.getChildren().add(newLoadPane);
        /*FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1080, 720);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();*/
    }

    @FXML public void switch_to_show_products(ActionEvent event) throws IOException {
        String s1="Admin_view_all_products.fxml";
        Pane newLoadPane = FXMLLoader.load(getClass().getResource(s1));
        showPane.getChildren().clear();
        showPane.getChildren().add(newLoadPane);
        /*FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();*/
    }

    @FXML public void switch_to_show_monthly_subscriptions(ActionEvent event) throws IOException {
        String s1="Admin_monthly_Subscription.fxml";
        Pane newLoadPane = FXMLLoader.load(getClass().getResource(s1));
        showPane.getChildren().clear();
        showPane.getChildren().add(newLoadPane);
        /*FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();*/
    }
}
