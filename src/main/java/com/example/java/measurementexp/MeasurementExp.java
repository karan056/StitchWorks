package com.example.java.measurementexp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;

import com.example.java.project.MySqlConnector;
import com.example.java.tableview.WorkerBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MeasurementExp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> order;

    @FXML
    private TextField ordercus;

    @FXML
    private TableView<MeasurementBean> tblview;

    @FXML
    private ComboBox<String> workerr;

    PreparedStatement stnt;

    ObservableList<MeasurementBean> getRecords() {
        ObservableList<MeasurementBean> ary = FXCollections.observableArrayList();
        try {
            stnt = con.prepareStatement("select * from measurement");
            ResultSet records = stnt.executeQuery();
            while (records.next()) {
                String oi = records.getString("orderId");//col name
                String mb = records.getString("mobile");//col name
                String dr = records.getString("dress");//col name
                String dd = records.getString("dod");
                String bl = records.getString("bill");//col name
                String wr = records.getString("worker");//col name
                String ws = records.getString("workstatus");//col name


                ary.add(new MeasurementBean(oi, mb, dr, dd, bl, wr, ws));

                //System.out.println(mn+"  "+nm+"  "+ad+"  "+gd+"  "+ct+"  "+dt);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        System.out.println(ary.size());
        return ary;
    }

    @FXML
    void doexport(ActionEvent event) {
        try {
            writeExcel();
            System.out.println("Exported");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeExcel() throws Exception {
        Writer Writer = null;
        try {
            File file = new File("user.csv");
            Writer = new BufferedWriter(new FileWriter(file));
            String text = "poi,pmobile,pdress,pdod,pbill,pworker,pworkstatus";
            Writer.write(text);
            for (MeasurementBean p : getRecords()) {
                text = p.getOrderId() + "," + p.getMobile() + "," + p.getDress() + "," + p.getDod() + "," + p.getBill() + "," + p.getWorker() + "," + p.getWorkstatus() + "\n";
                Writer.write(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Writer.flush();
            Writer.close();
        }
    }

    @FXML
    void dofetch(ActionEvent event) {
        tblview.getItems().clear();
        //int selectedOrderId = order.getSelectionModel().getSelectedIndex()+1;
        String selectedMobile = ordercus.getText();
        //String selectedorderstat = order.getSelectionModel().getSelectedItem();
        if (selectedMobile!=null) {

            ObservableList<MeasurementBean> MobileData = getmobileData(selectedMobile);
            //tblview.getItems().clear();
//            addColms();
            tblview.setItems(MobileData);
            System.out.println(MobileData.size());
//            System.out.println(selectedOrderId);
        }

    }

    ObservableList<MeasurementBean> getmobileData( String mobiledata) {
        ObservableList<MeasurementBean> ary = FXCollections.observableArrayList();
        try {
            stnt = con.prepareStatement("SELECT * FROM measurement WHERE mobile=?");
            stnt.setString(1, mobiledata);
           // stnt.setString(2,workerName);
            ResultSet records = stnt.executeQuery();
            while (records.next()) {
                String oi = records.getString("orderId");//col name
                String mb = records.getString("mobile");//col name
                String dr = records.getString("dress");//col name
                String dd = records.getString("dod");
                String bl = records.getString("bill");//col name
                String wr = records.getString("worker");//col name
                String ws = records.getString("workstatus");//col name


                ary.add(new MeasurementBean(oi, mb, dr, dd, bl, wr, ws));

                // show_table();
                //System.out.println(mn+"  "+nm+"  "+ad+"  "+gd+"  "+ct+"  "+dt);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
//        System.out.println(ary.size());
        return ary;

    }


    @FXML
    void doshowall(ActionEvent event) {

        tblview.getItems().clear();

        tblview.setItems(getRecords());
    }

    void addColms()
    {
        TableColumn<MeasurementBean, String> uidc = new TableColumn<MeasurementBean, String>("Order ID");
        uidc.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        uidc.setMinWidth(100);
        TableColumn<MeasurementBean, String> uidc2 = new TableColumn<MeasurementBean, String>("Mobile");
        uidc2.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        uidc2.setMinWidth(100);
        TableColumn<MeasurementBean, String> uidc3 = new TableColumn<MeasurementBean, String>("Dress");
        uidc3.setCellValueFactory(new PropertyValueFactory<>("dress"));
        uidc3.setMinWidth(100);
        TableColumn<MeasurementBean, String> uidc4 = new TableColumn<MeasurementBean, String>("Date Of Delivery");
        uidc4.setCellValueFactory(new PropertyValueFactory<>("dod"));
        uidc4.setMinWidth(100);
        TableColumn<MeasurementBean, String> uidc5 = new TableColumn<MeasurementBean, String>("Bill");
        uidc5.setCellValueFactory(new PropertyValueFactory<>("bill"));
        uidc5.setMinWidth(100);
        TableColumn<MeasurementBean, String> uidc6 = new TableColumn<MeasurementBean, String>("Worker");
        uidc6.setCellValueFactory(new PropertyValueFactory<>("worker"));
        uidc6.setMinWidth(100);
        TableColumn<MeasurementBean, String> uidc7 = new TableColumn<MeasurementBean, String>("Work Status");
        uidc7.setCellValueFactory(new PropertyValueFactory<>("workstatus"));
        uidc7.setMinWidth(100);


        tblview.getColumns().addAll(uidc, uidc2, uidc3, uidc4, uidc5, uidc6, uidc7);

    }
    @FXML
    void showorder(ActionEvent event) {
        tblview.getItems().clear();
        int selectedOrderId = Integer.parseInt(order.getSelectionModel().getSelectedItem());
        ObservableList<MeasurementBean> orderData = getOrderData(selectedOrderId);
        tblview.getItems().clear();
        tblview.setItems(orderData);
//        System.out.println(orderData.size());
//            System.out.println(selectedOrderId);


    }

    ObservableList<MeasurementBean> getOrderData(int OrderId) {
        ObservableList<MeasurementBean> ary = FXCollections.observableArrayList();
        try {
            stnt = con.prepareStatement("SELECT * FROM measurement WHERE workstatus=?");
            stnt.setInt(1, OrderId);
            ResultSet records = stnt.executeQuery();
            while (records.next()) {
                String oi = records.getString("orderId");//col name
                String mb = records.getString("mobile");//col name
                String dr = records.getString("dress");//col name
                String dd = records.getString("dod");
                String bl = records.getString("bill");//col name
                String wr = records.getString("worker");//col name
                String ws = records.getString("workstatus");//col name


                ary.add(new MeasurementBean(oi, mb, dr, dd, bl, wr, ws));

               // show_table();
                //System.out.println(mn+"  "+nm+"  "+ad+"  "+gd+"  "+ct+"  "+dt);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
//        System.out.println(ary.size());
        return ary;

    }


    @FXML
    void showordercust(ActionEvent event) {

    }

    @FXML
    void showworker(ActionEvent event) {
        tblview.getItems().clear();
        //int selectedOrderId = order.getSelectionModel().getSelectedIndex()+1;
        String selectedWorker = workerr.getSelectionModel().getSelectedItem();
        String selectedorderstat = order.getSelectionModel().getSelectedItem();
        if (selectedWorker!=null&& selectedorderstat!=null) {

            ObservableList<MeasurementBean> workerData = getworkerData(selectedorderstat,selectedWorker);
            //tblview.getItems().clear();
//            addColms();
            tblview.setItems(workerData);
            System.out.println(workerData.size());
//            System.out.println(selectedOrderId);
        }

    }

    ObservableList<MeasurementBean> getworkerData( String workstatus , String workerName) {
        ObservableList<MeasurementBean> ary = FXCollections.observableArrayList();
        try {
            stnt = con.prepareStatement("SELECT * FROM measurement WHERE workstatus=? and worker=?");
            stnt.setString(1, workstatus);
            stnt.setString(2,workerName);
            ResultSet records = stnt.executeQuery();
            while (records.next()) {
                String oi = records.getString("orderId");//col name
                String mb = records.getString("mobile");//col name
                String dr = records.getString("dress");//col name
                String dd = records.getString("dod");
                String bl = records.getString("bill");//col name
                String wr = records.getString("worker");//col name
                String ws = records.getString("workstatus");//col name


                ary.add(new MeasurementBean(oi, mb, dr, dd, bl, wr, ws));

                // show_table();
                //System.out.println(mn+"  "+nm+"  "+ad+"  "+gd+"  "+ct+"  "+dt);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
//        System.out.println(ary.size());
        return ary;

    }

    Connection con;

    @FXML
    void initialize() {
        con = MySqlConnector.doconnect();
        if (con == null)
            System.out.println("Connection Didn't Established");
        else
            System.out.println("Connection Doneee");
        //getRecords();
        try {
            stnt = con.prepareStatement("select distinct workstatus from measurement");
            ResultSet rs = stnt.executeQuery();
            while (rs.next()) {
                order.getItems().add(rs.getString("workstatus"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            stnt = con.prepareStatement("select distinct worker from measurement");
            ResultSet rs = stnt.executeQuery();
            while (rs.next()) {
                workerr.getItems().add(rs.getString("worker"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        addColms();

    }

}
