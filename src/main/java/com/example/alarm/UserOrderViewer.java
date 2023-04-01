package com.example.alarm;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOrderViewer extends Application {

    // Define database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/project";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "200041123";

    // Define ObservableList to hold orders
    private ObservableList<Order> orders = FXCollections.observableArrayList();

    // Define ComboBox options
    private ObservableList<String> statusOptions = FXCollections.observableArrayList(
            "Order Received", "Order Under Delivery", "Order Delivered");

    @Override
    public void start(Stage primaryStage) {

        // Create TableView to display orders
        TableView<Order> orderTable = new TableView<>();

        // Create TableColumn for each order field
        TableColumn<Order, String> orderIdCol = new TableColumn<>("Order ID");
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        TableColumn<Order, String> userNameCol = new TableColumn<>("User Name");
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));

        TableColumn<Order, String> orderDateCol = new TableColumn<>("Order Date");
        orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        TableColumn<Order, String> phoneNoCol = new TableColumn<>("Phone No");
        phoneNoCol.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));

        TableColumn<Order, String> totalAmountCol = new TableColumn<>("Total Amount");
        totalAmountCol.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));

        TableColumn<Order, String> deliveryAddressCol = new TableColumn<>("Delivery Address");
        deliveryAddressCol.setCellValueFactory(new PropertyValueFactory<>("deliveryAddress"));

        TableColumn<Order, String> orderMemoCol = new TableColumn<>("Order Memo");
        orderMemoCol.setCellValueFactory(new PropertyValueFactory<>("orderMemo"));

        TableColumn<Order, String> orderStatusCol = new TableColumn<>("Order Status");
        orderStatusCol.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        // Add TableColumn objects to TableView
        orderTable.getColumns().addAll(orderIdCol, userNameCol, orderDateCol,
                phoneNoCol, totalAmountCol, deliveryAddressCol, orderMemoCol, orderStatusCol);

        // Retrieve orders from database
        retrieveOrders();

        // Add orders to TableView
        orderTable.setItems(orders);

        // Create ComboBox for updating order status
        ComboBox<String> statusComboBox = new ComboBox<>(statusOptions);
        statusComboBox.setOnAction(event -> {
            Order selectedOrder = orderTable.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                selectedOrder.setOrderStatus(statusComboBox.getValue());
                updateOrderStatus(selectedOrder.getOrderId(), statusComboBox.getValue());
            }
        });

        // Create VBox to hold TableView and ComboBox
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(orderTable, statusComboBox);

        // Create Scene and add VBox to it
        Scene scene = new Scene(vbox);

        // Set title of stage and show it
        primaryStage.setTitle("lol");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Retrieves all orders from the database and adds them to the ObservableList.
     */
    private void retrieveOrders() {
        try {
            // Get database connection
            Database_connection dbcon = new Database_connection();
            Connection conn=dbcon.conn;

            // Create PreparedStatement to retrieve all orders
            String sql = "SELECT * FROM orders";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Execute query and get ResultSet
            ResultSet rs = stmt.executeQuery();

            // Loop through ResultSet and add each order to ObservableList
            while (rs.next()) {
                Order order = new Order(rs.getString("Order_ID"), rs.getString("User_Name"),
                        rs.getDate("Order_Date"), rs.getString("User_Phone_No"),
                        rs.getString("Total_Amount"), rs.getString("Delivery_Address"),
                        rs.getString("Order_Memo"), rs.getString("Order_Status"));
                orders.add(order);
            }

            // Close database objects
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the order status in the database.
     *
     * @param orderId   the ID of the order to update
     * @param newStatus the new order status
     */
    private void updateOrderStatus(String orderId, String newStatus) {
        try {
            // Get database connection

            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create PreparedStatement to update order status
            String sql = "UPDATE orders SET Order_Status = ? WHERE Order_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newStatus);
            stmt.setString(2, orderId);

            // Execute update
            stmt.executeUpdate();

            // Close database objects
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


