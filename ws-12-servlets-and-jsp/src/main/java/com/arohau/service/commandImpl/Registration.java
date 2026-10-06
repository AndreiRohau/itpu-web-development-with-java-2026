package com.arohau.service.commandImpl;

import com.arohau.service.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Registration implements Command {

    //UserService userService = new UserServiceImpl;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        //.. get registartion params
        //.. init user with received parameters
        //.. userService.register(user)
        //.. req.forward()
    }
}
