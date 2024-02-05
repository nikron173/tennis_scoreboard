package com.nikron.tennis.entity;

import java.util.HashMap;

public class Score {

    private final HashMap<ScoreName, int[]> score = new HashMap<>();

    public Score() {
        score.put(ScoreName.POINT, new int[2]);
        score.put(ScoreName.GAME, new int[2]);
        score.put(ScoreName.SET, new int[2]);
    }

    public void addPointFirstPlayer(int point) {
        score.get(ScoreName.POINT)[0] += point;
    }

    public void addPointSecondPlayer(int point) {
        score.get(ScoreName.POINT)[1] += point;
    }

    public void addGameFirstPlayer() {
        score.get(ScoreName.GAME)[0] += 1;
    }

    public void addGameSecondPlayer() {
        score.get(ScoreName.GAME)[1] += 1;
    }

    public void addSetFirstPlayer() {
        score.get(ScoreName.SET)[0] += 1;
    }

    public void addSetSecondPlayer() {
        score.get(ScoreName.SET)[1] += 1;
    }

    public int getPointFirstPlayer() {
        return score.get(ScoreName.POINT)[0];
    }

    public int getPointSecondPlayer() {
        return score.get(ScoreName.POINT)[1];
    }

    public int getGameFirstPlayer() {
        return score.get(ScoreName.GAME)[0];
    }

    public int getGameSecondPlayer() {
        return score.get(ScoreName.GAME)[1];
    }

    public int getSetFirstPlayer() {
        return score.get(ScoreName.SET)[0];
    }

    public int getSetSecondPlayer() {
        return score.get(ScoreName.SET)[1];
    }


    public enum ScoreName {
        POINT, GAME, SET
    }
}
