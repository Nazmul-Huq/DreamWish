package com.example.dreamwish.controllers;

import com.example.dreamwish.entities.Login;
import com.example.dreamwish.entities.User;
import com.example.dreamwish.entities.Wish;
import com.example.dreamwish.services.BoardService;
import com.example.dreamwish.services.LoginService;
import com.example.dreamwish.services.UserService;
import com.example.dreamwish.services.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("userSessionId")
public class HomeController {

    @Autowired
    WishService wishService;
    @Autowired
    UserService userService;
    LoginService loginService = new LoginService();
    BoardService boardService = new BoardService();

    /**
     * Default Methods starts here
     */

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    /**  Default Methods ends here*/


    @GetMapping("/add-wish")
    public String addWish(HttpSession httpSession) {
        Object getSession = httpSession.getAttribute("userSessionId");
        if (getSession != null) {
            return "add-wish";
        } else {
            return "redirect:/login";
        }

    }

    @PostMapping("/add-wish-handler")
    public String addWishHandler(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String status,
            @RequestParam String expireDate,
            @RequestParam(required = false, value = "imageUpload") MultipartFile multipartFile,
            HttpSession httpSession,
            Model model
    ) throws IOException {
        String imageName = "";
        if (!(multipartFile.isEmpty())) {
            imageName = multipartFile.getOriginalFilename();
            wishService.saveImage(imageName, multipartFile);
        }
        int userId = (int) httpSession.getAttribute("userSessionId");
        List<String> addStatus = wishService.addWish(title, description, imageName, status, expireDate, userId);

        model.addAttribute(addStatus);
        return "redirect:/mypage";
    }

    @PostMapping("/edit-wish-handler")
    public String editWishHandler(
            @RequestParam int wishId,
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String status,
            @RequestParam String expireDate,
            @RequestParam(required = false, value = "imageUpload") MultipartFile multipartFile,
            Model model
    ) throws IOException {
        List<String> editStatus = new ArrayList<>();
        String imageName = "";
        if (multipartFile.isEmpty()) {
            editStatus = wishService.editWish(wishId, title, description, imageName, status, expireDate);
        } else {
            imageName = multipartFile.getOriginalFilename();
            wishService.saveImage(imageName, multipartFile);
        }
        model.addAttribute(editStatus);
        return "redirect:/mypage";
    }

    @GetMapping("/edit-wish/{id}")
    public String editWish(@PathVariable int id, Model model) {
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
    public String shareWishInBoard(@RequestParam int id) {
        System.out.println(wishService.shareWish(id));
        return "redirect:board";
    }


    @GetMapping("/mypage")
    public String myPage(HttpSession httpSession, ModelMap modelMap){
        // before doing anything we should check if user is logged in or not
        // if not logged in redirect to login page
        // if logged in then call wish service, add model, return to mypage
        Object  sessionObject = httpSession.getAttribute("userSessionId");
        if (sessionObject != null) {
            int id = (int) httpSession.getAttribute("userSessionId");
            List<Wish> wishes = wishService.getUserWishes(id);
            String userName = userService.getUserName(id);
            modelMap.addAttribute("userName", userName);
            modelMap.addAttribute("wishes", wishes);
            return "mypage";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/board")
    public String board(Model model) {
        ArrayList<Wish> wishesFromBoard = boardService.getwishesFromBoard();
        model.addAttribute("wishesFromBoard", wishesFromBoard);

        return "board";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        loginService.logOut(request);
        return "redirect:/index";  //Where you go after logout.
    }

    @PostMapping("/login-handler")
    public String loginHandler(@RequestParam String username, @RequestParam String password, HttpSession httpSession) {
        int userId = loginService.logIn(username,password);
        if (userId == 0) {
            return "redirect:/login";
        } else {
            httpSession.setAttribute("userSessionId", userId);
            return "redirect:/mypage";
        }
    }

    @GetMapping("/add-user")
    public String addUser(){
        return "add-user";
    }

    @PostMapping("/add-user-handler")
    public String addUserHandler(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam int phone,
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) throws IOException {
        int newlyAddedUserId = userService.addUser(firstName, lastName, email, address, phone);
        if (newlyAddedUserId !=0 ){
            userService.addLogin(newlyAddedUserId, username, password);
            return "/login";
        }
        return "redirect:/mypage";
    }

    @GetMapping("/edit-user/{id}")
    public String editUser(@PathVariable int id, Model model) {
        User user = userService.getUserDetail(id);
        model.addAttribute("userDetail", user);
        return "edit-user";
    }

    @PostMapping("/edit-user-handler")
    public String editUserHandler(
            @RequestParam int userId,
            @RequestParam String email,
            @RequestParam String address,
            @RequestParam String phone
    ) {

        userService.editUser(userId, email, address, phone);
        return "redirect:/mypage";
    }

} // class ends here