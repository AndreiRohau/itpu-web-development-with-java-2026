package com.arohau.service;

import com.arohau.exception.MyCustomException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Calculator {
    void calculate(HttpServletRequest req, HttpServletResponse resp) throws MyCustomException;
}
