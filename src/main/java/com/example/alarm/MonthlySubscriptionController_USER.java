package com.example.alarm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MonthlySubscriptionController_USER {
@FXML
private  Label useID;
    @FXML
    private Label userNameLabel;

    @FXML
    private Label orderMemoLabel;

    @FXML
    private Label deliveryAddressLabel;

    @FXML
    private Label phoneNoLabel;

    @FXML
    private Label totalCostLabel;

    @FXML
    private Label deliveredTillLabel;

    public void initialize(String userName) {
        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "your_username", "your_password");

            // Prepare the SQL statement
            String sql = "SELECT * FROM monthly_subscription WHERE User_name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName);

            // Execute the SQL statement and get the result set
            ResultSet rs = stmt.executeQuery();

            // If the result set has a row, populate the labels with the data
            if (rs.next()) {
                useID.setText(rs.getString("idMonthly_subscription"));
                userNameLabel.setText(rs.getString("User_name"));
                orderMemoLabel.setText(rs.getString("Order_memo"));
                deliveryAddressLabel.setText(rs.getString("Delivery_address"));
                phoneNoLabel.setText(rs.getString("Phone_no"));
                totalCostLabel.setText(rs.getString("Total_Cost_Monthly"));
                deliveredTillLabel.setText(rs.getString("Delivered_till"));
            }

            // Close the resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleDeleteButtonAction() {
        try {
            // Connect to the database

            Database_connection dbcon=new Database_connection();
            Connection conn=dbcon.conn;
            // Prepare the SQL statement
            String sql = "DELETE FROM monthly_subscription WHERE User_name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, currentUser.user_name);

            // Execute the SQL statement
            int rowsAffected = stmt.executeUpdate();

            // Check if the data was deleted successfully
            if (rowsAffected > 0) {
                // Clear the UI controls
                userNameLabel.setText("");
                orderMemoLabel.setText("");
                deliveryAddressLabel.setText("");
                phoneNoLabel.setText("");
                totalCostLabel.setText("");
                deliveredTillLabel.setText("");

                // Show a message to the user
                System.out.println("Monthly subscription for " + currentUser.user_name+ " was deleted successfully.");
            } else {
                // Show an error message to the user
                System.out.println("No monthly subscription was found for " + currentUser.user_name + ".");
            }

            // Close the resources
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
