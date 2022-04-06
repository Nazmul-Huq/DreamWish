package com.example.dreamwish.repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardRepo {

    static Statement stmt;
    static ArrayList<String> wishes = new ArrayList<>();

    public static String shareWishes(String wish) {

        Connection con = DatabaseConnection.getDatabaseConnection();

        try {
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * " + "FROM users " + "JOIN wishes ON  users.id =wishes.wish_id " + "JOIN board ON wishes.wish_id = board.wishes_id;");

            while (rs.next()) {
                String gettingWish = rs.getString("firstname, title, description, image");
                wishes.add(wish);
            }
            return "wishes";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}

//SELECT users.id, users.firstname, wishes.title, wishes.description, wishes.image
//FROM users, wishes
//WHERE users.id = wishes.wish_id
