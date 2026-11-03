package com.arohau.webmvc.controller;

import com.arohau.webmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Controller
@RequestMapping("/user")
public class UserController {

    @ModelAttribute("user")
    public User userModel(){
        return new User();
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        System.out.println("UserController GET /registration");
        System.out.println(model);
        User user = (User) model.getAttribute("user");
        if (nonNull(user)) {
            user.setName("DefaultName");
            user.setLogin("qwe");
            user.setPassword1("1234");
            user.setPassword2("1234");
        }
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Validated @ModelAttribute("user") User user,
                               BindingResult bindingResult, Model model) {
        System.out.println("UserController POST /registration");
        System.out.println(user);
        System.out.println(bindingResult);
        System.out.println(model);
        System.out.println("...saving user...");
        model.addAttribute("serverMessage", "New User, with name" + user.getName() + " saved successfully");
        return "home";
    }

    @GetMapping("/")
    public String user(@RequestParam(name = "userId") String userId, Model model) {
        System.out.println("UserController GET /");
        System.out.println("userId = " + userId);
        System.out.println("model = " + model);
        System.out.println("... gettting user by user id");
        return "user";
    }

//    @GetMapping("/")
//    public String user(@Validated User user, Model model) {
//        System.out.println("UserController GET /");
//        model.addAttribute("userName", user.getName());
//        return "user";
//    }

}
