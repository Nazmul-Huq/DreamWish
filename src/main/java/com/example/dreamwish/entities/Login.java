package com.example.dreamwish.entities;

public class Login {

    private int userId;
    private String username;
    private String password;

    public Login() {
    }

    public Login(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }



    //toString
    @Override
    public String toString() {
        return "LoginService{" + "username='" + username + '\'' + ", password='" + password + '\'' +
                ", userId=" + userId + '}';
    }
}
