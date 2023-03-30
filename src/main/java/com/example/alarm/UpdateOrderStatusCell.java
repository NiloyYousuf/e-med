package com.example.alarm;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.util.Callback;

import java.util.Arrays;

public class UpdateOrderStatusCell extends TableCell<Order, String> {

    private final ComboBox<String> comboBox;

    public UpdateOrderStatusCell() {
        comboBox = new ComboBox<>(FXCollections.observableArrayList("Dispatched", "Delivering", "Delivered"));

        comboBox.setOnAction(event -> {
            Order order = getTableView().getItems().get(getIndex());
            order.setOrderStatus(comboBox.getSelectionModel().getSelectedItem());
            // TODO: Update the order status in the database
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            comboBox.getSelectionModel().select(item);
            setGraphic(comboBox);
        }
    }
}
