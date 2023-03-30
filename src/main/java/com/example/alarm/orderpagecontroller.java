package com.example.alarm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class orderpagecontroller implements Initializable {

    @FXML
    private VBox itemContainer;

    @FXML
    private  Button Cart;

    @FXML
    private  Label cartlabel;
    private List<orderedItem> ordereditemList = new ArrayList<>();

    @FXML
    private TextArea total_order_value;

    @FXML
    private  Button place_order;

    @FXML
    private TextArea addressarea;

    @FXML
    private  TextArea phonenoarea;
    @FXML
    private ChoiceBox<String> myChoiceBox;
    @FXML
    private  void placeorderButtonPressed() throws SQLException {
       cart.generateSummary(cart.Products);
        String selectedValue = myChoiceBox.getValue();
        orderdao Insertorder=new orderdao();
        Insertorder.addOrder(currentUser.user_name, phonenoarea.getText() ,String.valueOf(cart.Total_Amount),addressarea.getText(),cart.generateSummary(cart.Products),"Order Received");
        System.out.println(selectedValue);

     /*   ButtonNotificationExample b=new ButtonNotificationExample();
        b.showNotification(new Stage()); */
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the item list
        cart Cart=new cart();
        Cart.removeduplicatearraylist();
        for (int i=0;i<cart.Products.size();i++)
        {ordereditemList.add(new orderedItem(Cart.Products.get(i).Product_ID,Cart.Products.get(i).Product_Name,Cart.Products.get(i).Product_Price,Cart.Products.get(i).Product_Total_Available,Cart.Products.get(i).Product_Description,Cart.Products.get(i).Product_Image_URL));
            ordereditemList.get(i).product_selected=String.valueOf(cart.Products.get(i).Addedtocart);
        }

        for (orderedItem ordereditem : ordereditemList) {
            HBox itemBox = new HBox();

            ImageView imageView = new ImageView(ordereditem.getProduct_image_url());
            imageView.setFitWidth(160);
            imageView.setFitHeight(120);
            Label pricelabel = new Label("Price: " + ordereditem.getProduct_price());

            Label selectedlabel=new Label(ordereditem.getProduct_selected());
            Label nameLabel = new Label(ordereditem.getProduct_name());
            VBox NameAndDesc = new VBox(nameLabel, pricelabel);

            itemBox.getChildren().addAll(new HBox(imageView, NameAndDesc,selectedlabel));
            itemContainer.getChildren().add(itemBox);
            total_order_value.setText(String.valueOf(cart.Total_Amount));
        }

    }


    @FXML
    private  Button monthlysubscriptionpageclicked;



    public void decrease(Button button1, Label Quantity,orderedItem item)
    {
        if(!Quantity.getText().equals("0")) {
            Quantity.setText(Integer.toString(Integer.parseInt(Quantity.getText()) - 1));
            cartlabel.setText(Integer.toString(Integer.parseInt(cartlabel.getText()) - 1));
            item.selected--;
        }


    }





    public  void increase(Button button1, Label Quantity,orderedItem item)
    {

        Quantity.setText(Integer.toString(Integer.parseInt(Quantity.getText())+1));
        cartlabel.setText(Integer.toString(Integer.parseInt(cartlabel.getText())+1));
    }





    private  class orderedItem {
        private final String product_name ;
        private final String product_description ;
        private  final String product_ID;
        private   String product_selected;
        private final String product_price;
        private final  String product_image_url ;
        private  Integer selected;
        private orderedItem(  String Product_ID,String Product_Name ,String Product_Price,String Product_Total_Available,String Product_Description,String Product_Image_URL) {

            this.product_ID=Product_ID;
            this.product_name = Product_Name;
            this.product_description =Product_Description;
            this.product_selected =Product_Total_Available;
            this.product_price =Product_Price;
            this.product_image_url =Product_Image_URL;
            this.selected=0;
        }




        public  String getProduct_description() {
            return product_description;
        }

        public String getProduct_ID() {
            return product_ID;
        }

        public  String getProduct_image_url() {
            return product_image_url;
        }

        public  String getProduct_name() {
            return product_name;
        }

        public  String getProduct_price() {
            return product_price;
        }

        public String getProduct_selected() {
            return product_selected;
        }

    }



public void backbuttonpressed(ActionEvent e ) throws IOException {
    String s1="searchpage.fxml";
    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
    Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
    Scene scene = new Scene(fxmlLoader.load(), 720, 480);
    stage.setTitle("e-MED");
    stage.setScene(scene);
    stage.show();

}




}