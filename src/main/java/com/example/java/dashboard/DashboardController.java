package com.example.java.dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.java.project.MySqlConnector;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.beans.binding.Bindings;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PieChart pieChart;

    @FXML
    void getcust(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/java/project/customer-view.fxml"));
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("project/customer-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    void getgetready(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/java/project/getready-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    void getmeas(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/java/project/measurement-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    void getmeasrec(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/java/measurementexp/measexp-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    void getorderrec(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/java/orderdelivery/orderdel-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    void getworker(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/java/project/worker-console.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    void getworkerrec(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/java/tableview/tableview-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    @FXML
    void dologout(ActionEvent event) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/java/admin/admindeskView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int customerCount = 0;
        int workerCount = 0;
        int CoatCount = 0;
        int KPCount = 0;

        try (Connection con = MySqlConnector.doconnect())
        {

            PreparedStatement stmt;
            stmt= con.prepareStatement("SELECT COUNT(workstatus) AS count FROM measurement where workstatus like ?");
            stmt.setString(1,"%1%");
            ResultSet customerResultSet = stmt.executeQuery();
            if (customerResultSet.next()) {
                customerCount = customerResultSet.getInt("count");
            }
            System.out.println(customerCount);


            stmt= con.prepareStatement("SELECT COUNT(workstatus) AS count FROM measurement where workstatus like ?");
            stmt.setString(1,"%2%");
            ResultSet workerResultSet = stmt.executeQuery();
            if (workerResultSet.next()){
                workerCount = workerResultSet.getInt("count");
            }
            System.out.println(workerCount);

            stmt= con.prepareStatement("SELECT COUNT(workstatus) AS count FROM measurement where workstatus like ?");
            stmt.setString(1,"%3%");
            ResultSet CoatResultSet = stmt.executeQuery();
            if (CoatResultSet.next()){
                CoatCount = CoatResultSet.getInt("count");
            }

            stmt= con.prepareStatement("SELECT COUNT(workstatus) AS count FROM measurement where workstatus like ?");
            stmt.setString(1,"%4%");
            ResultSet KPResultSet = stmt.executeQuery();
            if (KPResultSet.next()){
                KPCount = KPResultSet.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        pieChartData.add(new PieChart.Data("Order Recieved", customerCount));
        pieChartData.add(new PieChart.Data("Order In progress", workerCount));
        pieChartData.add(new PieChart.Data("Ready Orders",CoatCount));
        pieChartData.add(new PieChart.Data("Delivered Orders",KPCount));

        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(),":",  data.pieValueProperty()
                        )
                )
        );
        pieChart.setData(pieChartData);
    }

}

