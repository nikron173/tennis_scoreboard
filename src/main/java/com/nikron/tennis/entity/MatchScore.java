package com.nikron.tennis.entity;

import com.nikron.tennis.dto.PlayerDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@Builder
public class MatchScore {
    private final UUID id = UUID.randomUUID();
    private Player firstPlayer;
    private Player secondPlayer;
    private Player winnerPlayer;
    private final Score firstPlayerScore = new Score();
    private final Score secondPlayerScore = new Score();
    private boolean tieBreak;
}
