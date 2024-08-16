package com.example.java;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;




public class HelloController implements Initializable{


    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList(
new PieChart.Data("Apples",28),
                new PieChart.Data(" Oranges",25),
                new PieChart.Data("Apples",50),
                new PieChart.Data("Apples",40)
        );

        pieChartData.forEach(data ->
               data.nameProperty().bind(
Bindings.concat(
        data.getName(), "amount:", data.pieValueProperty()
)
               )
                );
        pieChart.getData().addAll(pieChartData);
    }



}
