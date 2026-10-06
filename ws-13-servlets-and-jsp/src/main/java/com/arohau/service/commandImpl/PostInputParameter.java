package com.arohau.service.commandImpl;

import com.arohau.controller.FrontController;
import com.arohau.service.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.logging.Logger;

public class PostInputParameter implements Command {
    public static final Logger LOGGER = Logger.getLogger(PostInputParameter.class.getName());

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        final String command = req.getParameter("command");
        final String message = req.getParameter("message");
        LOGGER.info("Received post method got : " + command);
        LOGGER.info("Received post method got : " + message);
        req.setAttribute("result", "Received is [" + command + ", " + message + "]");
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
