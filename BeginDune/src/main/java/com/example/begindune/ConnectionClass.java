package com.example.begindune;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
    public Connection con;
    public Connection getConnection(){
        String dbName = "UserInfo";
        String userName = "root";
        String password = "";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, "root", "");
        }catch (Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
