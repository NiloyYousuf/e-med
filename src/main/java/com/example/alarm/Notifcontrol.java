package com.example.alarm;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Notifcontrol implements Initializable {

    @FXML
    private Label timez;
    @FXML
    private Label doseg;
    @FXML
    private Label medname;

    @FXML
    private Button stop;
    @FXML
    private Button taken;

    @FXML
    private ImageView img;

    private String weekde = new String();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        MedicinePageController hc = new MedicinePageController();
        notifobject nob = hc.getNob();

        timez.setText(nob.getTimez());
        doseg.setText(String.valueOf(nob.getDos()) + " " + nob.getDosunit());
        medname.setText(nob.getMednem());
        weekde = nob.getWeekde();
        //if(nob.getType() == "Liquid") img.setImage(new Image("/com/example/alarm/liquid.png"));
        //else if(nob.getType() == "Capsule") img.setImage(new Image("tablet.png"));
        //else if(nob.getType() == "Pills") img.setImage(new Image("pills.png"));
        //else if(nob.getType() == "Syringe") img.setImage(new Image("syringe.png"));

        Media ple = new Media(new File("who-let-the-dogs-out-4720.mp3").toURI().toString());
        //Media ple = new Media(new File("twirling-intime-lenovo-k8-note-alarm-tone-41440.mp3").toURI().toString());
        MediaPlayer mp = new MediaPlayer(ple);
        mp.play();

        stop.setOnAction(event -> {mp.stop();});

    }

    @FXML
    protected void onTakenPressed()
    {
        DBConn db = new DBConn();
        db.setTaken(medname.getText(), timez.getText(), doseg.getText().toString().split(" ")[0], weekde);

    }

    @FXML
    protected void onStopAlarmPressed()
    {

    }


}
