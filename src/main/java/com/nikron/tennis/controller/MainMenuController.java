package com.nikron.tennis.controller;

import com.nikron.tennis.service.PlayerService;
import com.nikron.tennis.util.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MainMenuController extends HttpServlet {

    private final PlayerService service = PlayerService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspPath.getPathJsp("main-menu")).forward(req, resp);
    }
}
