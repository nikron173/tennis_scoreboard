package com.nikron.tennis.controller;

import com.nikron.tennis.entity.MatchScore;
import com.nikron.tennis.exception.BadRequestException;
import com.nikron.tennis.service.MatchScoreService;
import com.nikron.tennis.util.JspPath;
import com.nikron.tennis.util.ValidParameter;
import com.nikron.tennis.util.ViewPointPlayerUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;


@WebServlet(urlPatterns = "/match-score")
public class MatchScoreController extends HttpServlet {
    private final MatchScoreService scoreService = MatchScoreService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID id = ValidParameter.getValidId(req, "uuid");
        MatchScore matchScore = scoreService.findById(id);
        req.setAttribute("uuid", id);
        req.setAttribute("firstPlayer", matchScore.getFirstPlayer());
        req.setAttribute("secondPlayer", matchScore.getSecondPlayer());
        req.setAttribute("firstScore", matchScore.getFirstPlayerScore());
        req.setAttribute("secondScore", matchScore.getSecondPlayerScore());
        req.setAttribute("firstPlayerViewPoint",
                ViewPointPlayerUtil.viewPoint(
                        matchScore.getFirstPlayerScore().getPoint(),
                        matchScore.getSecondPlayerScore().getPoint())
        );
        req.setAttribute("secondPlayerViewPoint",
                ViewPointPlayerUtil.viewPoint(
                        matchScore.getSecondPlayerScore().getPoint(),
                        matchScore.getFirstPlayerScore().getPoint())
        );
        req.getRequestDispatcher(JspPath.getPathJsp("match-score")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MatchScore matchScore;
        if (Objects.isNull(req.getParameter("first_player"))
                && Objects.isNull(req.getParameter("second_player"))) {
            throw new BadRequestException("Не корректный запрос (не заданы параметры первого и второго игрока)",
                    HttpServletResponse.SC_BAD_REQUEST);
        }
        if (Objects.nonNull(req.getParameter("first_player"))) {
            matchScore = scoreService.game(UUID.fromString(req.getParameter("uuid")),
                    req.getParameter("first_player"));
        } else {
            matchScore = scoreService.game(UUID.fromString(req.getParameter("uuid")),
                    req.getParameter("second_player"));
        }
        if (Objects.nonNull(matchScore.getWinnerPlayer())) {
            resp.sendRedirect("/matches");
            return;
        }
        resp.sendRedirect(String.format("/match-score?uuid=%s", matchScore.getId()));
    }
}
