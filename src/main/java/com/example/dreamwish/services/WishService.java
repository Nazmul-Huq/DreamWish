package com.example.dreamwish.services;

import com.example.dreamwish.entities.Wish;
import com.example.dreamwish.repositories.WishRepo;
import org.springframework.stereotype.Service;

@Service
public class WishService {

    WishRepo wishRepo = new WishRepo();

    public String addWish(Wish wish){
        wishRepo.addWish(wish);
        return null;
    }
}
