package com.nikron.tennis.util;

import com.nikron.tennis.exception.BadRequestException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;
import java.util.UUID;

public class ValidParameter {

    public static Integer getValidNumber(HttpServletRequest req, String parameter) {
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

    public static String getValidPlayerName(HttpServletRequest req, String parameter) {
        String name = req.getParameter(parameter);
        if (Objects.isNull(name)) {
            throw new BadRequestException("Не задан параметр " + parameter,
                    HttpServletResponse.SC_BAD_REQUEST);
        }
        if (name.isBlank()) {
            throw new BadRequestException("Имя не может состоять только из пробелов",
                    HttpServletResponse.SC_BAD_REQUEST);
        }
        if (name.length() < 3 || name.length() > 50) {
            throw new BadRequestException("Имя не может состоять меньше чем из 3 символов или больше 50 символов",
                    HttpServletResponse.SC_BAD_REQUEST);
        }
        if (!name.matches("([A-Za-zА-Яа-я]+)|([A-Za-zА-Яа-я]+\\s[A-Za-zА-Яа-я]+)")) {
            throw new BadRequestException("Имя не может состоять из спец. символов или чисел",
                    HttpServletResponse.SC_BAD_REQUEST);
        }
        return name;
    }

    public static UUID getValidId(HttpServletRequest req, String parameter) {
        String uuid = req.getParameter(parameter);
        if (Objects.isNull(uuid)) {
            throw new BadRequestException("Не задан индентификатор (id)", HttpServletResponse.SC_BAD_REQUEST);
        }
        try {
            return UUID.fromString(uuid);
        } catch (Exception e) {
            throw new BadRequestException("Не валидный идентификатор", HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
