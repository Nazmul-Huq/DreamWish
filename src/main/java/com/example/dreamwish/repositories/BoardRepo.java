package com.example.dreamwish.repositories;

import com.example.dreamwish.entities.Wish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardRepo {

    static Statement stmt;
    static ArrayList<String> wishes = new ArrayList<>();

    /**
     * get all wishes from board
     * @return
     */

    public ArrayList<Wish> gettingWishes() {

        Connection con = DatabaseConnection.getDatabaseConnection();
        ArrayList<Wish> wishes = new ArrayList<>();

        try {
            stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * " + "FROM users " + "JOIN wishes ON  users.id =wishes.wish_id " + "JOIN board ON wishes.wish_id = board.wishes_id;");

            while (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                String image = rs.getString("image");
                String status = rs.getString("status");
                String expireDate = rs.getString("expiredate");
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                Wish wish = new Wish(id, title, description, image, status, expireDate, userId);
                wishes.add(wish);

            }
            con.close();
            return wishes;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}

//SELECT users.id, users.firstname, wishes.title, wishes.description, wishes.image
//FROM users, wishes
//WHERE users.id = wishes.wish_id
