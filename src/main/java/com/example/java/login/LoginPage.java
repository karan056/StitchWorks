package com.example.java.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPage {
    String p="@123";
    @FXML
    private ResourceBundle resources;

    @FXML
    private Button btn;

    @FXML
    private URL location;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField pwd;
    @FXML
    private Label lbl;

    @FXML
    void dologin(ActionEvent event) throws IOException {

        if(pwd.getText().equals(p))
        {
            Parent root=FXMLLoader.load(getClass().getResource("/com/example/java/dashboard/dashboardview.fxml"));
            Scene scene = new Scene(root);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

            Scene scene1=(Scene)lbl.getScene();
            scene1.getWindow().hide();
        }
        else
            lbl.setText("Incorrect Password..");

    }

   @FXML
    void initialize() {
        assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'admindeskView.fxml'.";
        assert pwd != null : "fx:id=\"pwd\" was not injected: check your FXML file 'admindeskView.fxml'.";

    }

}
