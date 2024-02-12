package com.nikron.tennis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {
    private Long matchId;
    private Long firstPlayerId;
    private String firstPlayerName;
    private Long secondPlayerId;
    private String secondPlayerName;
    private Long winnerPlayerId;
    private String winnerPlayerName;
}
