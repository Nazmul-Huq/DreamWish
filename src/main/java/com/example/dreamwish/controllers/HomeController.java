package com.example.dreamwish.controllers;

import com.example.dreamwish.entities.Wish;
import com.example.dreamwish.services.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController {

    @Autowired
    WishService wishService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/add-wish")
    public String addWish(){
        return "add-wish";
    }

    @PostMapping("/add-wish-handler")
    public String addWishHandler(WebRequest formData){
        String title = formData.getParameter("title");
        String description = formData.getParameter("description");
        String status = formData.getParameter("status");
        String expireDate = formData.getParameter("expireDate");
        String image = formData.getParameter("image");

        Wish wish = new Wish(title, description, image, status, expireDate, 1);
        wishService.addWish(wish);

        return null;
    }
}