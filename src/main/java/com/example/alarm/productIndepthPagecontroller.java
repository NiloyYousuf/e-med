package com.example.alarm;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class productIndepthPagecontroller {
    @FXML
    private  ImageView product_depth_image;
    @FXML
    private Button plusbutton;

    @FXML
    private  Button minusButton;

    @FXML
    private Label priceLabel;
    @FXML
    private  Label DescriptionLabel;

    @FXML
    private  Label Product_Name;
    @FXML
    private Button back_button;

    @FXML
    private  Label total_selected;

    public productIndepthPagecontroller(String ImageURL,String ProductID,String ProductPrice,String ProductName,String ProductDescription,String ProductTotalAvailable  )
    {
        product_depth_image=new ImageView(ImageURL);
        priceLabel.setText(ProductPrice);
        Product_Name.setText(ProductName);
        DescriptionLabel.setText(ProductDescription);
        total_selected.setText("0");
    }

}
