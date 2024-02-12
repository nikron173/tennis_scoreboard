package com.nikron.tennis.controller;

import com.nikron.tennis.dto.MatchDto;
import com.nikron.tennis.service.MatchService;
import com.nikron.tennis.util.JspPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/matches")
public class MatchesController extends HttpServlet {

    private final MatchService matchService = MatchService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.nonNull(req.getParameter("page")) &&
                Objects.nonNull(req.getParameter("filter_by_player_name"))) {
            List<MatchDto> matches = matchService.findByPlayerName(
                    req.getParameter("filter_by_player_name"),
                    Integer.parseInt(req.getParameter("page"))
            );
            int countPage = matchService
                    .lastPageSizeByPlayerName(req.getParameter("filter_by_player_name"));
            req.setAttribute("matches", matches);
            req.setAttribute("countPage", countPage);
        } else if (Objects.nonNull(req.getParameter("filter_by_player_name"))) {
            List<MatchDto> matches = matchService.findByPlayerName(
                    req.getParameter("filter_by_player_name"), 1);
            int countPage = matchService
                    .lastPageSizeByPlayerName(req.getParameter("filter_by_player_name"));
            req.setAttribute("matches", matches);
            req.setAttribute("countPage", countPage);
        } else {
            int page = Objects.nonNull(req.getParameter("page")) ?
                    Integer.parseInt(req.getParameter("page")) : 1;
            List<MatchDto> matches = matchService.findAllByPage(page);
            int countPage = matchService.lastPageSize();
            req.setAttribute("matches", matches);
            req.setAttribute("countPage", countPage);
        }
        req.getRequestDispatcher(JspPath.getPathJsp("matches"))
                .forward(req, resp);
    }
}
