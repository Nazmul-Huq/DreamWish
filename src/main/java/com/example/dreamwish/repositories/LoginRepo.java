package com.example.dreamwish.repositories;

import com.example.dreamwish.entities.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LoginRepo {
    Connection connection;
    Statement stmt;
    ResultSet rSet;

    public ArrayList<Login> getUsernameAndPassword(String username, String password) {

        ArrayList<Login> logins = new ArrayList<Login>();
        try {
            connection = DatabaseConnection.getDatabaseConnection();
            stmt = connection.createStatement();

            String query = "SELECT * FROM login WHERE username AND password=" + username + password;

            rSet = stmt.executeQuery(query);
            while (rSet.next()) {
                int userid = rSet.getInt("user_id");
                username = rSet.getString("username");
                password = rSet.getString("password");

                Login loginData = new Login(username,password,userid);
                logins.add(loginData);
            }
            connection.close();

            return logins;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logins;

    }
}
