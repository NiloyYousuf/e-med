package com.example.alarm;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ProductEditor{

    public ProductEditor() {
        // default constructor with no parameters
    }
    private TextField idField = new TextField();
    private TextField nameField = new TextField();
    private TextField priceField = new TextField();
    private TextField availableField = new TextField();
    private TextField descriptionField = new TextField();

    private String productId;

    public ProductEditor(String productId) {
        this.productId = productId;
    }
    public void start(Stage primaryStage,String productId) {
        primaryStage.setTitle("Edit Product");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(new Label("Product ID:"), 0, 0);
        idField.setText(productId);
        idField.setDisable(true);
        grid.add(idField, 1, 0);

        grid.add(new Label("Product Name:"), 0, 1);
        grid.add(nameField, 1, 1);

        grid.add(new Label("Product Price:"), 0, 2);
        grid.add(priceField, 1, 2);

        grid.add(new Label("Total Available:"), 0, 3);
        grid.add(availableField, 1, 3);

        grid.add(new Label("Product Description:"), 0, 4);
        grid.add(descriptionField, 1, 4);

        Button btn = new Button("Update Product");
        btn.setOnAction(event -> {
            updateProduct(productId);
            primaryStage.close();
        });
        grid.add(btn, 1, 5);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateProduct(String productId) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "200041123");
             PreparedStatement stmt = conn.prepareStatement("UPDATE product_table SET Product_Name = ?, Product_Price = ?, Total_Avaiable = ?, Product_Description = ? WHERE Product_ID ='"+productId+"' ")) {
            stmt.setString(1, nameField.getText());
            stmt.setString(2, priceField.getText());
            stmt.setString(3, availableField.getText());
            stmt.setString(4, descriptionField.getText());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
