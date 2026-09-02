package com.arohau.model;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Model {
    void execute(HttpServletRequest req, HttpServletResponse resp);
}
