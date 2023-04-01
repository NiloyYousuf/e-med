package com.example.alarm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductViewController implements Initializable {

    @FXML
    private AnchorPane ancpane;
    @FXML
    private Button decreasebtn;

    @FXML
    private Button increasebtn;

    @FXML
    private Label medicinedescription;

    @FXML
    private Label medicinename;

    @FXML
    private Label medicineprice;

    @FXML
    private ImageView medimg;

    @FXML
    private Label quantity;

    @FXML
    private Label ID;

    User_ADD_TO_CART_backup ud = new User_ADD_TO_CART_backup();
    //public static Item itm = ud.obj;
    public static Item itm = User_ADD_TO_CART_backup.obj;

    public static int total;
    public static int current;

    public static String prodid = new String();

    public ProductViewController()
    {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        List<Item> itemList = ud.getArr();

        medicinedescription.setText(itm.getProduct_description());
        quantity.setText(String.valueOf(itm.getSelected()));
        medicinename.setText(itm.getProduct_name());
        medicineprice.setText("Price: " + itm.getProduct_price());
        medimg.setImage(new Image(itm.getProduct_image_url()));
        ID.setText(itm.getProduct_ID());

        System.out.println(total);

    }

    @FXML
    public void decrease()
    {
        if(!quantity.getText().equals("0")) {
            quantity.setText(Integer.toString(Integer.parseInt(quantity.getText()) - 1));
            total--;
            itm.setSelected(itm.getSelected()-1);
            //User_ADD_TO_CART_backup.obj = itm;
            prodid = ID.getText();
            System.out.println(medicinename.getText() + quantity.getText() + prodid);
            current = Integer.parseInt(quantity.getText());
            //item.selected--;
        }

    }

    @FXML
    public void increase()
    {
        quantity.setText(Integer.toString(Integer.parseInt(quantity.getText()) + 1));
        total++;
        itm.setSelected(itm.getSelected()+1);
        prodid = ID.getText();
        current = Integer.parseInt(quantity.getText());
    }

}
