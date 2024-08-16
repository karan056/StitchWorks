package com.example.java.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Measurement {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dod;

    @FXML
    private TextField getbill;

    @FXML
    private ComboBox<String> getdress;

    @FXML
    private TextField getmobile;

    @FXML
    private ComboBox<String> getworkers;

    @FXML
    private ImageView imgprev;

    @FXML
    private TextArea measr;

    @FXML
    private TextField orderid;

    @FXML
    private Button pickext;

    @FXML
    private TextField ppu;

    @FXML
    private ComboBox<String> qnt;



    @FXML
    public void doclose(ActionEvent event) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.close();
    }


    @FXML
    void donew(ActionEvent event) {
        try {
            getmobile.setText("");
            getdress.getSelectionModel().select(0);
            qnt.getSelectionModel().select(0);
            ppu.setText("");
            getbill.setText("");
            measr.setText("");
            getworkers.getSelectionModel().select(0);

            LocalDate local = LocalDate.now();

            dod.setValue(local);

        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
    }
    PreparedStatement stmt;
    @FXML
    void dosave(ActionEvent event) {
        //orderid,mobile,dress,picpath,dod,qty,bill,measurement,worker,doob
        try {


           // stmt=con.prepareStatement("insert into measurement values(?,?,?,?,?,?,?,?,current_date)",PreparedStatement.RETURN_GENERATED_KEYS);
            stmt=con.prepareStatement("INSERT INTO measurement (mobile, dress, picpath, dod, qty, bill, measurement, worker, doob, workstatus ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, current_date,?)", Statement.RETURN_GENERATED_KEYS);
//            stmt = con.prepareStatement("INSERT INTO measurement (mobile, dress, picpath, dod, qty, bill, measurement, worker, doob, workstatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, current_date,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1,getmobile.getText());
            stmt.setString(2,getdress.getSelectionModel().getSelectedItem());
            stmt.setString(3,filePath);
            LocalDate local =dod.getValue();
            java.sql.Date date = java.sql.Date.valueOf(local);
            stmt.setDate(4,date);
            stmt.setInt(5,Integer.parseInt(qnt.getSelectionModel().getSelectedItem()));
            stmt.setString(6,getbill.getText());
            stmt.setString(7,measr.getText());
            stmt.setString(8,getworkers.getSelectionModel().getSelectedItem());
            stmt.setInt(9,1);
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedOrderId = generatedKeys.getInt(1);
               // showMyMsg( "Records Saved Successfully", "Generated Order ID: " + generatedOrderId);
                System.out.println(generatedOrderId);
            }
            showMyMsg("Records Saved Successfulllyyyy...........");

        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

    }

    @FXML
    void dosearch(ActionEvent event) {
        try
        {
            stmt=con.prepareStatement("select * from measurement where orderId=?");
            stmt.setInt(1,Integer.parseInt(orderid.getText()));

            ResultSet records= stmt.executeQuery();
            while (records.next())
            {
                String mn=records.getString("mobile");//col name
                String drs=records.getString("dress");//col name
                String path=records.getString("picpath");//col name
                Date dt=records.getDate("dod");//col name
                String qt=records.getString("qty");//col name// int wala naam alag lena hai
                int billl=records.getInt("bill");//col name
                String ms=records.getString("measurement");
                String wrk=records.getString("worker");//col name


//                System.out.println(mn+"  "+drs+"  "+path+"  "+dt+"  "+qt+"  "+billl+" "+ms+" "+wrk);

                getdress.getSelectionModel().select(drs);
                getworkers.getSelectionModel().select(wrk);
                qnt.getSelectionModel().select(qt);
                getmobile.setText(mn);
                getmobile.setText(mn);
                measr.setText(ms);
                dod.setValue(((java.sql.Date) dt).toLocalDate());
                filePath=path;
                imgprev.setImage(new Image(new FileInputStream(filePath)));
                getbill.setText(String.valueOf(billl));
            }
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

    }

    @FXML
    void dopick(ActionEvent event) {
        try
        {
            stmt=con.prepareStatement("select * from measurement where mobile=?");
            //stmt.setInt(1,Integer.parseInt(orderid.getText()));
            stmt.setString(1,getmobile.getText());
            ResultSet records= stmt.executeQuery();
            while (records.next())
            {
                String mss=records.getString("measurement");//col name
                System.out.println("mss");
                measr.setText(mss);
            }
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

    }

    @FXML
    void doupdate(ActionEvent event) {
//orderid,mobile,dress,picpath,dod,qty,bill,measurement,worker,doob
        try {
            // int orderId = Integer.parseInt(this.orderid.getText());

            stmt=con.prepareStatement("UPDATE measurement set mobile=? , dress=?, picpath=?, dod=?,qty=?, bill=?, measurement=?, worker=? where orderId=?");

            stmt.setString(1,getmobile.getText());
            stmt.setString(2,getdress.getSelectionModel().getSelectedItem());
            stmt.setString(3,filePath);
            LocalDate local =dod.getValue();
            java.sql.Date date = java.sql.Date.valueOf(local);
            stmt.setDate(4,date);
            stmt.setInt(5,Integer.parseInt(qnt.getSelectionModel().getSelectedItem()));
            stmt.setInt(6,Integer.parseInt(getbill.getText()));
            stmt.setString(7,measr.getText());
            stmt.setString(8,getworkers.getSelectionModel().getSelectedItem());
            stmt.setInt(9,Integer.parseInt(orderid.getText()));
            stmt.executeUpdate();
            showMyMsg("Records Updated Successfulllyyyy...........");

        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }

    }
    String filePath="nopic.jpg";
    @FXML
    void doupload(ActionEvent event) {
        FileChooser chooser=new FileChooser();
        chooser.setTitle("Select Profile Pic:");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("*.*", "*.*")
        );
        File file=chooser.showOpenDialog(null);
        filePath=file.getAbsolutePath();


        try {
            imgprev.setImage(new Image(new FileInputStream(file)));
        }
        catch (FileNotFoundException e) {	e.printStackTrace();}

    }
    PreparedStatement stnt;
     @FXML
            public void doadd(ActionEvent actionEvent)
     {
         try
         {
             String drss= getdress.getSelectionModel().getSelectedItem();
             stnt=con.prepareStatement("select * from worker where spcl like ?");
             stnt.setString(1,"%" + drss+ "%");
             ResultSet records = stnt.executeQuery();
             getworkers.getItems().clear();
             while (records.next())
             {
                 getworkers.getItems().add(records.getString("wname"));
             }
            // getworkers.getItems().clear();
         }
         catch (Exception exp)
         {
             exp.printStackTrace();
         }
     }
  @FXML
          public void  dobill(KeyEvent keyEvent)
  {
      if (keyEvent.getCode()== KeyCode.ENTER)
      {
          if (qnt.getSelectionModel().getSelectedItem()==null)
          {
              showMyMsg("Please Select Quantity First");
          }
          int price = Integer.parseInt(ppu.getText());
          int a = Integer.parseInt(qnt.getSelectionModel().getSelectedItem());
          getbill.setText(a*price+"");
      }
  }

    String work[]={"Select","Pent","Shirt","Coat","Kurta Pajama"};
    String qntt[]={"Select","1","2","3","4"};
Connection con;
    @FXML
    void initialize() {
        con= MySqlConnector.doconnect();
        if (con==null)
            System.out.println("Connection Didn't Established");
        else
            System.out.println("Connection Doneee");
        getdress.getItems().addAll(work);
       // getdress.getSelectionModel().select(0);
        qnt.getItems().addAll(qntt);
        qnt.getSelectionModel().select(0);

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
