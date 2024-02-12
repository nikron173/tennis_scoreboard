package com.nikron.tennis.controller;

import com.nikron.tennis.entity.MatchScore;
import com.nikron.tennis.service.MatchScoreService;
import com.nikron.tennis.util.JspPath;
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
        req.setAttribute("uuid", id);
        req.setAttribute("first_player", matchScore.getFirstPlayer());
        req.setAttribute("second_player", matchScore.getSecondPlayer());
        req.setAttribute("first_score", matchScore.getFirstPlayerScore());
        req.setAttribute("second_score", matchScore.getSecondPlayerScore());
        req.getRequestDispatcher(JspPath.getPathJsp("match-score")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MatchScore matchScore;
        if (Objects.nonNull(req.getParameter("first_player"))) {
            matchScore = scoreService.game(UUID.fromString(req.getParameter("uuid")), req.getParameter("first_player"));
        } else {
            matchScore = scoreService.game(UUID.fromString(req.getParameter("uuid")), req.getParameter("second_player"));
        }
        if (Objects.nonNull(matchScore.getWinnerPlayer())) {
            //req.getRequestDispatcher("/matches").forward(req, resp);
            resp.sendRedirect("/matches");
            return;
        }
        System.out.println(matchScore.getId());
        resp.sendRedirect(String.format("/match-score?uuid=%s", matchScore.getId()));
    }
}
