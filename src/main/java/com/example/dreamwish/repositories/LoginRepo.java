package com.example.dreamwish.repositories;

import com.example.dreamwish.entities.Login;

import java.sql.Connection;

public class LoginRepo {
    Connection connection = DatabaseConnection.getDatabaseConnection();

    public void getData(String username,String password) {



    }
}
