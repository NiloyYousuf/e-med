package com.example.alarm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Admin_Add_Product_Controller {

    @FXML
    private Button submit_button;

    @FXML
    private Label welcomeText;

    @FXML
    public TextField productname;
    @FXML
    public TextField productId;

    @FXML
    public TextField productprice;

    @FXML
    public TextField TotalAvailable;

    @FXML
    public TextField productdescription;

    @FXML
    public TextField productimageURL;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }




    @FXML public  void Switch_To_admin_Menu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("AdminMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();

    }


    public void insert_into_database() {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Are you sure you want to make entry in database");
        Optional<ButtonType> result = alert.showAndWait();


        if (result.get() == ButtonType.OK) {
            String product_name, product_id, total_available, product_price, product_description, product_image_url;
            product_name = productname.getText();
            product_id = productId.getText();
            total_available = TotalAvailable.getText();
            product_price = productprice.getText();
            product_description = productdescription.getText();
            product_image_url = productimageURL.getText();
            table2 obj = new table2();
            obj.insert_val2(product_id, product_price, product_name, total_available, product_description, product_image_url);
            ButtonNotificationExample btn=new ButtonNotificationExample();
            btn.showNotificationProductAddedSuccessfully(new Stage());
        }

    }

    @FXML
    private ImageView imageView;

    public void selectImage() {
        Stage stage = (Stage) imageView.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        }
        productimageURL.setText(file.toURI().toString());


    }

}