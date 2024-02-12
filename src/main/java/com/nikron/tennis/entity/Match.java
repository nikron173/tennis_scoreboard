package com.nikron.tennis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "first_player_id",
            foreignKey = @ForeignKey(name = "fk_matches_player_first_id"))
    private Player firstPlayer;

    @ManyToOne
    @JoinColumn(name = "second_player_id",
            foreignKey = @ForeignKey(name = "fk_matches_player_second_id"))
    private Player secondPlayer;

    @ManyToOne
    @JoinColumn(name = "winner_player_id",
            foreignKey = @ForeignKey(name = "fk_matches_player_winner_id"))
    private Player winnerPlayer;
}
