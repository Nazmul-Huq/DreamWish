package com.example.dreamwish.services;

import com.example.dreamwish.entities.Wish;
import com.example.dreamwish.repositories.WishRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishService {

    // make necessary instances/objects
    WishRepo wishRepo = new WishRepo();

    /**
     * method to add a Wish to the database
     * @param title
     * @param description
     * @param image
     * @param status
     * @param expireDate
     * @return
     */
    public List<String> addWish(String title,
                          String description,
                          String image,
                          String status,
                          String expireDate)
    {

        //create an ArrayList to store and return if any error/data missing appear
        List<String> addWishMessage = new ArrayList<>();

        List<String> formError = validateWishForm(title, description, image, status, expireDate);

        // check if any form error appear or not.
        // if yes, then return the error, else add the wish
        if (formError.size() != 0) {
            addWishMessage = formError;
        } else {

            // create a wish
            Wish wish = new Wish(title, description, image, status, expireDate, 1);

            // if wish added successfully, then attach a success message, else attach a failed message
            if (wishRepo.addWish(wish)) {
                addWishMessage.add("Your wish has been added successfully");
            } else {
                addWishMessage.add("A problem appeared. Please try again later");
            }
        }
        return addWishMessage;


    } // addWish() ends here


    /**
     * Edit a wish
     * @param wishId
     * @param title
     * @param description
     * @param image
     * @param status
     * @param expireDate
     * @return
     */
    public List<String> editWish(int wishId,
                                 String title,
                                 String description,
                                 String image,
                                 String status,
                                 String expireDate) {

        //create an ArrayList to store and return if any error/data missing appear
        List<String> editWishMessage = new ArrayList<>();

        // validate form data
        List<String> formError = validateWishForm(title, description, image, status, expireDate);

        // check if any form error appear or not.
        // if yes, then return the error, else edit the wish
        if (formError.size() != 0) {
            editWishMessage = formError;
        } else {

            // if wish added successfully, then attach a success message, else attach a failed message
            if (wishRepo.editWish(wishId, title, description, image, status, expireDate)) {
                editWishMessage.add("Your wish has been added successfully");
            } else {
                editWishMessage.add("A problem appeared. Please try again later");
            }
        }
        return editWishMessage;
    }

    /**
     * method to validated incoming form data
     * if all information are correct will return an empty List
     * @param title
     * @param description
     * @param image
     * @param status
     * @param expireDate
     * @return
     */
    public List<String> validateWishForm(String title,
                                         String description,
                                         String image,
                                         String status,
                                         String expireDate)
    {
        List<String> formError = new ArrayList<>();

        if (title.isEmpty()) {
            formError.add("You must need to provide a title");
        }

        if (description.isEmpty()) {
            formError.add("You must need to provide a description");
        }
        if (status.isEmpty()) {
            formError.add("You must select status");
        }
        return formError;
    } //validateWishForm() ends here

    /**
     * method to delete a wish by wishId
     * @param wishId
     * @return
     */
    public boolean deleteWish(int wishId){
        wishRepo.deleteWish(wishId);
        return true;
    }

    public List<Wish> getUserWishes(int userId){
        List<Wish> wishes = wishRepo.getUserWishes(userId);
        return wishes;
    }

    public boolean shareWish(int id) {
        if(wishRepo.addWishToBoard(id)){
            return true;
        } else {
            return false;
        }
    }

    public Wish getWishById(int id) {
        Wish wish = wishRepo.getWishBYId(id);
        return wish;
    }


    public void saveImage(String imageName, MultipartFile multipartFile)  throws IOException {
        Path uploadPath = Paths.get("src/main/resources/static/images");
        try {
            Files.copy(multipartFile.getInputStream(), uploadPath.resolve(imageName));
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + imageName);
        }
    }
} // class ends here
