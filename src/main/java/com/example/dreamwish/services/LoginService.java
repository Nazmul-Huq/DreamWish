package com.example.dreamwish.services;

import com.example.dreamwish.repositories.LoginRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginService {
    static LoginRepo loginRepo = new LoginRepo();

    //Methods
    public int logIn(String username, String password) {

        return loginRepo.getUsernameAndPassword(username,password);

    }

    public void logOut(HttpServletRequest request) {
        //will log out and destroys session and sends user to home page
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

}
