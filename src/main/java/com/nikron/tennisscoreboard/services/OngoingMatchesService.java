package com.nikron.tennisscoreboard.services;

import com.nikron.tennisscoreboard.model.Score;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static Map<UUID, Score> matchMapIsNotFinished = new ConcurrentHashMap<>();

    public static void removeMatchFinished(UUID uuid){
        matchMapIsNotFinished.remove(uuid);
    }

    public static Score getMatchNotFinished(UUID uuid){
       return matchMapIsNotFinished.get(uuid);
    }

    public static void addMatchNotFinished(UUID uuid, Score score){
        matchMapIsNotFinished.put(uuid, score);
    }

    public static Map<UUID, Score> getMatchMapIsNotFinished(){
        return matchMapIsNotFinished;
    }
}
