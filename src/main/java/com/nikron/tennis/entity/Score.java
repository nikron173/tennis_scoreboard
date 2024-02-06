package com.nikron.tennis.entity;

import java.util.HashMap;

public class Score {

    private final HashMap<ScoreName, Integer> score = new HashMap<>();

    public Score() {
        score.put(ScoreName.POINT, 0);
        score.put(ScoreName.GAME, 0);
        score.put(ScoreName.SET, 0);
    }

    public void addPoint() {
        score.put(ScoreName.POINT, score.get(ScoreName.POINT) + 1);
    }

    public void addGame() {
        score.put(ScoreName.GAME, score.get(ScoreName.GAME) + 1);
    }

    public void addSet() {
        score.put(ScoreName.SET, score.get(ScoreName.SET) + 1);
    }

    public int getPoint() {
        return score.get(ScoreName.POINT);
    }

    public int getGame() {
        return score.get(ScoreName.GAME);
    }

    public int getSet() {
        return score.get(ScoreName.SET);
    }

    public void resetPoint() {
        score.put(ScoreName.POINT, 0);
    }

    public void resetGame() {
        score.put(ScoreName.GAME, 0);
    }


    private enum ScoreName {
        POINT, GAME, SET
    }
}
