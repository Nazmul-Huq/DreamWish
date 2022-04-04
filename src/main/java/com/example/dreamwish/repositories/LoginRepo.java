package com.example.dreamwish.repositories;

import com.example.dreamwish.entities.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LoginRepo {

    Statement stmt;
    ResultSet rSet;

    public int getUsernameAndPassword(String username, String password) {

        int userid = 0;
        try {
            Connection connection = DatabaseConnection.getDatabaseConnection();
            stmt = connection.createStatement();

            String query = "SELECT * FROM login WHERE username=\'"+ username + "\'AND password=\'"+password+"\'";

            rSet = stmt.executeQuery(query);
            while (rSet.next()) {
                userid = rSet.getInt("user_id");
            }
            connection.close();

            return userid;
        } catch (Exception e) {
            e.printStackTrace();
            return userid;
        }


    }
}
