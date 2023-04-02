package com.example.alarm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MonthlySubscriptionPage implements Initializable {

    @FXML
    private VBox monthlyItmContainer;

    @FXML
    private Label Deliveryaddressmissing;

    @FXML
    private TextField addressarea;

    @FXML
    private Label contact_no_missing;

    @FXML
    private DatePicker endMonth;

    @FXML
    private TextField phonenoarea;

    @FXML
    private DatePicker startMonth;

    @FXML
    private Label total_order_value;

    @FXML
    private Button apply;

    private List<orderedItem> ordereditemList = new ArrayList<>();
    public static orderedItem ot;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the item list
        cart_monthly Cart= new cart_monthly();
        Cart.removeduplicatearraylist();
        for (int i=0;i<cart.Products.size();i++)
        {ordereditemList.add(new orderedItem(Cart.Products.get(i).Product_ID,Cart.Products.get(i).Product_Name,Cart.Products.get(i).Product_Price,Cart.Products.get(i).Product_Total_Available,Cart.Products.get(i).Product_Description,Cart.Products.get(i).Product_Image_URL));
            ordereditemList.get(i).product_selected=String.valueOf(cart.Products.get(i).Addedtocart);
        }

        int i=0;
        Node[] nodes = new Node[ordereditemList.size()];

        for(orderedItem orderedItem : ordereditemList)
        {
            try {
                ot = orderedItem;

                final int j = i;

                nodes[i] = FXMLLoader.load(getClass().getResource("ordersummarymonthly.fxml"));

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : rgba(162,132,162,0.76)");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #fdfdff");
                });
                monthlyItmContainer.getChildren().add(nodes[i]);
                i++;

            } catch (IOException e) {
                e.printStackTrace();
            }

            total_order_value.setText(String.valueOf(cart_monthly.Total_Amount));

        }
    }

    @FXML
    private  Label noitemsaddedtocart;

    @FXML
    protected void onApplypressed() throws SQLException, IOException {
        boolean canmakedelivery = true;

        if (addressarea.getText().equals("")) {
            Deliveryaddressmissing.setText("Please put your Delivery Address");
            canmakedelivery = false;
        } else {
            Deliveryaddressmissing.setText("");
        }
        if (phonenoarea.getText().equals("")) {
            contact_no_missing.setText("Please put your contact number");
            canmakedelivery = false;
        } else {
            contact_no_missing.setText("");
        }
        if (total_order_value.getText().equals("")) {
            noitemsaddedtocart.setText("Please Add items to cart First");
            canmakedelivery = false;
        }

        if (canmakedelivery == true) {
            cart_monthly.generateSummary(cart_monthly.Products);
            orderdao Insertorder = new orderdao();
            Insertorder.addOrderMonthly(currentUser.user_name, phonenoarea.getText(), String.valueOf(cart.Total_Amount), addressarea.getText(), cart_monthly.generateSummary(cart_monthly.Products), startMonth.getValue().toString(), endMonth.getValue().toString());

            ButtonNotificationExample b = new ButtonNotificationExample();
            b.showNotificationorderPlaced(new Stage());

            switchtomenu(apply);

            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("memocard.fxml"));
            Stage stage = new Stage(StageStyle.TRANSPARENT);
            Scene scene = new Scene(fxmlLoader.load(), 252, 436);
            stage.setTitle("e-MED");
            stage.setScene(scene);
            stage.show();
            cart_monthly.Products.clear();
            cart_monthly.total_items_selected=0;

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

    public void switchtomenu(Button button ) throws IOException {
        String s1="userloggedin.fxml";
        Stage stage = (Stage) button.getScene().getWindow();
        // stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }
}
