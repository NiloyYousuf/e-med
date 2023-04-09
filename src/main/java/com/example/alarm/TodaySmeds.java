package com.example.alarm;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TodaySmeds implements Initializable {

    DBConn db = new DBConn();
    @FXML
    private VBox nottaken = null;

    @FXML
    private VBox taken =  null;

    @FXML
    private Label timez;

    public static int num = 0;

    public static demoinfo obj = new demoinfo();

    public TodaySmeds()
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ArrayList<demoinfo> list = db.getTakenTable(1);
        //llst = db.forDisplay();
        Node[] nodes = new Node[list.size()];

        System.out.println(list.size());

        for (int i = 0; i < list.size(); i++) {

            try {

                final int j = i;
                num = i;

                obj.m_name = list.get(i).m_name;
                obj.remtimee = list.get(i).remtimee;
                obj.doses = list.get(i).doses;



                nodes[i] = FXMLLoader.load(getClass().getResource("takenview.fxml"));

                    //give the items some effect

                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : rgba(162,132,162,0.76)");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #fdfdff");
                });
                    taken.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        list.clear();
        list = db.getTakenTable(0);
        Node[] nodes1 = new Node[list.size()];

        for (int i = 0; i < list.size(); i++) {

            try {

                final int j = i;
                num = i;

                obj.m_name = list.get(i).m_name;
                obj.remtimee = list.get(i).remtimee;
                obj.doses = list.get(i).doses;



                nodes1[i] = FXMLLoader.load(getClass().getResource("nottakenview.fxml"));

                //give the items some effect

                nodes1[i].setOnMouseEntered(event -> {
                    nodes1[j].setStyle("-fx-background-color : rgba(162,132,162,0.76)");
                });
                nodes1[i].setOnMouseExited(event -> {
                    nodes1[j].setStyle("-fx-background-color : #fdfdff");
                });
                nottaken.getChildren().add(nodes1[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        e ->  {
                            if(nottakenController.takenPressed == true)
                            {
                                obj = nottakenController.sendObj;
                                try {
                                    taken.getChildren().add((Node) FXMLLoader.load(getClass().getResource("takenview.fxml")));
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }

                                nottakenController.takenPressed = false;

                            }
                        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    @FXML
    public void onBackPressed(ActionEvent event) throws IOException
    {
        Node root = (Node) event . getSource () ;
        Stage myStage = ( Stage ) root . getScene () . getWindow () ;
        FXMLLoader fxmlLoader = new FXMLLoader ( MedicinePageController.class.getResource ("remview2.fxml") ) ;
        Scene as = new Scene ( fxmlLoader.load() ) ;
        myStage . setScene ( as ) ;
        myStage . show () ;
    }

    public void onTakenPressed(ActionEvent event) throws IOException
    {
        Node root = (Node) event . getSource () ;
        Stage myStage = ( Stage ) root . getScene () . getWindow () ;
        FXMLLoader fxmlLoader = new FXMLLoader ( MedicinePageController.class.getResource ("today'smeds.fxml") ) ;
        Scene as = new Scene ( fxmlLoader.load() ) ;
        myStage . setScene ( as ) ;
        myStage . show () ;
    }


}
