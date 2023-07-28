package com.nikron.tennisscoreboard.services;

import com.nikron.tennisscoreboard.dao.DaoMatch;
import com.nikron.tennisscoreboard.model.Match;
import com.nikron.tennisscoreboard.model.Player;
import java.util.List;

public class FinishedMatchesPersistenceService {
    private final DaoMatch daoMatch = new DaoMatch();
    public void addMatchDB(Match match){
        Match match1 = new Match(match.getPlayerOne(), match.getPlayerTwo(), match.getPlayerWinner());
        daoMatch.save(match1);
    }

    public Match getMatchDB(int id){
        if (daoMatch.findById(id).isPresent()){
            return daoMatch.findById(id).get();
        }
        throw new RuntimeException("Not found Match");
    }

    public List<Match> getMatchStartEndDB(int start, int total){
        return daoMatch.getMatchStartEndDB(start, total);
    }

    public Integer getCountPageMatches(int pageSize){
        return daoMatch.getCountPageMatches(pageSize);
    }

    public List<Match> getAllMatchFilterPlayer(Player player){
        return daoMatch.findMatchesByPlayer(player);
    }

    public Integer getCountPageMatchesFilterPlayer(int pageSize, Player player){
        return daoMatch.getCountPageMatches(pageSize, player);
    }

    public List<Match> getMatchStartEndDB(int start, int total, Player player){
        return daoMatch.getMatchStartEndDB(start, total, player);
    }
}
