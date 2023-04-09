package com.example.alarm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public class orderdao {


    public void addOrder(String userName, String userPhoneNo, String totalAmount, String deliveryAddress, String orderMemo, String orderStatus) throws SQLException {

        Connection conn=null;
        PreparedStatement stmt = null;

        try {
            // Register JDBC driver


            // Open a connection
            Database_connection dbcon = new Database_connection();
           conn=dbcon.conn;

            // Set auto-commit to false
            conn.setAutoCommit(false);

            // Create a PreparedStatement with a parameterized SQL query
            String sql = "INSERT INTO orders (Order_ID, User_Name, Order_Date, User_Phone_No, Total_Amount, Delivery_Address, Order_Memo, Order_Status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            // Get the current date/time
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String dateStr = now.format(formatter);

            // Generate the order ID by combining the user ID and date/time
            String orderId = userName + "-" + dateStr;

            // Set the parameters for the PreparedStatement
            stmt.setString(1, orderId);
            stmt.setString(2, userName);
            stmt.setDate(3, new java.sql.Date(new Date().getTime()));
            stmt.setString(4, userPhoneNo);
            stmt.setString(5, totalAmount);
            stmt.setString(6, deliveryAddress);
            stmt.setString(7, orderMemo);
            stmt.setString(8, orderStatus);

            // Execute the PreparedStatement
            stmt.executeUpdate();

            // Commit the transaction
            conn.commit();

        } catch (SQLException e) {
            // Rollback the transaction if there is an error
            if (conn != null) {
                conn.rollback();
            }
            throw e;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close the PreparedStatement and connection
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void addOrderMonthly(String userName, String userPhoneNo, String totalAmount, String deliveryAddress, String orderMemo, String start) throws SQLException {

        Connection conn=null;
        PreparedStatement stmt = null;

        try {
            // Register JDBC driver


            // Open a connection
            Database_connection dbcon = new Database_connection();
            conn=dbcon.conn;

            // Set auto-commit to false
            conn.setAutoCommit(false);

            // Create a PreparedStatement with a parameterized SQL query
            String sql = "INSERT INTO monthly_subscription " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            // Get the current date/time
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
            String dateStr = now.format(formatter);

            // Generate the order ID by combining the user ID and date/time
            String orderId = userName + "-" + dateStr;

            // Set the parameters for the PreparedStatement
            stmt.setString(1, orderId);
            stmt.setString(2, userName);
            stmt.setString(3, orderMemo);
            stmt.setString(4, deliveryAddress);
            stmt.setString(5, userPhoneNo);
            stmt.setString(6, totalAmount);
            stmt.setString(7, start);

            // Execute the PreparedStatement
            stmt.executeUpdate();

            // Commit the transaction
            conn.commit();

        } catch (SQLException e) {
            // Rollback the transaction if there is an error
            if (conn != null) {
                conn.rollback();
            }
            throw e;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close the PreparedStatement and connection
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void updateStock() throws SQLException, ClassNotFoundException {
        Database_connection dbconn = new Database_connection();
        Connection conn = dbconn.conn;
        for(Product products : cart.Products)
        {
            try {
                PreparedStatement ps = conn.prepareStatement("update product_table set Total_Avaiable = ? where Product_ID = ?");
                ps.setInt(1, Integer.parseInt(products.Product_Total_Available) - products.Addedtocart);
                ps.setString(2, products.Product_ID);
                //User_ADD_TO_CART.availables.put(products.Product_ID, Integer.parseInt(products.Product_Total_Available) - products.Addedtocart);
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(" Error while connecting to database. Exception code : " + e);
            }
        }

        table2 connecttotable = new table2();
        products.products = connecttotable.getProductlist();

    }
}
