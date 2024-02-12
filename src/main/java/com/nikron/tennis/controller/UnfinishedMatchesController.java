package com.nikron.tennis.controller;

import com.nikron.tennis.service.MatchScoreService;
import com.nikron.tennis.util.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/unfinished-matches")
public class UnfinishedMatchesController extends HttpServlet {

    private final MatchScoreService matchScoreService = MatchScoreService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("matches", matchScoreService.findAll());
        req.getRequestDispatcher(JspPath.getPathJsp("unfinished-matches")).forward(req, resp);
    }
}
