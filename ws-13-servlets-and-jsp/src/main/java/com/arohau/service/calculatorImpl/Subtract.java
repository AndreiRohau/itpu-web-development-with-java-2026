package com.arohau.service.calculatorImpl;

import com.arohau.exception.MyCustomException;
import com.arohau.service.Calculator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.arohau.controller.FrontController.NUMBER_1;
import static com.arohau.controller.FrontController.NUMBER_2;

public class Subtract implements Calculator {

    @Override
    public void calculate(HttpServletRequest req, HttpServletResponse resp) throws MyCustomException {
        try {
            final int number1 = Integer.parseInt(req.getParameter(NUMBER_1));
            final int number2 = Integer.parseInt(req.getParameter(NUMBER_2));
            final StringBuilder sb = new StringBuilder()
                    .append(number1)
                    .append("-")
                    .append(number2)
                    .append("=")
                    .append(number1 - number2);
            req.setAttribute("result", sb);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new MyCustomException(e);
        }
    }
}
