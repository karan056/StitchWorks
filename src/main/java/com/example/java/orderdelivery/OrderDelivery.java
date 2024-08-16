package com.example.java.orderdelivery;

//package com.example.java.orderdelivery;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.example.java.project.MySqlConnector;
import com.example.java.project.MySqlConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OrderDelivery {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField getmobile;

    @FXML
    private ListView<String> listbill;

    @FXML
    private ListView<String> listdress;

    @FXML
    private ListView<String> listoi;

    @FXML
    private ListView<String> listws;

    @FXML
    private TextField totbill;

    @FXML
    void getdeliveredall(ActionEvent event) {
        int bill = 0;
        for (int i=0;i<=listws.getItems().size();i++)
        {
            listws.getSelectionModel().select(i);
            if ("3".equals(listws.getSelectionModel().getSelectedItem()))
            {
                String od=listoi.getItems().get(i);
                listws.getItems().set(i,"4");
                update(od,"4");
                int a = Integer.parseInt(listbill.getItems().get(i));
                bill+=a;
            }
        }
        totbill.setText(""+bill);
        showMyMsg("All Work Delivered");

    }

    public void update(String oderId, String workstatus) {


        try {
            stmt=con.prepareStatement("update measurement set workstatus=? where orderId=?");
            stmt.setString(1,workstatus);
            stmt.setString(2,oderId);
            stmt.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @FXML
    void getrecords(ActionEvent event) {
        String Mobile = getmobile.getText();
        if (Mobile!=null)
        {
            WorkStatus(Mobile);
            OrderId(Mobile);
            Bill(Mobile);
            Dress(Mobile);
        }
    }
    PreparedStatement stmt;
    void WorkStatus(String mobile)
    {
        try
        {
            stmt=con.prepareStatement("select * from measurement where mobile=? and workstatus!=4");
            stmt.setString(1,mobile);
            ResultSet rs = stmt.executeQuery();
            ObservableList<String> ary = FXCollections.observableArrayList();
            while (rs.next())
            {
                Integer ws = rs.getInt("workstatus");
                ary.add(String.valueOf(ws));
            }
            listws.setItems(ary);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void OrderId(String orderID)
    {
        try
        {
            stmt=con.prepareStatement("select * from measurement where mobile=? and workstatus!=4");
            stmt.setString(1,orderID);
            ResultSet rs = stmt.executeQuery();
            ObservableList<String> ary2 = FXCollections.observableArrayList();
            while (rs.next())
            {
                Integer oi = rs.getInt("orderId");
                ary2.add(String.valueOf(oi));
            }
            listoi.setItems(ary2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void Bill(String Bil)
    {
        try
        {
            stmt=con.prepareStatement("select * from measurement where mobile=? and workstatus!=4");
            stmt.setString(1,Bil);
            ResultSet rs = stmt.executeQuery();
            ObservableList<String> ary3 = FXCollections.observableArrayList();
            while (rs.next())
            {
                Integer bl = rs.getInt("bill");
                ary3.add(String.valueOf(bl));
            }
            listbill.setItems(ary3);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    void Dress(String drs)
    {
        try
        {
            stmt=con.prepareStatement("select * from measurement where mobile=? And workstatus!=4");
            stmt.setString(1,drs);
            ResultSet rs = stmt.executeQuery();
            ObservableList<String> ary4 = FXCollections.observableArrayList();
            while (rs.next())
            {
                //Integer bl = rs.getInt("bill");
                String dr = rs.getString("dress");
                ary4.add(dr);
            }
            listdress.setItems(ary4);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    Connection con;
    @FXML
    void initialize() {
        con = MySqlConnector.doconnect();
        if (con == null)
            System.out.println("Connection Didn't Established");
        else
            System.out.println("Connection Doneee");

    }

    void showMyMsg(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Work Status");
        alert.setContentText(msg);

        alert.showAndWait();
    }

}