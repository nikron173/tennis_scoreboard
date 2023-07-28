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
        req.getRequestDispatcher("/new_match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("playerOne").length() > 3 ||
                req.getParameter("playerTwo").length() > 3)
        {
            if (daoPlayer.findByUsername(req.getParameter("playerOne")).isEmpty()) daoPlayer.save(new Player(req.getParameter("playerOne")));
            if (daoPlayer.findByUsername(req.getParameter("playerTwo")).isEmpty()) daoPlayer.save(new Player(req.getParameter("playerTwo")));
            Score match = new Score(daoPlayer.findByUsername(req.getParameter("playerOne")).get(), daoPlayer.findByUsername(req.getParameter("playerTwo")).get());
            match.setUuid(UUID.randomUUID());
            OngoingMatchesService.addMatchNotFinished(match.getUuid(), match);

            resp.sendRedirect("/match-score?uuid="+match.getUuid());
        }
    }
}
