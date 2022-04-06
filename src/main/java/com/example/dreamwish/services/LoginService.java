package com.example.dreamwish.services;

import com.example.dreamwish.repositories.LoginRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


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

}
