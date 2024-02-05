package com.nikron.tennis.repository;

import com.nikron.tennis.entity.MatchScore;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MatchScoreRepository {

    private final ConcurrentHashMap<UUID, MatchScore> matches = new ConcurrentHashMap<>();

    private final static MatchScoreRepository INSTANCE = new MatchScoreRepository();

    private MatchScoreRepository(){}

    public static MatchScoreRepository getInstance(){
        return INSTANCE;
    }

    public MatchScore save(MatchScore matchScore) {
        matches.put(matchScore.getId(), matchScore);
        return matchScore;
    }

    public MatchScore findById(UUID id) {
        return matches.get(id);
    }

    public MatchScore delete(UUID id) {
        return matches.remove(id);
    }

    public ConcurrentHashMap<UUID, MatchScore> getMatches() {
        return matches;
    }
}
