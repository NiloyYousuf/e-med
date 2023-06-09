package com.example.alarm;

import com.example.alarm.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import  javafx.scene.Parent;
import javafx.scene.Scene;
import  javafx.stage.Stage;
import  javafx.scene.control.TextField;
import  javafx.scene.control.PasswordField;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javafx.util.Pair;
public class LoginStaringPagecontroller {
    @FXML
    private Label welcomeText;

    @FXML
    public static Boolean monthlypressed;

    @FXML
    private TextField name;


    @FXML
    private  PasswordField passwordField;

    public LoginStaringPagecontroller() {
    }

   // @FXML
   // protected void onHelloButtonClick() {
   //     welcomeText.setText("Welcome to JavaFX Application!");
   // }



    @FXML
    private  TextField namefield;
    private  Stage stage;
    private  Scene scene;
    private Parent root;
    public void switchToScene2(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("RegisterpageD.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    private  Label unmatchedMessage;

    public void switchToScene3(ActionEvent event,String username) throws IOException {
        String s1="UserLoggedInprevious.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private  Button goshoppingbutton;

    public void switchToScene4(ActionEvent event) throws IOException {
        String s1="searchpage.fxml";
        monthlypressed = false;
        System.out.println("Here is shopping");
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();

    }






    public  ArrayList<Pair<String,String>> string_processing(String s1)
    {
        ArrayList<Pair<String,String>> array_of_product=new ArrayList<Pair<String,String>>();
        Pair<String,String>p=new Pair<>("s1","description");
        array_of_product.add(1,p);

        // search the string in the database return arrays of product names and the location of the picture of the products

        return  array_of_product;
    }



    @FXML
    public void switchtoalaram(ActionEvent event) throws IOException {
        String s1="remview2.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchtoMonthly(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        monthlypressed = true;
        Database_connection dbconn = new Database_connection();
        Connection conn = dbconn.conn;
        String s1 = new String();
        try {

            Statement stmt = ((java.sql.Connection) conn).createStatement();
            PreparedStatement ps = conn.prepareStatement("select * from monthly_subscription where User_name = ?");
            ps.setString(1, currentUser.user_name);
            ResultSet rs = ps.executeQuery();

            if (rs.next() == Boolean.TRUE) {
                s1="showMontlySubpage.fxml";
            } else {

                s1="nomonthlysub.fxml";
            }
            stmt.close();
            ((java.sql.Connection) conn).close();


        } catch (SQLException e) {
            System.out.println(" Error while connecting to database. Exception code : " + e);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(MonthlySubscriptionPage.class.getResource(s1));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }

    public void login_button_click(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.println(name.getText());

        if(name.getText().isBlank()==false && passwordField.getText().isBlank()==false )
        {
          /*  if(name.getText().equals("Niloy") && passwordField.getText().equals("123"))  /// here the databases users has to be connected
            {

            }
            else
            System.out.println("Successfull");
*/

           String Name=name.getText();
           System.out.println(Name );
            String Password=passwordhasher.hash(passwordField.getText());
            System.out.println(Password);
            table obj = new table();
          // obj.insert_val("niloyfdfrry", "12tr3", "0171", "email");
           boolean output = obj.login(Name, Password);

          if (output) {
              currentUser.user_name=Name;
              switchToScene3(event, Name);
          }
           else
              unmatchedMessage.setText(" Username and Password don't Match");

        }

       else unmatchedMessage.setText("Please Enter Username and Password");

    }


    @FXML
    private  Label regunmatchedMessage;
    @FXML
    private  TextField regname;
    @FXML
    private PasswordField regpassword;
    @FXML
    private  TextField regmail;
    @FXML
    private  TextField regphone;


    @FXML
    private Button Logout;


    @FXML
    public void logoutbuttonclicked(ActionEvent event) throws IOException {
        String s1="loginpage.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();

    }

    public void register_button_click(ActionEvent event) throws IOException, SQLException, ClassNotFoundException{

            System.out.println("clicked");
            String u_name,key,mob_no,mail_id;
            u_name=regname.getText();
            key=regpassword.getText();
            mob_no=regphone.getText();
            mail_id=regmail.getText();
            table obj=new table();
            boolean inserted=false;
            boolean allnotfilled=false;
            if(u_name.equals("") || key.equals("")||mob_no.equals("")||mail_id.equals(""))
            {
                regunmatchedMessage.setText("Fillup All the Fields");
                allnotfilled=true;
            }
            else {
                inserted= obj.insert_val(u_name, key, mob_no, mail_id);
            }

            if(inserted==true)
            {
                System.out.println("Inserted");
                currentUser.user_name=u_name;
                switchToScene3(event,u_name);
            }

            else if(allnotfilled==false)
            {
               regunmatchedMessage.setText(" Username Already Exists");
                System.out.println("could not be Inserted");

            }



    }

    @FXML
    protected void onPreviousOrdersClicked(ActionEvent event) throws IOException
    {
        String s1="user_previous_orders.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void baccpressed(ActionEvent event) throws IOException {
        String s1="UserLoggedInprevious.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }



}