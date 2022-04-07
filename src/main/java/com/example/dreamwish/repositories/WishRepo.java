package com.example.dreamwish.repositories;

import com.example.dreamwish.entities.Wish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WishRepo {
    Connection connection;

    /**
     * repository method to add Wish to database
     * @param wish
     * @return
     */
    public boolean addWish(Wish wish) {

        try{
            connection = DatabaseConnection.getDatabaseConnection();
            // create the query
            String query = " insert into wishes (title, description, image, status, expiredate, user_id)"
                    + " values (?, ?, ?, ?, ?, ?)";

            // create the mysql insert prepared statement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, wish.getTitle());
            preparedStmt.setString (2, wish.getDescription());
            preparedStmt.setString   (3, wish.getImage());
            preparedStmt.setString   (4, wish.getStatus());
            preparedStmt.setString(5, wish.getExpireDate());
            preparedStmt.setInt    (6, wish.getUserId());

            // execute the prepared statement
            preparedStmt.executeUpdate();

            // close the connection
            connection.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * repository method to delete a wish
     * @param wishId
     * @return
     */
    public Boolean deleteWish(int wishId) {
        try {
            connection = DatabaseConnection.getDatabaseConnection();
            Statement stmt = connection.createStatement();

            String query = "DELETE FROM `wishes` WHERE (`wish_id` = '" +wishId+"')";

            stmt.executeUpdate(query);
            connection.close();
            return true;
        } catch (Exception e){
            return false;
        }

    }

    /**
     * fetch all wishes of a particular user
     * @param userId
     * @return
     */
    public List<Wish> getUserWishes(int userId) {
        List<Wish> wishes = new ArrayList<>();
        try {

            connection = DatabaseConnection.getDatabaseConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM wishes WHERE user_id =" + userId;
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()){
                int wishId = resultSet.getInt("wish_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");
                String status = resultSet.getString("status");
                String expireDate = resultSet.getString("expiredate");

                Wish wish = new Wish(wishId, title, description, image, status, expireDate, userId);
                wishes.add(wish);
            }
            connection.close();

            return wishes;
        } catch (Exception e){
            return wishes;
        }
    }

    /**
     * add wish to the board when a user clicked on "Share Wish"
     * @param id
     * @return
     */
    public boolean addWishToBoard(int id) {

        try{
            Connection connection = DatabaseConnection.getDatabaseConnection();

            String query = " insert into board (wishes_id)"
                    + " values (?)";

            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, id);
            // execute the prepared statement
            preparedStmt.executeUpdate();
            connection.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * get an individual wish by id
     * @param id
     * @return
     */
    public Wish getWishBYId(int id) {
        Wish emptyWish = new Wish();
        try {
            Connection connection = DatabaseConnection.getDatabaseConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM wishes WHERE wish_id =" + id;

            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next() == false){
                connection.close();
                return emptyWish;

            } else {
                int userId = resultSet.getInt("user_id");
                int wishId = resultSet.getInt("wish_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String image = resultSet.getString("image");
                String status = resultSet.getString("status");
                String expireDate = resultSet.getString("expiredate");

                Wish wish = new Wish(wishId, title, description, image, status, expireDate, userId);
                connection.close();
                return wish;
            }
        } catch (Exception e){
            return emptyWish;
        }
    }

    /**
     * set edited data to the database for a particualr wish
     * @param wishId
     * @param title
     * @param description
     * @param image
     * @param status
     * @param expireDate
     * @return
     */
    public boolean editWish(
            int wishId,
            String title,
            String description,
            String image,
            String status,
            String expireDate
    )
    {
        try {

            Connection connection = DatabaseConnection.getDatabaseConnection();

            //UPDATE `dream_wish`.`wishes` SET `title` = 'title2', `description` = 'description2', `image` = 'dsds', `status` = 'status2', `expiredate` = '1-1-2021' WHERE (`wish_id` = '16');
            String query = "UPDATE wishes SET title = ?, description= ?, image= ?, status= ?, expiredate= ? where wish_id = ?";

            // create the mysql insert prepared statement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, title);
            preparedStmt.setString (2, description);
            preparedStmt.setString   (3, image);
            preparedStmt.setString   (4, status);
            preparedStmt.setString(5, expireDate);
            preparedStmt.setInt    (6, wishId);

            // execute the prepared statement
            preparedStmt.executeUpdate();
            preparedStmt.close();
            // close the connection
            connection.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }
} // class ends here
