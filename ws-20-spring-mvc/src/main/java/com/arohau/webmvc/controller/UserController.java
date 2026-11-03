package com.arohau.webmvc.controller;

import com.arohau.webmvc.model.User;
import com.arohau.webmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User userModel(){
        return new User();
    }

//    @GetMapping
//    public String getUserById(@RequestParam(name = "userId") Long userId, Model model) {
//        System.out.println("UserController GET /");
//        System.out.println("userId = " + userId);
//        System.out.println("model = " + model);
//        System.out.println("... gettting user by user id");
//        return "user";
//    }

    @GetMapping
    public String getUserByLogin(@RequestParam(name = "userLogin") String login, Model model) {
        System.out.println("UserController GET /");
        System.out.println("login = " + login);
        System.out.println("model = " + model);
        com.arohau.webmvc.entity.User userByLogin = userService.getUserByLogin(login);
        model.addAttribute("user", userByLogin);
        return "user";
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
        com.arohau.webmvc.entity.User savedUser = userService.saveUser(user);
        model.addAttribute("serverMessage", "New User, " + savedUser.getName() + " saved successfully");
        return "redirect:/home?" + "serverMessage=" + "New User, " + savedUser.getName() + " saved successfully";
    }

//    @GetMapping("/")
//    public String user(@Validated User user, Model model) {
//        System.out.println("UserController GET /");
//        model.addAttribute("userName", user.getName());
//        return "user";
//    }

}
