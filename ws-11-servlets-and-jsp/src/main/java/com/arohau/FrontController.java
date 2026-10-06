package com.arohau;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    public static final String VERSION_ = "VERSION_";
    public static final String VERSION_VALUE = "4";
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontController.doGet()");
        System.out.println(VERSION_ + VERSION_VALUE);
        req.setAttribute(VERSION_, VERSION_VALUE);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
