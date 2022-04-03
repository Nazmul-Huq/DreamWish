package com.example.dreamwish.controllers;

import com.example.dreamwish.entities.Wish;
import com.example.dreamwish.services.LoginService;
import com.example.dreamwish.services.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    WishService wishService;
    LoginService loginService;

    /**  Default Methods starts here*/

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    /**  Default Methods ends here*/




    /**  Nazmul's Methods starts here*/
    @GetMapping("/add-wish")
    public String addWish(){
        return "add-wish";
    }

    @PostMapping("/add-wish-handler")
    public String addWishHandler(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String status,
            @RequestParam String expireDate,
            @RequestParam("imageUpload")MultipartFile multipartFile,
            Model model
            ) throws IOException
    {
        String imageName = "";
        if (multipartFile != null) {
            imageName = multipartFile.getOriginalFilename();
            wishService.saveImage(imageName, multipartFile);
        }

        List<String> addStatus = wishService.addWish(title, description, imageName, status, expireDate);

        model.addAttribute(addStatus);
        return "redirect:/add-wish";
    }

    @PostMapping("/edit-wish-handler")
    public String editWishHandler(
            @RequestParam int wishId,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String status,
            @RequestParam String expireDate,
            @RequestParam String image,
            Model model
    )
    {
        List<String> editStatus = wishService.editWish(wishId, title, description, image, status, expireDate);
        model.addAttribute(editStatus);
        return "redirect:/";
    }

    @GetMapping("/edit-wish/{id}")
    public String editWish(@PathVariable int id, Model model){
        Wish wish = wishService.getWishById(id);
        model.addAttribute("wish", wish);
        return "edit-wish";
    }

    @GetMapping("/delete-wish")
    public String deleteWish(@RequestParam int id) {
        wishService.deleteWish(id);
        return "redirect:/";
    }

    @GetMapping("/share-wish")
    public String shareWishInBoard(@RequestParam int id){
        System.out.println(wishService.shareWish(id));
        return "redirect:board";
    }

    /**
     * Below method will be deleted after Sara implement her controller method
     */
    /*
    @GetMapping("/mypage")
    public String myPage(@RequestParam int id, Model model){
        // before doing anything we should check if user is logged in or not
        // if not logged in redirect to login page
        // if logged in then call wish service, add model, return to mypage
        List<Wish> wishes = wishService.getUserWishes(id);
        model.addAttribute("wishes", wishes);
        return "mypage";
    }
    */
    @GetMapping("/board")
    public String board(){
        return "board";
    }



    /**  Nazmul's Methods starts here */
}