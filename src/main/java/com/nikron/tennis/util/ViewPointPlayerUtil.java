package com.nikron.tennis.util;

import java.util.Objects;

public class ViewPointPlayerUtil {
    private static String viewPointToString(int point) {
        switch (point) {
            case 0: return "0";
            case 1: return "15";
            case 2: return "30";
            case 3: return "40";
            default: return null;
        }
    }
    public static String viewPoint(int firstPoint, int secondPoint) {
        String firstPointStr = viewPointToString(firstPoint);
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
