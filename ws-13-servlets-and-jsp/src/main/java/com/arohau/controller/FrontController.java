package com.arohau.controller;

import com.arohau.service.Calculator;
import com.arohau.service.Command;
import com.arohau.service.calculatorImpl.Subtract;
import com.arohau.service.calculatorImpl.Sum;

import com.arohau.service.commandImpl.Logination;
import com.arohau.service.commandImpl.PostInputParameter;
import com.arohau.service.commandImpl.Registration;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

// you can use annotation instead of web.xml
//@WebServlet("/fc")
public class FrontController extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(FrontController.class.getName());
    public static final String
            COMMAND = "command",
            OPERATION = "operation",
            LOGINATION = "log",
            REGISTRACTION = "reg",
            SUM = "sum",
            SUBTRACT = "subtract",
            NUMBER_1 = "number-1",
            NUMBER_2 = "number-2",
            LOGIN = "login",
            PASSWORD = "password",
            EMAIL = "email"
                    ;

    public Map<String, Calculator> operations = new HashMap<>();
    public Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        Iterator<String> iterator = config.getInitParameterNames().asIterator();
        while (iterator.hasNext()) {
            String nextParameterName = iterator.next();
            String initParameter = config.getInitParameter(nextParameterName);
            System.out.println(nextParameterName + " = " + initParameter);
        }

        System.out.println("config: " + config);
        operations.put(SUM, new Sum());
        operations.put(SUBTRACT, new Subtract());

        commands.put(LOGINATION, new Logination());
        commands.put(REGISTRACTION, new Registration());
        commands.put("postInputParameter", new PostInputParameter());
    }

    private void doExec(HttpServletRequest req, HttpServletResponse resp) {
        readCookies(req, resp);
        try {
            final String operation = req.getParameter(OPERATION);
            if (operation != null && !operation.isEmpty()) {
                Calculator calculator = operations.get(operation);
                calculator.calculate(req, resp);
            } else {
                final String commandName = req.getParameter(COMMAND);
                LOGGER.info("CommandName=" + commandName);
                Command command = commands.get(commandName);
                command.execute(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readCookies(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println(String.format("%s{%s=%s,%s}", "cookie", cookie.getName(), cookie.getValue(), cookie.getAttributes()));
//            System.out.println(cookie.toString());
        }
        resp.addCookie(new Cookie("hello", "guys"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doExec(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doExec(req, resp);
    }
}