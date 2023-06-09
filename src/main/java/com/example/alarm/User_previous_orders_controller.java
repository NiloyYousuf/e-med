package com.example.alarm;
import java.io.IOException;
import java.net.URL;
import java.nio.file.attribute.AttributeView;
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

public class User_previous_orders_controller implements Initializable {

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


        // Set the cell value factories for each column

        totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        deliveryAddressColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryAddress"));
        orderMemoColumn.setCellValueFactory(new PropertyValueFactory<>("orderMemo"));
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        // Populate the orders ObservableList from the database
        try {
            Database_connection dbcon = new Database_connection();
            Connection conn = dbcon.conn;
            System.out.println(currentUser.user_name);
            PreparedStatement ps = conn.prepareStatement("select * from orders where User_Name = ?");
            ps.setString(1, currentUser.user_name);

            // Execute the query and retrieve the results
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
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

                System.out.println(rs.getString(1));
                orders.add(order);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Populate the orders TableView with the orders ObservableList
        ordersTable.setItems(orders);
    }


    public  void  backButtonPressed(ActionEvent event) throws IOException {
        String s1="UserLoggedInprevious.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }

}
