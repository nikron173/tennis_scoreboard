package com.nikron.tennisscoreboard.controllers;

import com.nikron.tennisscoreboard.dao.DaoPlayer;
import com.nikron.tennisscoreboard.model.Score;
import com.nikron.tennisscoreboard.model.Player;
import com.nikron.tennisscoreboard.services.OngoingMatchesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "newMatch", urlPatterns = "/new-match")
public class NewMatchController extends HttpServlet {
    private final DaoPlayer daoPlayer = new DaoPlayer();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "New-match");
        req.getRequestDispatcher("/new_match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerOne = req.getParameter("playerOne");
        String playerTwo = req.getParameter("playerTwo");

        if (playerOne == null || playerTwo == null) {
            resp.sendError(400, "Invalid player name.");
            return;
        }

        if (playerOne.isBlank() || playerTwo.isBlank()) {
            resp.sendError(400, "Invalid player name.");
            return;
        }

        if (daoPlayer.findByUsername(playerOne).isEmpty()) daoPlayer.save(new Player(playerOne));
        if (daoPlayer.findByUsername(playerTwo).isEmpty()) daoPlayer.save(new Player(playerTwo));
        Score match = new Score(daoPlayer.findByUsername(playerOne).get(), daoPlayer.findByUsername(playerTwo).get());
        match.setUuid(UUID.randomUUID());
        OngoingMatchesService.addMatchNotFinished(match.getUuid(), match);
        resp.sendRedirect("/match-score?uuid="+match.getUuid());

    }
}
