package com.example.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
     // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/java/piechart/pie-view.fxml"));
         //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/java/direct/direct-view.fxml"));
           // FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/java/admin/admindeskView.fxml"));
         //   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/java/tableview/tableview-view.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/java/admin/admindeskView.fxml"));


            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 530, 380);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
