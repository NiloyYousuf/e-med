
package com.example.alarm;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ButtonNotificationExample  {

    public void showNotificationorderPlaced(Stage primaryStage) {
        Text text = new Text("Order Placed");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        text.setFill(Color.WHITE);

        StackPane root = new StackPane(text);
        root.setStyle("-fx-background-color: #4CAF50; -fx-padding: 10;");
        root.setPrefWidth(200);
        root.setPrefHeight(50);

        Scene scene = new Scene(root);

        Stage notificationStage = new Stage();
        notificationStage.initStyle(StageStyle.UNDECORATED); // set the style to undecorated
        notificationStage.setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        notificationStage.setX(660);
        notificationStage.setY(50);
        notificationStage.setResizable(false);
        notificationStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                notificationStage.close();
            }
        }));
        timeline.play();
    }
    public void showNotificationEditedSuccessfully(Stage primarystage)
    {
        Text text = new Text("Product Edited");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        text.setFill(Color.WHITE);

        StackPane root = new StackPane(text);
        root.setStyle("-fx-background-color: #4CAF50; -fx-padding: 10;");
        root.setPrefWidth(200);
        root.setPrefHeight(40);

        Scene scene = new Scene(root);

        Stage notificationStage = new Stage();
        notificationStage.initStyle(StageStyle.UNDECORATED); // set the style to undecorated
        notificationStage.setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        notificationStage.setX(660);
        notificationStage.setY(50 );
        notificationStage.setResizable(false);
        notificationStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                notificationStage.close();
            }
        }));
        timeline.play();

    }


    public void showNotificationProductAddedSuccessfully(Stage primaryStage) {
        Text text = new Text("Product Added Successfully");
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        text.setFill(Color.WHITE);

        StackPane root = new StackPane(text);
        root.setStyle("-fx-background-color: #4CAF50; -fx-padding: 10;");
        root.setPrefWidth(200);
        root.setPrefHeight(50);

        Scene scene = new Scene(root);

        Stage notificationStage = new Stage();
        notificationStage.initStyle(StageStyle.UNDECORATED); // set the style to undecorated
        notificationStage.setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        notificationStage.setX(660);
        notificationStage.setY(50);
        notificationStage.setResizable(false);
        notificationStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                notificationStage.close();
            }
        }));
        timeline.play();
    }

}
