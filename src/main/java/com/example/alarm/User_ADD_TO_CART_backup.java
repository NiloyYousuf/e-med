package com.example.alarm;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


public class User_ADD_TO_CART_backup implements Initializable {

    @FXML
    private VBox itemContainer = null;

    @FXML
    private  Button Cart;

    @FXML
    public Label cartlabel;

    private List<Item> itemList = new ArrayList<>();




    cart products_added_to_cart;
    ArrayList<Product> itemsadded;

    public static Item obj = new Item();
    public static int num = 0;

    public List<Item> getArr()
    {
        return itemList;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the item list

        //cartlabel.setText("0");
        for (int i=0;i<products.products.size();i++)
            itemList.add(new Item(products.products.get(i).Product_ID,products.products.get(i).Product_Name,products.products.get(i).Product_Price,products.products.get(i).Product_Total_Available,products.products.get(i).Product_Description,products.products.get(i).Product_Image_URL));


        //   itemList.add(new Item("Item 5", "This is item 5", "C://Users//yousu//IdeaProjects//scrollingfxml//src//main//resources//com//example//img.png"));
        Node[] nodes = new Node[itemList.size()];
        int i = 0;
        ProductViewController.total = 0;
        System.out.println("ekhane" + ProductViewController.total);

        for(Item item : itemList)
        {
            try {

                final int j = i;
                num = i;

                obj.setProduct_image_url(item.getProduct_image_url());
                obj.setProduct_description(item.getProduct_description());
                obj.setProduct_ID(item.getProduct_ID());
                obj.setProduct_name(item.getProduct_name());
                obj.setProduct_price(item.getProduct_price());
                obj.setSelected(item.getSelected());


                nodes[i] = FXMLLoader.load(getClass().getResource("productview.fxml"));

                //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : rgba(162,132,162,0.76)");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #fdfdff");
                });
                itemContainer.getChildren().add(nodes[i]);
                i++;

            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("ekhane" + ProductViewController.total);
        }

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        System.out.println("ekhane" + ProductViewController.total);

        //cartlabel.setText(str);
        //cartlabel.setText(Integer.toString(num));
    }

    Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(1),
                    e -> {
                updateCart();
                cartlabel.setText(Integer.toString(ProductViewController.total));
                for(Item item : itemList)
                {
                    System.out.println(item.getSelected() + " " + item.getProduct_name());
                }
                    }));

    public void updateCart()
    {
        for(Item item : itemList)
        {
            System.out.println(ProductViewController.prodid);
            if(ProductViewController.prodid == item.getProduct_ID())
            {
                item.setSelected(ProductViewController.current);
            }
        }

        //cartlabel.setText(Integer.toString(ProductViewController.total));

        System.out.println("inside update: " + ProductViewController.total);
    }


    public void cartpressed(ActionEvent e) throws IOException {
        System.out.println(itemList.size());
        for(int i=0;i<itemList.size();i++)
        {
            int k=0;

            if(itemList.get(i).getSelected()!=0)
            {
                //System.out.println(itemList.get(i).product_name+"   "+itemList.get(i).selected + " " +itemList.get(i).product_price);
                products_added_to_cart.Total_Amount+= itemList.get(i).getSelected() *Double.parseDouble(itemList.get(i).getProduct_price());
              //  products_added_to_cart.products.add(new Product(itemList.get(i).product_ID,itemList.get(i).product_name,itemList.get(i).product_price,itemList.get(i).product_total_available,itemList.get(i).getProduct_description(), itemList.get(i).getProduct_image_url() ));
                Product tempproduct=new Product(itemList.get(i).getProduct_ID(), itemList.get(i).getProduct_name(), itemList.get(i).getProduct_price(), itemList.get(i).getProduct_total_available(),itemList.get(i).getProduct_description(), itemList.get(i).getProduct_image_url(), itemList.get(i).getSelected());
                products_added_to_cart.Products.add(tempproduct);
                products_added_to_cart.total_items_selected+= itemList.get(i).getSelected();
            }
        }


        for(int i=0;i<products_added_to_cart.Products.size();i++)
        {
            System.out.println(products_added_to_cart.Products.get(i).Product_ID + products_added_to_cart.Products.get(i).Addedtocart);
        }

        switchtoOrderPage(e);

    }


    public  void backbuttonpressed(ActionEvent event) throws IOException {
        String s1="UserLoggedIn.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }


    /*private class Item {
        private String product_name;
        private String product_description;
        private String product_ID;
        private String product_total_available;
        private String product_price;
        private String product_image_url;
        private  Integer selected;
        private Item(  String Product_ID,String Product_Name ,String Product_Price,String Product_Total_Available,String Product_Description,String Product_Image_URL) {

            this.product_ID=Product_ID;
            this.product_name = Product_Name;
            this.product_description =Product_Description;
            this.product_total_available =Product_Total_Available;
            this.product_price =Product_Price;
            this.product_image_url =Product_Image_URL;
            this.selected=0;
        }

        private Item()
        {

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

    }*/


    public void Hboxclicked(String product_image_url) throws IOException {
     System.out.println("Hobox Clicked");
        String s1="ProductdepthPage.fxml";
        new productIndepthPagecontroller(product_image_url,"a ", " d", " d", " d"," 1");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }



    public void switchtoOrderPage(ActionEvent e) throws IOException {
        String s1="orderpage.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }







    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Database_connection dbcon = new Database_connection();
            Connection conn=dbcon.conn;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product_table");

            while (rs.next()) {
                Product product = new Product(
                        rs.getString("Product_ID"),
                        rs.getString("Product_Name"),
                        rs.getString("Product_Price"),
                        rs.getString("Total_Avaiable"),
                        rs.getString("Product_Description"),
                        rs.getString("Image_URL"),
                        0
                );
                products.add(product);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return products;
    }


    public ArrayList<Product> searchProducts(String searchStr) {
        ArrayList<Product> results = new ArrayList<>();
        try {
            Database_connection dbcon = new Database_connection();
            Connection conn=dbcon.conn;
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM product_table WHERE Product_Name LIKE ?");
            stmt.setString(1, "%" + searchStr + "%");
            ResultSet rs = stmt.executeQuery();

            /*while (rs.next()) {
                Product product = new Product(
                        rs.getString("Product_ID"),
                        rs.getString("Product_Name"),
                        rs.getString("Product_Price"),
                        rs.getString("Total_Avaiable"),
                        rs.getString("Product_Description"),
                        rs.getString("Image_URL"),
                        0
                );
                results.add(product);
            }*/

            // Find products with similar names using Levenshtein distance algorithm
            for (Product product : getAllProducts()) {
                if (isSimilar(searchStr, product.getProduct_Name())) {
                    results.add(product);
                }
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return results;
    }

    private boolean isSimilar(String searchStr, String productName) {
        int n = searchStr.length();
        int m = productName.length();

        // Check for exact match
        if (searchStr.equals(productName)) {
            return true;
        }

        // Check for permutation
        if (n == m) {
            char[] a = searchStr.toCharArray();
            char[] b = productName.toCharArray();
            Arrays.sort(a);
            Arrays.sort(b);
            if (Arrays.equals(a, b)) {
                return true;
            }
        }

        // Check for missing 1 or 2 characters
        if (n == m - 1 || n == m - 2) {
            int i = 0, j = 0;
            while (i < n && j < m) {
                if (searchStr.charAt(i) == productName.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == n) {
                return true;
            }
            i = 0; j = 0;
            while (i < n && j < m) {
                if (searchStr.charAt(i) == productName.charAt(j)) {
                    i++;
                    j++;
                } else {
                    j++;
                }
            }
            if (i == n) {
                return true;
            }
        }

        // Check for prefix or suffix
        if (productName.startsWith(searchStr) || productName.endsWith(searchStr)) {
            return true;
        }

        // Check for substring
        if (productName.contains(searchStr)) {
            return true;
        }

        // No match found
        return false;
    }


    @FXML
    private TextField searchtextfield;

    public  void searchbuttonclicked(ActionEvent event) throws IOException {

        String stringToCompare=searchtextfield.getText();
        products.products=searchProducts(stringToCompare);

        String s1="searchpage.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
        products.products= products.connecttotable.getProductlist();

    }







}