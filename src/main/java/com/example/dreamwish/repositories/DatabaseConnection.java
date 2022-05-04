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
            /**String url ="jdbc:mysql://dreamwish.mysql.database.azure.com:3306/dream_wish?useSSL=true&requireSSL=false"; myDbConn = DriverManager.getConnection(url, "teamcharlie@dreamwish", {your_password});**/
            String url ="jdbc:mysql://dreamwish.mysql.database.azure.com:3306/dream_wish?useSSL=true&requireSSL=false";
            Connection con = DriverManager.getConnection(url, "teamcharlie@dreamwish", "Kea22charlie");
            return con;
        } catch (Exception e) {
            return null;
        }
    }
}
