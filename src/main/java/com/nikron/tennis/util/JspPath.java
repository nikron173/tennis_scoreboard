package com.nikron.tennis.util;

public class JspPath {
    private static final String pathJsp = "/jsp/%s.jsp";

    public static String getPathJsp(String content) {
        return String.format(pathJsp, content);
    }
}
