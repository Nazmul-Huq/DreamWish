package com.example.dreamwish.entities;

public class Login {

    private String username;
    private String password;
    private int userId;


    public Login(String username,String password,int userId) {
        this.username = username;
        this.password = password;
        this.userId = userId;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUserid() {
        return userId;
    }


    //toString
    @Override
    public String toString() {
        return "LoginService{" + "username='" + username + '\'' + ", password='" + password + '\'' +
                ", userId=" + userId + '}';
    }
}
