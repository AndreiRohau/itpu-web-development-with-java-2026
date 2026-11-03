package com.arohau.webmvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LocalControllerAdvice {
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(Model model) {
        System.out.println("NullPointerException handled");
        model.addAttribute("msg", "NullPointerException");
        return "error";
    }
}
