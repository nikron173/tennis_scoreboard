package com.nikron.tennisscoreboard.controllers;

import com.nikron.tennisscoreboard.services.OngoingMatchesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "unfinished", urlPatterns = "/unfinished-matches")
public class UnfinishedMatchesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/unfinished-matches") && req.getQueryString() == null){
            req.setAttribute("unfinished_matches", OngoingMatchesService.getMatchMapIsNotFinished());
            req.getRequestDispatcher("/unfinished_matches.jsp").forward(req, resp);
        }
    }
}
