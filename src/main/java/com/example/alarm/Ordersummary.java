package com.example.alarm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Ordersummary implements Initializable {

    @FXML
    public static Boolean delPressed = Boolean.FALSE;
    @FXML
    private ImageView img;

    @FXML
    private AnchorPane apane;

    @FXML
    private Label mednem;

    @FXML
    private Label prcxquan;

    @FXML
    private Label total;
    @FXML
    private Button button;

    orderedItem oitem = orderpagecontroller.ot;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        if(LoginStaringPagecontroller.monthlypressed == Boolean.TRUE) oitem = MonthlySubscriptionPage.ot;
        mednem.setText(oitem.product_name);
        prcxquan.setText(oitem.product_price + " x " + oitem.product_selected);
        total.setText(String.valueOf((Integer.parseInt(oitem.product_price) * Integer.parseInt(oitem.product_selected))));
        img.setImage(new Image(oitem.product_image_url));
        delPressed = Boolean.FALSE;
        button.setOnAction(event -> {
            try {
                deletebuttonpressed(new ActionEvent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
    void deletebuttonpressed(ActionEvent event) throws IOException {
        cart.removeProductByName(oitem.product_name);
        cart.generateSummary(cart.Products);
        cart.total_items_selected -= Integer.parseInt(oitem.product_selected);
        delPressed = Boolean.TRUE;

        VBox vb = (VBox) this.apane.getParent();
        vb.getChildren().remove(this.apane);
        //orderpagecontroller.makeAppends(cart.Total_Amount.toString());
        /*String s1="orderpage.fxml";
        Node root = (Node) event . getSource () ;
        Stage stage = ( Stage ) root . getScene ().getWindow() ;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        //Stage stage =new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 720, 528);
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();*/
    }

}
