package com.nikron.tennis.util;

import com.nikron.tennis.exception.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

public class ValidParameter {

    public static Integer getNumber(HttpServletRequest req, String parameter) {
        if (Objects.isNull(req.getParameter(parameter))) {
            throw new BadRequestException("Не задан параметр " + parameter, HttpServletResponse.SC_BAD_REQUEST);
        }
        try {
            return Integer.parseInt(req.getParameter(parameter));
        } catch (Exception e) {
            throw new BadRequestException("Не валидный параметр " + parameter + ": " + req.getParameter(parameter),
                    HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
