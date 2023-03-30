package com.example.alarm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginpage {
    @FXML
    private Button loginbutton;
    @FXML
    private  TextField name;
    @FXML
    private Label unmatchedMessage;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Label failed;

    public void switchToScene3(ActionEvent event) throws IOException {
        String s1="AdminAddProduct.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 720, 480);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();

    }


    @FXML
    public  void loginbuttonclicked(ActionEvent event) throws IOException {
       if(name.getText().equals("Niloy") && passwordField.getText().equals("123"))
       {

           switchToScene3(event);
       }

       else {
           failed.setText("Invalid Admin ID And Password");
       }
    }
}
