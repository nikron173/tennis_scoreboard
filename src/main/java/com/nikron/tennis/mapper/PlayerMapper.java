package com.nikron.tennis.mapper;

import com.nikron.tennis.dto.PlayerDto;
import com.nikron.tennis.entity.Player;

public class PlayerMapper implements Mapper<PlayerDto, Player> {

    private static final PlayerMapper INSTANCE = new PlayerMapper();

    private PlayerMapper(){}

    public static PlayerMapper getInstance(){
        return INSTANCE;
    }

    @Override
    public PlayerDto convertToDto(Player entity) {
        return PlayerDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    @Override
    public Player convertToEntity(PlayerDto dto) {
        return Player.builder()
                .name(dto.getName())
                .build();
    }
}
