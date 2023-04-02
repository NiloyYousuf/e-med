package com.example.alarm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminShowAllProducts implements Initializable {

    @FXML
    private VBox itemContainer;

    @FXML
    private Button Cart;

    @FXML
    private Label cartlabel;
    private List<Item> itemList = new ArrayList<>();


    @FXML public  void Switch_To_admin_Menu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("AdminMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        table2 connecttotable=new table2();
        ArrayList<Product> products=connecttotable.getProductlist();


        for (int i=0;i<products.size();i++)
            itemList.add(new Item(products.get(i).Product_Name,products.get(i).Product_Description,products.get(i).Product_ID,products.get(i).Product_Price,products.get(i).Product_Total_Available,products.get(i).Product_Image_URL));


        // Add the items to the item container
        for (Item item : itemList) {


            HBox itemBox = new HBox(30);
            Image image = new Image(item.getProduct_image_url());
            ImageView imageView =new ImageView();
            imageView.setImage(image);
            imageView.setFitHeight(90);
            imageView.setFitWidth(90);
            Label descriptionLabel = new Label("Medicine Description : "+item.getProduct_description());
            Label pricelabel=new Label("Medicine Price : "+item.getProduct_price());
            Label product_ID=new Label("Product ID : "+item.getProduct_ID() );
            descriptionLabel.setTextAlignment(TextAlignment.LEFT);
            descriptionLabel.setWrapText(true);
            descriptionLabel.setMinSize(560,100);
            Label nameLabel = new Label("Medicine name : "+item.getProduct_name());
            VBox NameAndDesc=new VBox(product_ID,nameLabel,pricelabel,descriptionLabel);
            Button deleetebutton=new Button("Delete");
            deleetebutton.minWidth(10);

          deleetebutton.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white;");
          deleetebutton.setOnMouseEntered(e -> deleetebutton.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white;"));
          deleetebutton.setOnMouseExited(e -> deleetebutton.setStyle("-fx-background-color: #F74C3C; -fx-text-fill: white;"));
          deleetebutton.setOnAction(event -> deleteProduct(item.getProduct_ID()));

          descriptionLabel.setAlignment(Pos.TOP_LEFT);

          Button editbutton=new Button(" Edit ");
         deleetebutton.setMinSize(50,20);
          editbutton.setMinSize(50,20);
          editbutton.setOnAction(event -> editproduct(item.product_ID));

            editbutton.setStyle("-fx-background-color: #918987; -fx-text-fill: white;");
            editbutton.setOnMouseEntered(e -> editbutton.setStyle("-fx-background-color: #918987; -fx-text-fill: white;"));
            editbutton.setOnMouseExited(e -> editbutton.setStyle("-fx-background-color: #7B7675; -fx-text-fill: white;"));

VBox vbox=new VBox(deleetebutton,editbutton);

            NameAndDesc.setStyle("-fx-background-color:#F6E3BA;");
            itemBox.getChildren().addAll(new HBox(imageView,NameAndDesc,vbox));
            itemContainer.getChildren().add(itemBox);


        }


    }

    public void editproduct(String productID)
    {
        ProductEditor p=new ProductEditor();
        p.start(new Stage(),productID);
    }


    public void deleteProduct(String productId) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            System.out.println(productId);
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");
                 PreparedStatement stmt = conn.prepareStatement("DELETE FROM product_table WHERE Product_ID = ?")) {
                stmt.setString(1, productId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("COnnection failed");
                e.printStackTrace();
            }
        }
    }


    private static class Item {
        private final String product_name;
        private final String product_description;
        private final String product_ID;
        private final String product_total_available;
        private final String product_price;
        private final String product_image_url;
        public Item(String name, String description, String ID, String Total, String price, String image_url) {
            product_name = name;
            product_description = description;
            product_ID = ID;
            product_total_available = Total;
            product_price = price;
            product_image_url = image_url;
        }



        public String getProduct_description() {
            return product_description;
        }

        public String getProduct_ID() {
            return product_ID;
        }

        public String getProduct_image_url() {
            return product_image_url;
        }

        public String getProduct_name() {
            return product_name;
        }

        public String getProduct_price() {
            return product_price;
        }

        public String getProduct_total_available() {
            return product_total_available;
        }

    }



}