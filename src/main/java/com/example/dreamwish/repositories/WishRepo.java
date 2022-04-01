package com.example.dreamwish.repositories;

import com.example.dreamwish.entities.Wish;

import java.sql.Connection;
import java.sql.Statement;

public class WishRepo {
    Connection connection = DatabaseConnection.getDatabaseConnection();

    public void addWish(Wish wish) {

        try{

            Statement stmt =  connection.createStatement();
            String insertData = "INSERT INTO `wishes` " +
                    "(`email`) " +
                    "VALUE " +
                    "('"+wish+"')";

            stmt.executeUpdate(insertData);
            connection.close();

        } catch (Exception e){

        }
    }
}
