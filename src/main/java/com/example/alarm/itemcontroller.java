package com.example.alarm;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class itemcontroller implements Initializable
{
    @FXML
    private Label alarmtime = new Label();
    @FXML
    private Label mednem = new Label();
    @FXML
    private Label dos = new Label();
    //private DBConn db = new DBConn();

    @FXML
    private HBox itemC;
    @FXML
    private Label weekdes;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBConn db = new DBConn();
        ObservableList<demoinfo> list = db.getTable();
        demoinfo[] llst = new demoinfo[list.size()];
        llst = db.forDisplay();

        AlarmControl hc = new AlarmControl();
        //System.out.println(llst[hc.num].m_name);
        mednem.setText(llst[hc.num].m_name);
        alarmtime.setText(llst[hc.num].remtimee);
        dos.setText(String.valueOf(llst[hc.num].doses));
        String[] weeks = new String[7];
        weeks = db.getWeeks(mednem.getText(), alarmtime.getText(), Integer.parseInt(dos.getText()));
        String toSet = new String("On ");
        for(int i=0; i< weeks.length; i++) {
            if(weeks[i] != null) toSet += weeks[i] + "  ";

        }

        weekdes.setText(toSet);

    }

    @FXML
    protected void onDeletePressed()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure want to delete this?");
        alert.setContentText(mednem.getText() + ", " + dos.getText() + " doses at " + alarmtime.getText());
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
            DBConn db = new DBConn();
            db.deleteItem(mednem.getText(), dos.getText(), alarmtime.getText());
            VBox vb = (VBox) this.itemC.getParent();
            vb.getChildren().remove(this.itemC);
        }
        else if(result.get() == ButtonType.CANCEL)
        {
            System.out.println("Not deleted");
        }

    }
}
