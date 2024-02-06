package com.nikron.tennis.mapper;

import com.nikron.tennis.dto.MatchDto;
import com.nikron.tennis.dto.PlayerDto;
import com.nikron.tennis.entity.Match;

public class MatchMapper implements Mapper<MatchDto, Match> {

    private static final MatchMapper INSTANCE = new MatchMapper();
    private final PlayerMapper playerMapper = PlayerMapper.getInstance();

    private MatchMapper(){}

    public static MatchMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public MatchDto convertToDto(Match entity) {
        return MatchDto.builder()
                .matchId(entity.getId())
                .firstPlayerId(entity.getFirstPlayer().getId())
                .firstPlayerName(entity.getFirstPlayer().getName())
                .secondPlayerId(entity.getSecondPlayer().getId())
                .secondPlayerName(entity.getSecondPlayer().getName())
                .winnerPlayerId(entity.getWinnerPlayer().getId())
                .winnerPlayerName(entity.getWinnerPlayer().getName())
                .build();
    }

    @Override
    public Match convertToEntity(MatchDto dto) {
        return Match.builder()
                .id(dto.getMatchId())
                .firstPlayer(playerMapper
                        .convertToEntity(PlayerDto.builder()
                                .id(dto.getFirstPlayerId())
                                .name(dto.getFirstPlayerName())
                                .build()))
                .secondPlayer(playerMapper
                        .convertToEntity(PlayerDto.builder()
                                .id(dto.getSecondPlayerId())
                                .name(dto.getSecondPlayerName())
                                .build()))
                .winnerPlayer(playerMapper
                        .convertToEntity(PlayerDto.builder()
                                .id(dto.getWinnerPlayerId())
                                .name(dto.getWinnerPlayerName())
                                .build()))
                .build();
    }
}
