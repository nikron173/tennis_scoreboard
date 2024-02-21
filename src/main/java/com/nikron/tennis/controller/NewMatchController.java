package com.nikron.tennis.controller;

import com.nikron.tennis.dto.PlayerDto;
import com.nikron.tennis.entity.MatchScore;
import com.nikron.tennis.service.MatchScoreService;
import com.nikron.tennis.util.JspPath;
import com.nikron.tennis.util.ValidParameter;
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
        String firstPlayerName = ValidParameter.getValidPlayerName(req, "first_player");
        String secondPlayerName = ValidParameter.getValidPlayerName(req, "second_player");
        MatchScore matchScore = scoreService.create(
                PlayerDto.builder()
                        .name(firstPlayerName)
                        .build(),
                PlayerDto.builder()
                        .name(secondPlayerName)
                        .build()
        );
        resp.sendRedirect(String.format("%s/match-score?uuid=%s", req.getContextPath(), matchScore.getId()));
    }
}
