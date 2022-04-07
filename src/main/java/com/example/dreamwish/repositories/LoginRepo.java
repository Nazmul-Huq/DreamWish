package com.example.dreamwish.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginRepo {

    Statement stmt;
    ResultSet rSet;

    // check if username and password is correct or not
    // if ok, return the userid for the given password
    // if not, return 0
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
