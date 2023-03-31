package com.example.alarm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class productIndepthPagecontroller {
    @FXML
    public  static   ImageView product_depth_image=new ImageView();
    @FXML
    public static Button plusbutton=new Button("lol");

    @FXML
    public static  Button minusButton;

    @FXML
    public static Label priceLabel=new Label();
    @FXML
    public static  Label DescriptionLabel=new Label();

    @FXML
    public static   Label Product_Name=new Label();
    @FXML
    private Button back_button;

    @FXML
    private  Label total_selected;
    @FXML
    public static  Label ProductID;

    public productIndepthPagecontroller(String ImageURL,String ProductID,String ProductPrice,String ProductName,String ProductDescription,String ProductTotalAvailable  ) throws IOException {

        Product_Name.setText("lol");
        System.out.println(Product_Name.getText());
        System.out.println(ProductName);
        DescriptionLabel.setText(ProductDescription);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("productdepthpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        Stage stage=new Stage();
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
        System.out.println("HiNoliAmiAudhi");
        product_depth_image=new ImageView(ImageURL);






    }
    public  productIndepthPagecontroller()
    {
priceLabel.setText("lokl");
    }


    public void setuptheproductpage()
    {
        Product_Name.setText("lolol");
    }
}
