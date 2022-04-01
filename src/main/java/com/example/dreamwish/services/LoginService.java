package com.example.dreamwish.services;

import com.example.dreamwish.repositories.DatabaseConnection;

import java.sql.*;

public class LoginService {

    //Methods
    public static Boolean logIn(String username, String password) {
        return null;

        /*
        for (int i = 0; i < username.length(); i++) {

            //username has to have 1 UpperCase letter to be valid
            if (Character.isUpperCase(username.charAt(i))) {
                return true;
            }
        }
        //Password has to be 8 or longer to be valid
        if (password.length() >= 8) {

            for (int i = 0; i < password.length(); i++) {
                //has to have an uppercase letter in the start
                if (Character.isUpperCase(password.charAt(0))) {
                }
                //has to have numbers in the password
                if (Character.isDigit(password.charAt(password.length()))) {

                }

            }
            return true;
        }
        return false;

         */
    }

    public void logOut() {
        //will log out and destroys session and sends user to home page
    }

    public boolean checkLogin(String username) {
        //it will check if the user is in the database if it is it
        // will continue if not it will go nowhere

        boolean existingUsername = false;
        String url = "jdbc:mysql://localhost:3306/dream_wish";

        try {
            Class.forName("jdbc:mysql://localhost:3306/dream_wish");
            Connection con = DriverManager.getConnection(url,"root","Moha2300");

            Statement stmt = con.prepareStatement("select * from dream_wish.login;");

            ResultSet r1 = stmt.getResultSet();

            String usernameCounter;

            if (r1.next()) {
                usernameCounter = r1.getString(username);
                if (usernameCounter.equals(username)) {
                    existingUsername = true;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return existingUsername;
    }

}
