package com.example.alarm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
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

public class MonthlySubscriptionAdminController implements Initializable {

    @FXML
    private TableView<MonthlySubscription> monthlySubscriptionTable;

    @FXML
    private TableColumn<MonthlySubscription, String> userID;
    @FXML
    private TableColumn<MonthlySubscription, String> userNameColumn;

    @FXML
    private TableColumn<MonthlySubscription, String> orderMemoColumn;

    @FXML
    private TableColumn<MonthlySubscription, String> deliveryAddressColumn;

    @FXML
    private TableColumn<MonthlySubscription, String> phoneNoColumn;

    @FXML
    private TableColumn<MonthlySubscription, String> deliveredTillColumn;

    @FXML
    private  TableColumn<MonthlySubscription,String> SubscriptionFeesColumn;
    @FXML
    private ComboBox<String> monthComboBox;

    @FXML
    private Button deliveredtill;

    private MonthlySubscriptionDAO dao;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the DAO
        try {
            dao = new MonthlySubscriptionDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Initialize the table columns
        deliveredtill.setOnAction(event -> handleDeliveredTillButtonAction());
        userID.setCellValueFactory(new PropertyValueFactory<>("id"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        orderMemoColumn.setCellValueFactory(new PropertyValueFactory<>("orderMemo"));
        deliveryAddressColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryAddress"));
        phoneNoColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        SubscriptionFeesColumn.setCellValueFactory(new PropertyValueFactory<>("totalCostMonthly"));
        deliveredTillColumn.setCellValueFactory(cellData -> {
            String value = cellData.getValue().getDeliveredTill();
            return new SimpleStringProperty(value.substring(0, 3)); // show only the date part
        });

        // Initialize the month combo box
        monthComboBox.setItems(FXCollections.observableArrayList(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        ));
        monthComboBox.getSelectionModel().selectFirst();
        // Populate the table with data
        populateTable();
    }

    @FXML
    private void handleMonthComboBoxAction() {
        populateTable();
    }

    private void populateTable() {
        String month = monthComboBox.getSelectionModel().getSelectedItem().toLowerCase();
        List<MonthlySubscription> subscriptions = dao.getAllSubscriptions();
        monthlySubscriptionTable.setItems(FXCollections.observableArrayList(subscriptions));
    }

    @FXML
    private void handleDeliveredTillButtonAction() {
        MonthlySubscription selectedSubscription = monthlySubscriptionTable.getSelectionModel().getSelectedItem();
        System.out.println(selectedSubscription.getUserName()+selectedSubscription.getId());
        if (selectedSubscription != null) {
            String month = monthComboBox.getSelectionModel().getSelectedItem().toLowerCase();
            String deliveredTill = month + " 01, 2023"; // set the delivered till date to the first day of the selected month
            selectedSubscription.setDeliveredTill(deliveredTill);
            dao.updateMonthlySubscription(selectedSubscription);
            monthlySubscriptionTable.refresh();
        }
    }

    @FXML
    private  void back_button_pressed(ActionEvent event) throws IOException {
        String s1="Adminmenu.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }

}
