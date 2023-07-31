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
        Integer countPage;
        req.setAttribute("title", "Matches");
        if (req.getRequestURI().matches("/matches") && req.getQueryString() == null){
            countPage = finishedMatchesPersistenceService.getCountPageMatches(2);
            req.setAttribute("pageSize", countPage);
            List<Match> matches = finishedMatchesPersistenceService.getMatchStartEndDB(1,2);
            req.setAttribute("matches", matches);
            req.getRequestDispatcher("/matches.jsp").forward(req, resp);
        } else if (req.getRequestURI().matches("/matches") && req.getQueryString().matches("page=\\d+")){
            int page = Integer.parseInt(req.getParameter("page"));
            countPage = finishedMatchesPersistenceService.getCountPageMatches(2);
            int total = 2;
            List<Match> matches;
            if (page == 1) {}
            else {
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
            int page = Integer.parseInt(req.getParameter("page") == null ? String.valueOf(1) : req.getParameter("page"));
            countPage = finishedMatchesPersistenceService.getCountPageMatchesFilterPlayer(2, player);
            List<Match> matches;
            int total = 2;
            if (page == 1) {}
            else {
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
