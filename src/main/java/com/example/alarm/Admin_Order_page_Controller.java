package com.example.alarm;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Admin_Order_page_Controller implements Initializable {

    // Database connection details
    private final String DB_URL = "jdbc:mysql://localhost:3306/project";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "200041123";

    // ObservableList of orders for TableView
    private ObservableList<Order> orders = FXCollections.observableArrayList();

    @FXML
    private ComboBox<String> statusDropdown;

    @FXML
    private Button updateButton;

    @FXML
    private TableView<Order> ordersTable;

    @FXML
    private TableColumn<Order, String> orderIdColumn;

    @FXML
    private TableColumn<Order, String> userNameColumn;

    @FXML
    private TableColumn<Order, String> orderDateColumn;

    @FXML
    private TableColumn<Order, String> phoneNoColumn;

    @FXML
    private TableColumn<Order, String> totalAmountColumn;

    @FXML
    private TableColumn<Order, String> deliveryAddressColumn;

    @FXML
    private TableColumn<Order, String> orderMemoColumn;

    @FXML
    private TableColumn<Order, String> orderStatusColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Populate the status dropdown
        statusDropdown.getItems().addAll("Order Received", "Order Under Delivery", "Order Delivered");

        // Set the cell value factories for each column
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        deliveryAddressColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryAddress"));
        orderMemoColumn.setCellValueFactory(new PropertyValueFactory<>("orderMemo"));
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

        // Populate the orders ObservableList from the database
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM orders");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getString("Order_ID"),
                        rs.getString("User_Name"),
                        rs.getDate("Order_Date"),
                        rs.getString("User_Phone_No"),
                        rs.getString("Total_Amount"),
                        rs.getString("Delivery_Address"),
                        rs.getString("Order_Memo"),
                        rs.getString("Order_Status")
                );
                orders.add(order);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Populate the orders TableView with the orders ObservableList
        ordersTable.setItems(orders);
    }

    @FXML public  void Switch_To_admin_Menu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("AdminMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void handleUpdateButton(ActionEvent event) {
        // Get the selected order from the TableView
        Order selectedOrder = ordersTable.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            // Update the order status in the database
            try {
                Database_connection dbcon = new Database_connection();
                Connection conn=dbcon.conn;
                PreparedStatement stmt = conn.prepareStatement("UPDATE orders SET Order_Status = ? WHERE Order_ID = ?");
                stmt.setString(1, statusDropdown.getValue());
                stmt.setString(2, selectedOrder.getOrderId());
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 1) {
                    // Update the selected order's status in the TableView and the orders ObservableList
                    selectedOrder.setOrderStatus(statusDropdown.getValue());
                    ordersTable.refresh();
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
