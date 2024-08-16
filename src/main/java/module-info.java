module com.example.java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jdi;
   // requires com.twilio.sdk;

    requires com.dlsc.formsfx;

    opens com.example.java to javafx.fxml;
    exports com.example.java;
   // requires java.sql;
    opens com.example.java.project to javafx.fxml;
    exports com.example.java.project;
    opens com.example.java.tableview to javafx.fxml;
    exports com.example.java.tableview;
    opens com.example.java.measurementexp to javafx.fxml;
    exports com.example.java.measurementexp;
   opens com.example.java.orderdelivery to javafx.fxml;
   exports com.example.java.orderdelivery;
//    opens com.example.java.direct to javafx.fxml;
//    exports com.example.java.direct;
    opens com.example.java.piechart to javafx.fxml;
    exports com.example.java.piechart;
    opens com.example.java.direct to javafx.fxml;
    exports com.example.java.direct;
  opens com.example.java.login to javafx.fxml;
   exports com.example.java.login;
    opens com.example.java.dashboard to javafx.fxml;
    exports com.example.java.dashboard;




}