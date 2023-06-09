package com.example.alarm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Alarmview extends Application
{

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Alarmview.class.getResource("remview2.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("e-Med Reminder");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
