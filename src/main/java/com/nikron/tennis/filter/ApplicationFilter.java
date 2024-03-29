package com.nikron.tennis.filter;

import com.nikron.tennis.exception.ApplicationException;
import com.nikron.tennis.util.JspPath;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter(urlPatterns = "/*")
public class ApplicationFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (request.getRequestURI().equals(request.getContextPath() + "/")) {
            request.getRequestDispatcher(JspPath.getPathJsp("main-menu")).forward(request, response);
            return;
        }
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            if (e instanceof ApplicationException) {
                req.setAttribute("error", e.getMessage());
                request.getRequestDispatcher(JspPath.getPathJsp("error")).forward(request, response);
                return;
            }
            throw new RuntimeException(e);
        }
    }
}
