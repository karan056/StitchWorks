package com.example.java.tableview;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

import com.example.java.project.MySqlConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableCont {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> showall;

    @FXML
    private TableView<WorkerBean> tblview;
    PreparedStatement stnt;

    ObservableList<WorkerBean> getRecords(String input1)

    {
        ObservableList<WorkerBean> ary= FXCollections.observableArrayList();
        try
        {
            stnt=con.prepareStatement("select * from worker where spcl like ?");
            stnt.setString(1,"%" + input1 +"%");
            ResultSet records= stnt.executeQuery();
            while (records.next())
            {
                String wn=records.getString("wname");//col name
                String mb=records.getString("mobile");//col name
                String sp=records.getString("spcl");//col name
                // String ct=records.getString("city");//col name
                // String gd=records.getString("gender");//col name

                // Date dt=records.getDate("dob");//col name
                ary.add(new WorkerBean(wn,mb,sp));

                //System.out.println(mn+"  "+nm+"  "+ad+"  "+gd+"  "+ct+"  "+dt);
            }
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
//        System.out.println(ary.size());
        return  ary;
    }
    ObservableList<WorkerBean> getRecords()

    {
        ObservableList<WorkerBean> ary= FXCollections.observableArrayList();
        try
        {
            stnt=con.prepareStatement("select * from worker");
            ResultSet records= stnt.executeQuery();
            while (records.next())
            {
                String wn=records.getString("wname");//col name
                String mb=records.getString("mobile");//col name
                String sp=records.getString("spcl");//col name
               // String ct=records.getString("city");//col name
               // String gd=records.getString("gender");//col name

               // Date dt=records.getDate("dob");//col name
                ary.add(new WorkerBean(wn,mb,sp));

                //System.out.println(mn+"  "+nm+"  "+ad+"  "+gd+"  "+ct+"  "+dt);
            }
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
        System.out.println(ary.size());
        return  ary;
    }

    @FXML
    void doexport(ActionEvent event) {
try {
    writeExcel();
    System.out.println("Exported");
}
catch (Exception e)
{
    e.printStackTrace();
}
    }

    public void writeExcel() throws Exception
    {
        Writer Writer=null;
        try
        {
            File file = new File("users.csv");
            Writer = new BufferedWriter(new FileWriter(file));
            String text = "pid,pmobile,pspcl";
            Writer.write(text);
            for (WorkerBean p : getRecords())
            {
                text= p.getWname()+ ","+p.getMobile()+ ","+p.getSpcl()+"\n";
                Writer.write(text);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            Writer.flush();
            Writer.close();
        }
    }

    @FXML
    void dofetch(ActionEvent event) {

        TableColumn<WorkerBean, String> uidc = new TableColumn<WorkerBean,String>("Worker Name");
        uidc.setCellValueFactory(new PropertyValueFactory<>("wname"));
        uidc.setMinWidth(100);
        TableColumn<WorkerBean, String> uidc2= new TableColumn<WorkerBean,String>("Mobile Number");
        uidc2.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        uidc2.setMinWidth(100);
        TableColumn<WorkerBean, String> uidc3 = new TableColumn<WorkerBean,String>("Specialization");
        uidc3.setCellValueFactory(new PropertyValueFactory<>("spcl"));
        uidc3.setMinWidth(100);

        tblview.getColumns().addAll(uidc,uidc2,uidc3);
       tblview.setItems(getRecords());
    }

    @FXML
    void doshow(ActionEvent event) {
        tblview.getItems().clear();
        tblview.setItems(getRecords(showall.getValue()));
    }
    void addCOLMS(){
        TableColumn<WorkerBean, String> uidc = new TableColumn<WorkerBean,String>("Worker Name");
        uidc.setCellValueFactory(new PropertyValueFactory<>("wname"));
        uidc.setMinWidth(100);
        TableColumn<WorkerBean, String> uidc2= new TableColumn<WorkerBean,String>("Mobile Number");
        uidc2.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        uidc2.setMinWidth(100);
        TableColumn<WorkerBean, String> uidc3 = new TableColumn<WorkerBean,String>("Specialization");
        uidc3.setCellValueFactory(new PropertyValueFactory<>("spcl"));
        uidc3.setMinWidth(100);

        tblview.getColumns().addAll(uidc,uidc2,uidc3);
    }
Connection con;
    @FXML
    void initialize() {
        con= MySqlConnector.doconnect();
        if (con==null)
            System.out.println("Connection Didn't Established");
        else
            System.out.println("Connection Doneee");
        getRecords();
        String []aa={"Pent","Coat","Kurta_Pajama","Shirt"};
        showall.getItems().addAll(aa);
        addCOLMS();
    }

}
