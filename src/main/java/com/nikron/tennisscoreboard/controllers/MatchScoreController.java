package com.nikron.tennisscoreboard.controllers;

import com.nikron.tennisscoreboard.model.Score;
import com.nikron.tennisscoreboard.services.MatchScoreCalculationService;
import com.nikron.tennisscoreboard.services.OngoingMatchesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "match-score", urlPatterns = "/match-score")
public class MatchScoreController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Score match = OngoingMatchesService.getMatchNotFinished(UUID.fromString(req.getParameter("uuid")));
        req.setAttribute("title", "Game-match");
        if (match == null) {
            resp.sendRedirect("/matches");
            return;
        }
        req.setAttribute("uuid", req.getParameter("uuid"));
        req.setAttribute("scorePlayerOne", match.getScorePlayerOne());
        req.setAttribute("scorePlayerTwo", match.getScorePlayerTwo());
        req.setAttribute("gamePlayerOne", match.getGamePlayerOneWin());
        req.setAttribute("gamePlayerTwo", match.getGamePlayerTwoWin());
        req.setAttribute("setPlayerOne", match.getSetPlayerOneWin());
        req.setAttribute("setPlayerTwo", match.getSetPlayerTwoWin());
        req.setAttribute("playerOne", match.getPlayerOne());
        req.setAttribute("playerTwo", match.getPlayerTwo());
        req.getRequestDispatcher("/match_score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Score score = OngoingMatchesService.getMatchNotFinished(UUID.fromString(req.getParameter("uuid")));
        if (req.getParameter("addPointOne") != null){
            MatchScoreCalculationService.mainGame(score, score.getPlayerOne());
        } else {
            MatchScoreCalculationService.mainGame(score, score.getPlayerTwo());
        }
        resp.sendRedirect("/match-score?uuid="+ score.getUuid());
    }
}
