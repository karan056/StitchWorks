package com.example.java.project;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class CustomerEnroll {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ShowAdd;

    @FXML
    private DatePicker getDob;

    @FXML
    private ComboBox<String> getGender;

    @FXML
    private TextField showCity;

    @FXML
    private TextField showCont;

    @FXML
    private TextField showname;

    @FXML
    void doClear(ActionEvent event) {
        try {

            showCont.setText("");
            showname.setText("");
            showCity.setText("");
            ShowAdd.setText("");
            getGender.getSelectionModel().select(0);

            LocalDate local = LocalDate.now();

            getDob.setValue(local);




        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

    }

    @FXML
    void doEdit(ActionEvent event) {
        try {


            stnt=con.prepareStatement("update customer set cname=? , address=?, city=?,gender=?, dob=? where mobile=?");


            stnt.setString(1,showname.getText());
            stnt.setString(2,ShowAdd.getText());
            stnt.setString(3,showCity.getText());
            stnt.setString(4,getGender.getValue());


            LocalDate local =getDob.getValue();
            java.sql.Date date = java.sql.Date.valueOf(local);
            stnt.setDate(5,date);



            //stnt.setInt(6,Integer.parseInt(showCont.getText()));
            stnt.setLong(6, Long.parseLong(showCont.getText()));
            stnt.executeUpdate();
            showMyMsg("Records Updated Successfulllyyyy...........");

        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

    }
    PreparedStatement stnt;
    @FXML
    void doEnroll(ActionEvent event) {
        //name,contact,add,city,gender,dob,currentdate
        try {


            stnt=con.prepareStatement("insert into customer values(?,?,?,?,?,?,current_date)");
            stnt.setLong(1, Long.parseLong(showCont.getText()));

            stnt.setString(2,showname.getText());
            // stnt.setInt(1,Integer.parseInt(showCont.getText()));
            stnt.setString(3, ShowAdd.getText());
            stnt.setString(4, showCity.getText());
            stnt.setString(5,getGender.getSelectionModel().getSelectedItem());


            LocalDate local =getDob.getValue();
            java.sql.Date date = java.sql.Date.valueOf(local);
            stnt.setDate(6,date);


            stnt.executeUpdate();
            showMyMsg ("Records Saved Successfulllyyyy...........");

        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

    }

    void showall()
    {
        try
        {
            stnt=con.prepareStatement("select * from customer");
            ResultSet records= stnt.executeQuery();
            while (records.next())
            {
                String mn=records.getString("mobile");//col name
                String nm=records.getString("cname");//col name
                String ad=records.getString("address");//col name
                String ct=records.getString("city");//col name
                String gd=records.getString("gender");//col name

                Date dt=records.getDate("dob");//col name

                System.out.println(mn+"  "+nm+"  "+ad+"  "+gd+"  "+ct+"  "+dt);
            }
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }


    @FXML
    void doFetch(ActionEvent event) {
        try
        {
            stnt=con.prepareStatement("select * from customer where mobile=?");

            // stnt.setString(1, String.valueOf(showCont.getText()));
            stnt.setString(1,showCont.getText());
            ResultSet records= stnt.executeQuery();
            while (records.next())
            {
                String mn=records.getString("mobile");//col name
                String nm=records.getString("cname");//col name
                String ad=records.getString("address");//col name// int wala naam alag lena hai
                String ct=records.getString("city");//col name
                String gd=records.getString("gender");//col name

                Date dt=records.getDate("dob");//col name

                System.out.println(mn+"  "+nm+"  "+ad+"  "+ct+"  "+gd+"  "+dt);


                showname.setText(nm);
                ShowAdd.setText(ad);
                showCity.setText(ct);

                getGender.getSelectionModel().select(gd);
                getGender.setSelectionModel(getGender.getSelectionModel());

                getDob.setValue(((java.sql.Date) dt).toLocalDate());



            }
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

    }
    Connection con;
    @FXML
    void initialize() {

        con= MySqlConnector.doconnect();
        if (con==null)
            System.out.println("Connection Didn't Established");
        else
            System.out.println("Connection Doneee");
        String gender[]={"Select","Male","Female","Others"};
        getGender.getItems().addAll(gender);
        // getGender.getSelectionModel().select(0);
        showall();
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