package com.example.dreamwish.services;

import com.example.dreamwish.repositories.DatabaseConnection;
import com.example.dreamwish.repositories.LoginRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class LoginService {
    static LoginRepo loginRepo = new LoginRepo();

    //Methods
    public int logIn(String username, String password) {

        return loginRepo.getUsernameAndPassword(username,password);

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

    public void logOut(HttpServletRequest request) {
        //will log out and destroys session and sends user to home page
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    public void checkLogin(String username, String password) {
        //it will check if the username is in the database if it is
        // it will continue if not it will go nowhere

        loginRepo.getUsernameAndPassword(username,password);

        /*
        boolean existingUsername = false;

        Connection con = DatabaseConnection.getDatabaseConnection();

        try {

            Statement stmt = con.prepareStatement("select * from login;");

            ResultSet r1 = stmt.getResultSet();

            String usernameCounter;

            if (r1.next()) {
                usernameCounter = r1.getString(username);
                if (usernameCounter.equals(username)) {
                    existingUsername = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("user is not found");
            e.printStackTrace();
        }
        return existingUsername;

         */

    }



}
