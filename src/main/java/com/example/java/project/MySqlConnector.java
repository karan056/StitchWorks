package com.example.java.project;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;


public class MySqlConnector
{
    public static Connection doconnect()
    {
        Connection con=null;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/projectfx","root","karan@1409");
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
        }
        return con;
    }
    public static void main(String ary[])
    {
        if (doconnect()==null)
            System.out.println("soryyyyyy");
        else
            System.out.println("Doneeeee");
    }
}
