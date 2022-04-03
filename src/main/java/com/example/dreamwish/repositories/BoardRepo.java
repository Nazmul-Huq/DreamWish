package com.example.dreamwish.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardRepo {

    static Statement stmt;
    static Connection con;

    static ArrayList<String> wishes = new ArrayList<>();

    public static Connection getDatabaseConnection(){

        try {
            String url = "jdbc:mysql://localhost:3306/dream_wish";
            Connection con = DriverManager.getConnection(url,"root","Kiy3ia#");
            return con;
        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> fetchWishes() {

        try {
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT wishes FROM dream_wish");

            while (rs.next()) {
                String wish = rs.getString("wish");
                wishes.add(wish);
            }
            return wishes;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
