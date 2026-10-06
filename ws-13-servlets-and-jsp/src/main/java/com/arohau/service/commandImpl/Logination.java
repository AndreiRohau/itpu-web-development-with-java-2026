package com.arohau.service.commandImpl;

import com.arohau.domain.User;
import com.arohau.repository.Repository;
import com.arohau.repository.impl.UserRepositoryImpl;
import com.arohau.service.Command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static com.arohau.controller.FrontController.*;
import static com.arohau.repository.impl.UserRepositoryImpl.INSTANCE;

public class Logination implements Command {

    private final Repository<Long, User> userRepository = INSTANCE;

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        User user = new User(login, password);

        User found = ((UserRepositoryImpl) userRepository).findByLoginAndPassword(user);

        if (found != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedUser", found);
            session.setAttribute("greeting", "Hello dear [" + found.getLogin() + "]");
            req.setAttribute("msg", "User is logged in [" + found + "]");
            resp.sendRedirect(req.getContextPath());
        } else {
            req.setAttribute("msg", "User not found [" + user + "]");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
