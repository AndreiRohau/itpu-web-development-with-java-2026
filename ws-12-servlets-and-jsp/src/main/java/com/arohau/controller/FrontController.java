package com.arohau.controller;

import com.arohau.exception.MyCustomException;
import com.arohau.service.Calculator;
import com.arohau.service.calculatorImpl.Subtract;
import com.arohau.service.calculatorImpl.Sum;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class FrontController extends HttpServlet {
    public static final Logger LOGGER = Logger.getLogger(FrontController.class.getName());
    public static final String
            COMMAND = "command",
            OPERATION = "operation",
            SUM = "sum",
            SUBTRACT = "subtract",
            NUMBER_1 = "number-1",
            NUMBER_2 = "number-2";

    public Map<String, Calculator> commands = new HashMap<>();

    @Override
    public void init() {
        commands.put(SUM, new Sum());
        commands.put(SUBTRACT, new Subtract());
    }
//    operation=sum&command=sum&number-1=0&number-2=0&SubmitButtonType=sum
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final String operation = req.getParameter(OPERATION).trim();

            Calculator calculator = commands.get(operation);
            calculator.calculate(req, resp);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);
        } catch (MyCustomException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String commandTitle = req.getParameter(COMMAND).trim();
        LOGGER.info("CommandTitle=" + commandTitle);
        final String command = req.getParameter("command");
        final String message = req.getParameter("message");
        LOGGER.info("Received post method got : " + command);
        LOGGER.info("Received post method got : " + message);
        req.setAttribute("result", "Received is [" + command + ", " + message + "]");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}