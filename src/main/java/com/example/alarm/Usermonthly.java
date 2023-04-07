package com.example.alarm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Usermonthly implements Initializable {
    @FXML
    public Label no_subscription_label;
    @FXML
    public Label subscription_memo;
    @FXML
    public  Label last_month_received;


    @FXML
    public void switchtomenu(ActionEvent event) throws IOException {
        String s1="userloggedin.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Database_connection dbcon= null;
        try {
            dbcon = new Database_connection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (Connection conn =dbcon.conn) {
                    System.out.println("Database connected.");
                    String user_name = currentUser.user_name;
                    // SQL query to retrieve Order_memo and Total_Cost_Monthly for the given user name
                    String sql = "SELECT Order_memo, Total_Cost_Monthly,Delivered_till FROM monthly_subscription WHERE User_name = ?";
                    // create prepared statement
                    PreparedStatement statement = conn.prepareStatement(sql);
                    // set parameter values
                    statement.setString(1, "Niloy");
                    // execute query
                    ResultSet result = statement.executeQuery();
                    int i=0;
                    // loop through result set
                    while (result.next()) {
                        i=1;
                        String order_memo = result.getString("Order_memo");
                        String total_cost_monthly = result.getString("Total_Cost_Monthly");
                        String deliveredtill=result.getString("Delivered_till");
                        // do something with the retrieved values
                        System.out.println("Order Memo: " + order_memo + ", Total Cost Monthly: " + total_cost_monthly);

                        subscription_memo.setText(order_memo);
                        last_month_received.setText(deliveredtill);

                    }
                    if(i==0)
                        no_subscription_label.setText("NO SUBSCRIPTION");
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
}

