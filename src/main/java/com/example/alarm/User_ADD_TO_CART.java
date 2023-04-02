package com.example.alarm;
import java.util.Arrays;
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
import  java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class User_ADD_TO_CART implements Initializable {

    @FXML
    private VBox itemContainer;

    @FXML
    private  Button Cart;

    @FXML
    private  Label cartlabel;

    private List<Item> itemList = new ArrayList<>();




    cart products_added_to_cart;
    ArrayList<Product> itemsadded;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the item list


        for (int i=0;i<products.products.size();i++)
            itemList.add(new User_ADD_TO_CART.Item(products.products.get(i).Product_ID,products.products.get(i).Product_Name,products.products.get(i).Product_Price,products.products.get(i).Product_Total_Available,products.products.get(i).Product_Description,products.products.get(i).Product_Image_URL,products.products.get(i).Addedtocart));


        //   itemList.add(new Item("Item 5", "This is item 5", "C://Users//yousu//IdeaProjects//scrollingfxml//src//main//resources//com//example//img.png"));

        // Add the items to the item container
        for (Item item : itemList) {
            HBox itemBox = new HBox(30);



            ImageView imageView = new ImageView(item.getProduct_image_url());
            imageView.setFitWidth(160);
            imageView.setFitHeight(120);
            Label descriptionLabel = new Label(item.getProduct_description());
            Label pricelabel=new Label("Price: "+item.getProduct_price());
            descriptionLabel.setMinSize(150,50);

            descriptionLabel.setWrapText(true);
            Label nameLabel = new Label(item.getProduct_name());
            VBox NameAndDesc=new VBox(nameLabel,pricelabel,descriptionLabel);
            NameAndDesc.setStyle("-fx-background-color:#F6E3BA;");
            Button plusBtn =new Button("+");

            // button.setStyle("-fx-background-image: url('file:///C:/Users/yousu/IdeaProjects/PRACTICE/src/main/resources/com/example/practice/plus.png');");

            plusBtn.setStyle("-fx-background-radius:10; -fx-font-size: 10pt; -fx-padding: 8px 15px;");

            plusBtn.setStyle("-fx-background-color: #58D88D; -fx-text-fill: white;");
            plusBtn.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            plusBtn.setEffect(new DropShadow(10, Color.BLACK));

            plusBtn.setOnMouseEntered(e -> plusBtn.setStyle("-fx-background-color: #58D88D; -fx-text-fill: white;"));
            plusBtn.setOnMouseExited(e -> plusBtn.setStyle("-fx-background-color: #58DF9D; -fx-text-fill: white;"));


            Button minusBtn = new Button("-");

            minusBtn.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white;");
            minusBtn.setFont(Font.font("Arial", FontWeight.BOLD, 18));
            minusBtn.setEffect(new DropShadow(10, Color.BLACK));

            minusBtn.setOnMouseEntered(e -> minusBtn.setStyle("-fx-background-color: #E74C3C; -fx-text-fill: white;"));
            minusBtn.setOnMouseExited(e -> minusBtn.setStyle("-fx-background-color: #F74C3C; -fx-text-fill: white;"));


            HBox hbox = new HBox();
            hbox.setSpacing(10);
            hbox.setAlignment(Pos.CENTER);
            hbox.setPadding(new Insets(2));
            Label Quantity=new Label();
            Quantity.setText(Product.getAddedToCartValue(item.product_ID).toString());;
            hbox.getChildren().addAll(plusBtn,Quantity ,minusBtn);



            //  button.setOnAction(event -> Quantity.setText(Integer.toString(Integer.parseInt(Quantity.getText())+1)));
            // button1.setOnAction(event -> Quantity.setText(Integer.toString(Integer.parseInt(Quantity.getText())-1)));
            minusBtn.setOnAction(event ->decrease(minusBtn,Quantity,item));
            plusBtn.setOnAction(event ->increase(plusBtn,Quantity,item));
            NameAndDesc.setMinWidth(420);
            plusBtn.setMinSize(50,30);
            minusBtn.setMinSize(50,30);
            plusBtn.onMouseClickedProperty();
            itemBox.getChildren().addAll(new HBox(imageView,NameAndDesc,hbox));
            itemContainer.getChildren().add(itemBox);
        }

        cartlabel.setText(String.valueOf(cart.total_items_selected));

        System.out.println(cart.total_items_selected);


    }



    public void decrease(Button button1, Label Quantity,Item item)
    {
        if(!Quantity.getText().equals("0")) {
            Quantity.setText(Integer.toString(Integer.parseInt(Quantity.getText()) - 1));
            cartlabel.setText(Integer.toString(Integer.parseInt(cartlabel.getText()) - 1));
            cart.total_items_selected--;
            item.selected--;
        }

    }

    public  void increase(Button button1, Label Quantity,Item item)
    {

        Quantity.setText(Integer.toString(Integer.parseInt(Quantity.getText())+1));
        cartlabel.setText(Integer.toString(Integer.parseInt(cartlabel.getText())+1));
        cart.total_items_selected++;
        item.selected++;
    }






    public void cartpressed(ActionEvent e) throws IOException {
        for(int i=0;i<itemList.size();i++)
        {
            int k=0;

            if(itemList.get(i).selected!=0)
            {
                //System.out.println(itemList.get(i).product_name+"   "+itemList.get(i).selected + " " +itemList.get(i).product_price);
                products_added_to_cart.Total_Amount+=itemList.get(i).selected*Double.parseDouble(itemList.get(i).product_price);
              //  products_added_to_cart.products.add(new Product(itemList.get(i).product_ID,itemList.get(i).product_name,itemList.get(i).product_price,itemList.get(i).product_total_available,itemList.get(i).getProduct_description(), itemList.get(i).getProduct_image_url() ));
                Product tempproduct=new Product(itemList.get(i).product_ID,itemList.get(i).product_name,itemList.get(i).product_price,itemList.get(i).product_total_available,itemList.get(i).getProduct_description(), itemList.get(i).getProduct_image_url(), itemList.get(i).selected);
                products_added_to_cart.Products.add(tempproduct);
                products_added_to_cart.total_items_selected+= itemList.get(i).selected;
            }
        }


        for(int i=0;i<products_added_to_cart.Products.size();i++)
        {
            System.out.println(products_added_to_cart.Products.get(i).Product_ID + products_added_to_cart.Products.get(i).Addedtocart);
        }

        if(LoginStaringPagecontroller.monthlypressed == Boolean.TRUE)
        {
            cart_monthly.Products = products_added_to_cart.Products;
            switchtoMonthlyrPage(e);
        }
        else switchtoOrderPage(e);


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


    private class Item {
        private final String product_name;
        private final String product_description;
        private final String product_ID;
        private final String product_total_available;
        private final String product_price;
        private final String product_image_url;
        private  Integer selected=0;
        private Item(  String Product_ID,String Product_Name ,String Product_Price,String Product_Total_Available,String Product_Description,String Product_Image_URL,Integer selected) {

            this.product_ID=Product_ID;
            this.product_name = Product_Name;
            this.product_description =Product_Description;
            this.product_total_available =Product_Total_Available;
            this.product_price =Product_Price;
            this.product_image_url =Product_Image_URL;
            this.selected=0;
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





    public void switchtoOrderPage(ActionEvent e) throws IOException {
        String s1="orderpage.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 528);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }

    public void switchtoMonthlyrPage(ActionEvent e) throws IOException {
        String s1="monthlysubscriptionpage.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 528);
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
                results.add(product);
            }

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
        if(searchStr.equals(productName))
            return false;
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