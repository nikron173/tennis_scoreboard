package com.nikron.tennis.controller;

import com.nikron.tennis.entity.MatchScore;
import com.nikron.tennis.service.MatchScoreService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;


@WebServlet(urlPatterns = "/match-score")
public class MatchScoreController extends HttpServlet {
    private final MatchScoreService scoreService = MatchScoreService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("uuid"));
        MatchScore matchScore = scoreService.findById(id);
        req.setAttribute("first_player", matchScore.getFirstPlayer());
        req.setAttribute("second_player", matchScore.getSecondPlayer());
        req.setAttribute("score", matchScore.getScore());
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID id = UUID.fromString(req.getParameter("uuid"));
        MatchScore matchScore = scoreService.findById(id);
        if (Objects.nonNull(req.getParameter("first_player"))) {

        } else {

        }
        req.getRequestDispatcher(String.format("/match-score?uuid=%s", matchScore.getId()))
                .forward(req, resp);
    }
}
