package com.nikron.tennis.controller;

import com.nikron.tennis.dto.PlayerDto;
import com.nikron.tennis.entity.MatchScore;
import com.nikron.tennis.service.MatchScoreService;
import com.nikron.tennis.util.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/new-match")
public class NewMatchController extends HttpServlet {

    private final MatchScoreService scoreService = MatchScoreService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(JspPath.getPathJsp("new-match")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MatchScore matchScore = scoreService.create(
                PlayerDto.builder()
                        .name(req.getParameter("first_player"))
                        .build(),
                PlayerDto.builder()
                        .name(req.getParameter("second_player"))
                        .build()
        );
        req.setAttribute("first_player", matchScore.getFirstPlayer());
        req.setAttribute("second_player", matchScore.getSecondPlayer());
        req.setAttribute("first_score", matchScore.getFirstPlayerScore());
        req.setAttribute("second_score", matchScore.getSecondPlayerScore());
        req.getRequestDispatcher(String.format("/match-score?uuid=%s", matchScore.getId()))
                .forward(req, resp);
    }
}
