package com.nikron.tennis.util;

import java.util.Objects;

public class ViewPointPlayerUtil {
    private static String viewPointToString(int point) {
        return switch (point) {
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default -> null;
        };
    }
    public static String viewPoint(int firstPoint, int secondPoint) {
        String firstPointStr = viewPointToString(firstPoint);
        String secondPointStr = viewPointToString(secondPoint);
        if (Objects.nonNull(firstPointStr) && firstPointStr.equals("40") && Objects.isNull(secondPointStr)) {
            return "Меньше";
        }
        if (Objects.isNull(firstPointStr)) {
            if (firstPoint > secondPoint) {
                return "Больше";
            } else if (firstPoint < secondPoint) {
                return "Меньше";
            } else {
                return "Равно";
            }
        }
        return firstPointStr;
    }
}
