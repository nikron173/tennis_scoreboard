package com.nikron.tennis.controller;

import com.nikron.tennis.dto.PlayerDto;
import com.nikron.tennis.entity.Player;
import com.nikron.tennis.service.PlayerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/player")
public class PlayerController extends HttpServlet {

    private final PlayerService playerService = PlayerService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlayerDto dto = playerService.findById(
                Long.parseLong(req.getParameter("id"))
        );
        var writer = resp.getWriter();
        writer.println("<h1>" + dto.getName() + "</h1>");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlayerDto dto = playerService.save(PlayerDto.builder()
                .name(req.getParameter("name"))
                .build());
        var writer = resp.getWriter();
        writer.println("<h1>" + dto.getName() + "</h1>");
        writer.close();
    }
}
