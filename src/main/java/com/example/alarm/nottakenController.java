package com.example.alarm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class nottakenController implements Initializable
{
    public static boolean takenPressed = false;
    public static demoinfo sendObj = new demoinfo();
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

    @FXML
    protected void TakenPressed(ActionEvent event) throws IOException
    {
        takenPressed = true;
        sendObj.remtimee = medicinetime.getText();
        sendObj.m_name = medicinename.getText();
        sendObj.doses = Integer.parseInt(medicinedescription.getText());
        DBConn db = new DBConn();
        CurrentTime ctime = new CurrentTime();
        db.setTaken(medicinename.getText(), medicinetime.getText(), medicinedescription.getText(), ctime.currentweekday());
        VBox vb = (VBox) this.ancpane.getParent();
        vb.getChildren().remove(this.ancpane);

    }
}
