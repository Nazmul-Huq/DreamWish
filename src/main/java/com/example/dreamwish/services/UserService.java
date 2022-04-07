package com.example.dreamwish.services;

import com.example.dreamwish.entities.Login;
import com.example.dreamwish.entities.User;
import com.example.dreamwish.repositories.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class UserService {
    UserRepo userRepo = new UserRepo();

    public String getUserName(int id) {
        // get username from  database
        String userName = userRepo.getUserName(id);

        //if username is not empty
        if (userName != null) {
            // break the username and put it into an array, so we can get first and last name
            String[] userNames = userName.split(" ");
            String firstName = userNames[0];
            String lastName = userNames[1];
            // make first character of first and last name upper case
            firstName = (firstName.substring(0,1)).toUpperCase(Locale.ROOT) + firstName.substring(1, firstName.length());
            lastName = (lastName.substring(0,1)).toUpperCase(Locale.ROOT) + lastName.substring(1, lastName.length());
            // re-create the username
            userName = firstName + " " + lastName;
        }
        return userName;
    }

    public int addUser(String firstName, String lastName, String email, String address, int phone) {

        User user = new User(firstName, lastName, email, address, phone);
        return userRepo.addUser(user);
    }

    public int addLogin(int newlyAddedUserId, String username, String password) {
        Login login = new Login(newlyAddedUserId, username, password);
        return userRepo.addLogin(login);
    }

    public User getUserDetail(int id) {
        return userRepo.getUserDetail(id);
    }

    public void editUser(int userId, String email, String address, String phone) {
        userRepo.editUser(userId, email, address, phone);
    }
}