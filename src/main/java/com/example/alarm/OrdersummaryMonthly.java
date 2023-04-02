package com.example.alarm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OrdersummaryMonthly implements Initializable {

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
        cart_monthly.removeProductByName(oitem.product_name);
        cart_monthly.generateSummary(cart.Products);
        String s1="monthlysubscriptionpage.fxml";
        Stage stage = (Stage) button.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(s1));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("e-MED");
        stage.setScene(scene);
        stage.show();
    }

}
