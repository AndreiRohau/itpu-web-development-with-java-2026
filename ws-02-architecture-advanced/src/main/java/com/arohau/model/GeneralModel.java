package com.arohau.model;

import com.arohau.old.dao.Dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static java.util.Objects.isNull;
import static com.arohau.Constant.MESSAGE;

public class GeneralModel implements Model {
    public static final Logger LOGGER = Logger.getLogger(GeneralModel.class.getName());

    private final Dao dao = Dao.getInstance();

    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final String message = req.getParameter(MESSAGE).trim();
            LOGGER.info("MESSAGE=" + message);

            // todo do business logic
            // todo go to data base
            String translation = dao.getTranslation(message);

            if (isNull(translation) || translation.trim().isEmpty()) {
                translation = "NO SUCH WORD IN VOCABULARY";
            }


            req.setAttribute("message", message);
            req.setAttribute("translation", translation);

            // finally, update view
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            LOGGER.warning(e.getMessage());
        }
    }
}
