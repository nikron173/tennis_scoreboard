package com.nikron.tennis.mapper;

import com.nikron.tennis.dto.MatchDto;
import com.nikron.tennis.entity.Match;

public class MatchMapper implements Mapper<MatchDto, Match> {

    public static final MatchMapper INSTANCE = new MatchMapper();

    private MatchMapper(){}

    public static MatchMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public MatchDto convertToDto(Match entity) {
        return null;
    }

    @Override
    public Match convertToEntity(MatchDto dto) {
        return null;
    }
}
