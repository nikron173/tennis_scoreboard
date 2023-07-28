package com.nikron.tennisscoreboard.model;

import java.util.UUID;

public class Score {
    private final Player playerOne;

    private final Player playerTwo;

    private Player playerWinner;
    private boolean finished;

    private UUID uuid;

    private int scorePlayerOne;
    private int scorePlayerTwo;

    private int gamePlayerOneWin;
    private int gamePlayerTwoWin;
    private int setPlayerOneWin;
    private int setPlayerTwoWin;

    public Score(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayerWinner() {
        return playerWinner;
    }

    public void setPlayerWinner(Player playerWinner) {
        this.playerWinner = playerWinner;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
    public int getScorePlayerOne() {
        return scorePlayerOne;
    }

    public void setScorePlayerOne(int scorePlayerOne) {
        this.scorePlayerOne = scorePlayerOne;
    }

    public int getScorePlayerTwo() {
        return scorePlayerTwo;
    }

    public int getGamePlayerOneWin() {
        return gamePlayerOneWin;
    }

    public void setGamePlayerOneWin(int gamePlayerOneWin) {
        this.gamePlayerOneWin = gamePlayerOneWin;
    }

    public int getGamePlayerTwoWin() {
        return gamePlayerTwoWin;
    }

    public void setGamePlayerTwoWin(int gamePlayerTwoWin) {
        this.gamePlayerTwoWin = gamePlayerTwoWin;
    }

    public int getSetPlayerOneWin() {
        return setPlayerOneWin;
    }

    public void setSetPlayerOneWin(int setPlayerOneWin) {
        this.setPlayerOneWin = setPlayerOneWin;
    }

    public int getSetPlayerTwoWin() {
        return setPlayerTwoWin;
    }

    public void setSetPlayerTwoWin(int setPlayerTwoWin) {
        this.setPlayerTwoWin = setPlayerTwoWin;
    }

    public void setScorePlayerTwo(int scorePlayerTwo) {
        this.scorePlayerTwo = scorePlayerTwo;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}
