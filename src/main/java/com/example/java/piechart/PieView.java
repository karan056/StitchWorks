package com.example.java.piechart;

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

public class PieView implements Initializable {

    @FXML
    private PieChart pieChart;
    private int CoatCount;
    private int KPCount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void viewpie(ActionEvent event) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        int customerCount = 0;
        int workerCount = 0;

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
