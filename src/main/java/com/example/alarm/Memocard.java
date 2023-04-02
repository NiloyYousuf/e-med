package com.example.alarm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Memocard implements Initializable {

    @FXML
    private Label dlvrdto;

    @FXML
    private Label dttime;

    @FXML
    private Label grndtotal;

    @FXML
    private VBox sumContainer;

    public static Product ot = new Product();

    @Override
    public void initialize(URL Loc, ResourceBundle rbr)
    {

        int i=0;

        Node[] nodes = new Node[cart.Products.size()];
        LocalDateTime now = LocalDateTime.now();
        dttime.setText(now.toString());
        dlvrdto.setText("Delivered to: " + currentUser.user_name);


        for(Product product : cart.Products)
        {
            try {
                ot = product;

                final int j = i;

                nodes[i] = FXMLLoader.load(getClass().getResource("memoitem.fxml"));

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : rgba(162,132,162,0.76)");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #fdfdff");
                });
                sumContainer.getChildren().add(nodes[i]);
                i++;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        cart.generateSummary(cart.Products);
        grndtotal.setText(String.valueOf(cart.Total_Amount));

    }

    @FXML
    protected void onChckout(ActionEvent e) throws IOException
    {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.close();

    }


}
