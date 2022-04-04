package com.example.dreamwish.repositories;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    /**
     * method to establish a connection with the database
     * @return
     */
    public static Connection getDatabaseConnection(){

        try {
            String url = "jdbc:mysql://localhost:3306/dream_wish";
            Connection con = DriverManager.getConnection(url,"root","####");
            return con;
        } catch (Exception e) {
            return null;
        }
    }
}
