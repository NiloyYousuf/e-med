package com.example.alarm;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class takenController implements Initializable
{
    @FXML
    private Label ID;

    @FXML
    private AnchorPane ancpane;

    @FXML
    private Label medicinedescription;

    @FXML
    private Label medicinename;

    @FXML
    private Label medicinetime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("heree" + TodaySmeds.obj.m_name + TodaySmeds.obj.doses + TodaySmeds.obj.remtimee);
        //System.out.println(llst[hc.num].m_name);
        medicinename.setText(TodaySmeds.obj.m_name);
        medicinetime.setText(TodaySmeds.obj.remtimee);
        medicinedescription.setText(String.valueOf(TodaySmeds.obj.doses));

    }
}
