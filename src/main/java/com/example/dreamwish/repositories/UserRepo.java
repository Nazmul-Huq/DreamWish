package com.example.dreamwish.repositories;

import com.example.dreamwish.entities.Login;
import com.example.dreamwish.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public int addUser(User user) {

        try{
            Connection connection = DatabaseConnection.getDatabaseConnection();
            String columnNames[] = new String[] { "id" };
            // create the query
            String query = " insert into users (firstname, lastname, email, address, phone)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert prepared statement
            PreparedStatement preparedStmt = connection.prepareStatement(query, columnNames );
            preparedStmt.setString (1, user.getFirstName());
            preparedStmt.setString (2, user.getLastName());
            preparedStmt.setString   (3, user.getEmail());
            preparedStmt.setString   (4, user.getAddress());
            preparedStmt.setInt(5, user.getPhone());

            // execute the prepared statement
            int affectedRows = preparedStmt.executeUpdate();
            if (affectedRows == 0) {
                connection.close();
                return 0;
            } else {
                java.sql.ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
                if ( generatedKeys.next() ) {
                   int primkey = generatedKeys.getInt(1);
                   connection.close();
                   return primkey;
                }
                return 1;
            }

        } catch (Exception e){
            return 0;
        }
    }

    public int addLogin(Login login) {
        try{
            Connection connection = DatabaseConnection.getDatabaseConnection();
            // create the query
            String query = " insert into login (user_id, username, password)"
                    + " values (?, ?, ?)";

            // create the mysql insert prepared statement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, login.getUserid());
            preparedStmt.setString (2, login.getUsername());
            preparedStmt.setString (3, login.getPassword());


            // execute the prepared statement
            int affectedRows = preparedStmt.executeUpdate();
            if (affectedRows == 0) {
                connection.close();
                return 0;
            } else {
                return 1;
            }

        } catch (Exception e){
            return 0;
        }
    }

    public User getUserDetail(int id) {
        User user = new User();
        try {
            Connection connection = DatabaseConnection.getDatabaseConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users where id =\'"+ id+"\'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int userId = resultSet.getInt("id");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                int phone = resultSet.getInt("phone");
                user = new User(userId, firstName, lastName, email, address, phone);
            }
            connection.close();
            return user;
        } catch (Exception e){
            return user;
        }
    }

    public boolean editUser(int userId, String email, String address, String phone) {
        try {

            Connection connection = DatabaseConnection.getDatabaseConnection();

            String query = "UPDATE users SET email = ?, address= ?, phone= ? where id = ?";

            // create the mysql insert prepared statement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, email);
            preparedStmt.setString (2, address);
            preparedStmt.setString   (3, phone);
            preparedStmt.setInt   (4, userId);

            // execute the prepared statement
            preparedStmt.executeUpdate();
            preparedStmt.close();
            // close the connection
            connection.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
