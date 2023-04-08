package com.example.alarm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MonthlySubscriptionPage implements Initializable {
    @FXML
    private TextField adddrefld;

    @FXML
    private TextField cellphone;

    @FXML
    private VBox itemContainer;

    @FXML
    private TextArea itemsummary;

    @FXML
    private Label month;

    @FXML
    private Label noitemsaddedtocart;

    @FXML
    private Button onCancelPressed;

    @FXML
    private Label orderID;

    @FXML
    private Label orderID1;

    @FXML
    private Label orderID2;

    @FXML
    private TextArea total_order_value;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Database_connection dbconn;
        try {
            dbconn = new Database_connection();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Connection conn = dbconn.conn;

        try
        {
            PreparedStatement ps = conn.prepareStatement("select * from monthly_subscription where User_name = ?");
            ps.setString(1, currentUser.user_name);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                orderID.setText(rs.getString("idmonthly"));
                itemsummary.setText(rs.getString("Order_Memo"));
                adddrefld.setText(rs.getString("Delivery_address"));
                cellphone.setText(rs.getString("Phone_no"));
                total_order_value.setText(rs.getString("Total_Cost_Monthly"));
                month.setText(rs.getString("Delivered_from"));
            }


        }
        catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }

    }

    public void backbuttonpressed(ActionEvent e ) throws IOException {
        cart_monthly.generateSummary(cart_monthly.Products);
        String s1="searchpage.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();

    }

    public void cancelButtonPressed(ActionEvent e) throws IOException {
        String s1="UserLoggedInprevious.fxml";
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // stage.close();
        Database_connection dbconn;
        try {
            dbconn = new Database_connection();
        } catch (ClassNotFoundException ev) {
            throw new RuntimeException(ev);
        } catch (SQLException ev) {
            throw new RuntimeException(ev);
        }
        Connection conn = dbconn.conn;

        try
        {
            Statement stmt = conn.createStatement();
            String quer = "delete from monthly_subscription where idmonthly = '"+orderID.getText()+"' AND username = '"+currentUser.user_name+"'";

            int a = stmt.executeUpdate(quer);
            if (a > 0) {
                System.out.println("Data is deleted");
            } else {
                System.out.println("Deletion failed");
            }
            stmt.close();

        }
        catch (SQLException ev) {
            System.out.println(" Error while connecting to database. Exception code : " + ev);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }
}
