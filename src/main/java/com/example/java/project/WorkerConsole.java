package com.example.java.project;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class WorkerConsole {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField getspcl;

    @FXML
    private ListView<String> listspcl;

    @FXML
    private TextField wadd;

    @FXML
    private TextField wname;

    @FXML
    private TextField wphone;

    @FXML
    void doadd(MouseEvent event) {
        if (event.getClickCount()==2)
        {
            String selectedItem = listspcl.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                String currentText = getspcl.getText();

                if (!currentText.contains(selectedItem)) {
                    if (!currentText.isEmpty()) {
                        currentText += ", ";
                    }
                    getspcl.setText(currentText + selectedItem);
                }
            }

        }
    }
    @FXML
    void donext(ActionEvent event) {
        try {
            wname.setText("");
            wphone.setText("");
            wadd.setText("");
            getspcl.setText("");
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
    PreparedStatement stnt;
    @FXML
    void dosave(ActionEvent event) {

        //wname,mobile,add,spcl,currentdate
        try {
            stnt=con.prepareStatement("insert into worker values(?,?,?,?,current_date)");
            stnt.setString(1,wname.getText());
            stnt.setString(2, wphone.getText());
            stnt.setString(3, wadd.getText()); 
           stnt.setString(4,getspcl.getText());
            stnt.executeUpdate();
            showMyMsg("Records Saved Successfully.......");
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

    }
Connection con;
    String work[]={"Pent","Shirt","Coat","Kurta Pajama"};
    @FXML
    void initialize() {
        //con= MySqlConnector.doconnect();
        con= MySqlConnector.doconnect();
        if (con==null)
            System.out.println("Connection Didn't Established");
        else
            System.out.println("Connection Doneee");
        //listspcl.getItems().clear();
        listspcl.getItems().addAll(work);
    }
    void showMyMsg(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Its Header");
        alert.setContentText(msg);

        alert.showAndWait();
    }
}
