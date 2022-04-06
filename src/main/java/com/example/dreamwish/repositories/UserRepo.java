package com.example.dreamwish.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserRepo {
    public String getUserName(int id) {
        String userName = "";
        try {
            Connection connection = DatabaseConnection.getDatabaseConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT firstname, lastname FROM users where id =\'"+ id+"\'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                userName = firstName + " " + lastName;
            }
            connection.close();
            return userName;
        } catch (Exception e){
            return userName;
        }
    }
}
