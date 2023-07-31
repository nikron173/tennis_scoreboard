package com.nikron.tennisscoreboard.controllers;

import com.nikron.tennisscoreboard.dao.DaoPlayer;
import com.nikron.tennisscoreboard.model.Match;
import com.nikron.tennisscoreboard.model.Player;
import com.nikron.tennisscoreboard.services.FinishedMatchesPersistenceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "matches", urlPatterns = "/matches")
public class MatchesController extends HttpServlet {
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    private final DaoPlayer daoPlayer = new DaoPlayer();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "Matches");
        Integer countPage = finishedMatchesPersistenceService.getCountPageMatches(5);
        int page;
        int total = 5;
        try {
            page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
            if (page < 1 || page > countPage) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e){
            resp.sendError(400, "Invalid number page!");
            return;
        }

        if (req.getRequestURI().matches("/matches") && req.getQueryString() == null){
            req.setAttribute("pageSize", countPage);
            List<Match> matches = finishedMatchesPersistenceService.getMatchStartEndDB(1,5);
            req.setAttribute("matches", matches);
            req.getRequestDispatcher("/matches.jsp").forward(req, resp);
        } else if (req.getRequestURI().matches("/matches") && req.getQueryString().matches("page=\\d+"))
        {
            List<Match> matches;
            if (page != 1) {
                page -= 1;
                page = page*total+1;
            }
            matches = finishedMatchesPersistenceService.getMatchStartEndDB(page,total);
            req.setAttribute("matches", matches);
            req.setAttribute("pageSize", countPage);
            req.getRequestDispatcher("/matches.jsp").forward(req, resp);
        } else if (req.getRequestURI().matches("/matches")
                && (req.getQueryString().matches("page=\\d+&filter_by_player_name=\\w+")
                || req.getQueryString().matches("filter_by_player_name=\\w+")))
        {
            String username = req.getParameter("filter_by_player_name");
            Player player;
            if (daoPlayer.findByUsername(username).isPresent()){
                player = daoPlayer.findByUsername(username).get();
            } else {
                resp.sendError(404, "Player name not found");
                return;
            }
            countPage = finishedMatchesPersistenceService.getCountPageMatchesFilterPlayer(5, player);
            if (page > countPage){
                resp.sendError(400, "Invalid number page!");
                return;
            }
            List<Match> matches;
            if (page != 1) {
                page -= 1;
                page = page*total+1;
            }
            matches = finishedMatchesPersistenceService.getMatchStartEndDB(page, total, player);
            req.setAttribute("matches", matches);
            req.setAttribute("username", username);
            req.setAttribute("pageSize", countPage);
            req.getRequestDispatcher("/matches.jsp").forward(req, resp);
        }
    }
}
