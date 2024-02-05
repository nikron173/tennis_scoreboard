package com.nikron.tennis.controller;

import com.nikron.tennis.dto.PlayerDto;
import com.nikron.tennis.entity.MatchScore;
import com.nikron.tennis.service.MatchScoreService;
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
        resp.sendRedirect("new-match.jsp");
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
        req.setAttribute("score", matchScore.getScore());
        req.getRequestDispatcher(String.format("/match-score?uuid=%s", matchScore.getId()))
                .forward(req, resp);
    }
}
