package com.nikron.tennis.controller;

import com.nikron.tennis.service.PlayerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet(urlPatterns = "/")
public class MainMenuController extends HttpServlet {

    private final PlayerService service = PlayerService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/main-menu.jsp").forward(req, resp);
    }
}
