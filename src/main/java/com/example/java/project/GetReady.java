package com.example.java.project;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.example.java.project.MySqlConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class GetReady {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label doe;

    @FXML
    private ListView<Date> getdod;

    @FXML
    private ListView<String> getdrs;

    @FXML
    private ListView<String> getoi;

    @FXML
    private ListView<String> getstat;

    @FXML
    private ComboBox<String> wname;

    PreparedStatement stnt;
    Connection con;
    @FXML
    void doass(ActionEvent event) {
        for (int i=0;i<=getstat.getItems().size();i++)
        {
            getstat.getSelectionModel().select(i);
            if ("1".equals(getstat.getSelectionModel().getSelectedItem()))
            {
                String od=getoi.getItems().get(i);
                getstat.getItems().set(i,"2");
                update(od,"2");
            }
        }
        showMyMsg("All work Assigned ");

    }
    @FXML
    void dorec(ActionEvent event) {
for (int i=0;i<=getstat.getItems().size();i++)
{
    getstat.getSelectionModel().select(i);
    if ("2".equals(getstat.getSelectionModel().getSelectedItem()))
    {
        String od=getoi.getItems().get(i);
       getstat.getItems().set(i,"3");
       update(od,"3");
    }
}
showMyMsg("All Work Collected");
    }

    @FXML
    void dostat(MouseEvent event) {

    }

    @FXML
    void wname(ActionEvent event)
    {
        String worker=wname.getSelectionModel().getSelectedItem();
        updatetable(worker);

    }
    @FXML
    void dochange(MouseEvent event) {
if (event.getClickCount()==2)
{
    String sel = getoi.getSelectionModel().getSelectedItem();
    getstat.getSelectionModel().select(getoi.getSelectionModel().getSelectedIndex());
    String stat=getstat.getSelectionModel().getSelectedItem();
    if (sel!=null)
    {
        if(stat.equals("1")){
            update(sel,"2");
        }
        if (stat.equals("2"))
        {
            update(sel,"3");
        }
        String worker=wname.getSelectionModel().getSelectedItem();
        updatetable(worker);

    }
}
    }
public void update(String oderId, String workstatus) {


    try {
        stnt=con.prepareStatement("update measurement set workstatus=? where orderId=?");
        stnt.setString(1,workstatus);
        stnt.setString(2,oderId);
        stnt.executeUpdate();
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
}

    void updatetable(String w){
        getoi.getItems().clear();
        getstat.getItems().clear();
        getdod.getItems().clear();
        getdrs.getItems().clear();
        try {
            stnt= con.prepareStatement("SELECT * from measurement where worker =? AND (workstatus=1 or workstatus=2)");
            String workerr=wname.getSelectionModel().getSelectedItem();
            stnt.setString(1,workerr);


            ResultSet rs=stnt.executeQuery();

            while (rs.next()){
                getoi.getItems().add(rs.getString(1));
                getstat.getItems().add(rs.getString(11));
                getdod.getItems().add(rs.getDate(5));
                getdrs.getItems().add(rs.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void initialize() {
        con= MySqlConnector.doconnect();
        try {
            stnt= con.prepareStatement("select distinct worker from measurement");
            ResultSet rs=stnt.executeQuery();
            while(rs.next()) {
                wname.getItems().add(rs.getString("worker"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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