package com.arohau.webmvc.controller;

import com.arohau.webmvc.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private List<User> users = new ArrayList<>();

    @ModelAttribute("user")
    public User userModel(){
        return new User();
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        System.out.println("UserController GET /registration");
        System.out.println(model);
        return "registration";
    }

    @PostMapping
    public String registration(@Validated @ModelAttribute("user") User user,
                               BindingResult bindingResult, Model model) {
        System.out.println("UserController POST /registration");
        System.out.println(user);
        System.out.println(bindingResult);
        System.out.println(model);
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        System.out.println("...saving user...");
        
        // ... PROCESSING creation of a new user here, by calling service ...
        user.setUserId(Long.valueOf(users.size() + 1));
        users.add(user);

        model.addAttribute("serverMessage", "New User, with name " + user.getName() + " saved successfully");
        return "home";
    }

    @GetMapping("/")
    public String user(@RequestParam(name = "userId") String userId, Model model) {
        System.out.println("UserController GET /");
        System.out.println("userId = " + userId);
        System.out.println("model = " + model);
        System.out.println("... gettting user by user id");
        // getting user from database by id
        // User user = new User(11L, "Bill", "Lee", null, null);
        List<User> foundUsers = this.users
            .stream()
            .filter(x -> x.getUserId().equals(Long.valueOf(userId)))
            .collect(Collectors.toList());

        model.addAttribute("user", foundUsers.getFirst());
        return "user";
    }

//    @GetMapping("/")
//    public String user(@Validated User user, Model model) {
//        System.out.println("UserController GET /");
//        model.addAttribute("userName", user.getName());
//        return "user";
//    }

}
