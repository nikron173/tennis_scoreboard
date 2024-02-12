package com.nikron.tennis.controller;

import com.nikron.tennis.dto.MatchDto;
import com.nikron.tennis.exception.BadRequestException;
import com.nikron.tennis.exception.NotFoundResourceException;
import com.nikron.tennis.service.MatchService;
import com.nikron.tennis.util.JspPath;
import com.nikron.tennis.util.ValidParameter;
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
        int page = Objects.isNull(req.getParameter("page")) ? 1 : ValidParameter.getNumber(req, "page");
        if (page <= 0) throw new BadRequestException("Номер страницы не может быть отрицательным число или нулем.",
                HttpServletResponse.SC_BAD_REQUEST);
        if (Objects.nonNull(req.getParameter("filter_by_player_name"))) {
            int countPage = matchService
                    .lastPageSizeByPlayerName(req.getParameter("filter_by_player_name"));
            System.out.println("Количество страниц - " + countPage);
            if (page > countPage) {
                throw new NotFoundResourceException("Ресурс не найден", HttpServletResponse.SC_NOT_FOUND);
            }
            List<MatchDto> matches = matchService.findByPlayerName(
                    req.getParameter("filter_by_player_name"), page);
            req.setAttribute("matches", matches);
            req.setAttribute("countPage", countPage);
        } else {
            int countPage = matchService.lastPageSize();
            if (page > countPage) {
                throw new NotFoundResourceException("Ресурс не найден", HttpServletResponse.SC_NOT_FOUND);
            }
            List<MatchDto> matches = matchService.findAllByPage(page);
            req.setAttribute("matches", matches);
            req.setAttribute("countPage", countPage);
        }
        req.getRequestDispatcher(JspPath.getPathJsp("matches"))
                .forward(req, resp);
    }
}
