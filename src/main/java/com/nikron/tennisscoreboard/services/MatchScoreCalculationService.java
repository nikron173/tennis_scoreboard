package com.nikron.tennisscoreboard.services;

import com.nikron.tennisscoreboard.model.Match;
import com.nikron.tennisscoreboard.model.Score;
import com.nikron.tennisscoreboard.model.Player;

public class MatchScoreCalculationService {
    private static void addPointPlayer(Score score, Player player){
        if (score.getPlayerTwo().getUsername().equals(player.getUsername())){
            score.setScorePlayerTwo(score.getScorePlayerTwo() >= 30 ? score.getScorePlayerTwo() + 10 : score.getScorePlayerTwo() +15);
        } else {
            score.setScorePlayerOne(score.getScorePlayerOne() >= 30 ? score.getScorePlayerOne() + 10 : score.getScorePlayerOne() +15);
        }
    }

    private static void addPointGame(Score score, Player player){
        if (score.getPlayerOne().getUsername().equals(player.getUsername())){
            score.setGamePlayerOneWin(score.getGamePlayerOneWin()+1);
        } else {
            score.setGamePlayerTwoWin(score.getGamePlayerTwoWin()+1);
        }
    }

    private static void addPointSet(Score score, Player player){
        if (score.getPlayerOne().getUsername().equals(player.getUsername())){
            score.setSetPlayerOneWin(score.getSetPlayerOneWin()+1);
        } else {
            score.setSetPlayerTwoWin(score.getSetPlayerTwoWin()+1);
        }
    }

    public static void mainGame(Score score, Player player){
        addPointPlayer(score, player);
        if (checkWinGamePlayerOne(score))
        {
            score.setScorePlayerOne(0);
            score.setScorePlayerTwo(0);
            addPointGame(score, score.getPlayerOne());
        } else if (checkWinGamePlayerTwo(score)) {
            score.setScorePlayerOne(0);
            score.setScorePlayerTwo(0);
            addPointGame(score, score.getPlayerTwo());
        }
        if (checkWinSetPlayerOne(score)){
            score.setGamePlayerTwoWin(0);
            score.setGamePlayerOneWin(0);
            addPointSet(score, score.getPlayerOne());
        } else if (checkWinSetPlayerTwo(score)){
            score.setGamePlayerTwoWin(0);
            score.setGamePlayerOneWin(0);
            addPointSet(score, score.getPlayerTwo());
        }
        if (score.getSetPlayerOneWin() + score.getSetPlayerTwoWin() >= 3){
            if (score.getSetPlayerOneWin() > score.getSetPlayerTwoWin()){
                score.setFinished(true);
                score.setPlayerWinner(score.getPlayerOne());
            } else {
                score.setFinished(true);
                score.setPlayerWinner(score.getPlayerTwo());
            }

        }
        if (score.isFinished()) {

            new FinishedMatchesPersistenceService().addMatchDB(new Match(score.getPlayerOne(), score.getPlayerTwo(), score.getPlayerWinner()));
            OngoingMatchesService.removeMatchFinished(score.getUuid());
        }
    }

    private static boolean checkWinGamePlayerOne(Score score){
        return score.getScorePlayerOne() > 40 && score.getScorePlayerTwo() < 40 ||
                (Math.abs(score.getScorePlayerOne() - score.getScorePlayerTwo()) >= 20 &&
                        (score.getScorePlayerOne() >= 40 && score.getScorePlayerTwo()>=40) &&
                        score.getScorePlayerOne() > score.getScorePlayerTwo());
    }

    private static boolean checkWinGamePlayerTwo(Score score){
        return score.getScorePlayerTwo() > 40 && score.getScorePlayerOne() < 40 ||
                (Math.abs(score.getScorePlayerOne() - score.getScorePlayerTwo()) >= 20 &&
                        (score.getScorePlayerOne() >= 40 && score.getScorePlayerTwo()>=40) &&
                        score.getScorePlayerOne() < score.getScorePlayerTwo());
    }

    private static boolean checkWinSetPlayerOne(Score score){
        return (score.getGamePlayerOneWin() >= 6 && score.getGamePlayerTwoWin() < 6 &&
                Math.abs(score.getGamePlayerOneWin() - score.getGamePlayerTwoWin()) >= 2) ||
                (Math.abs(score.getGamePlayerOneWin() - score.getGamePlayerTwoWin()) >= 2 &&
                        (score.getGamePlayerOneWin() >= 5) && (score.getGamePlayerTwoWin() >= 5) &&
                        score.getGamePlayerOneWin() > score.getGamePlayerTwoWin());
    }

    private static boolean checkWinSetPlayerTwo(Score score){
        return (score.getGamePlayerTwoWin() >= 6 && score.getGamePlayerOneWin() < 6 &&
                Math.abs(score.getGamePlayerOneWin() - score.getGamePlayerTwoWin()) >= 2) ||
                (Math.abs(score.getGamePlayerOneWin() - score.getGamePlayerTwoWin()) >= 2 &&
                        (score.getGamePlayerOneWin() >= 5) && (score.getGamePlayerTwoWin() >= 5) &&
                        score.getGamePlayerOneWin() < score.getGamePlayerTwoWin());
    }
}
