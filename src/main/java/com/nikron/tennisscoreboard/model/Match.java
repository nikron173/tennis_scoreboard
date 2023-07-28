package com.nikron.tennisscoreboard.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "MATCHES")
public class Match implements Serializable {
    @Id
    @GeneratedValue(generator = "MATCH_ID_SEQ")
    @Column(name = "match_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_one_id", referencedColumnName = "player_id")
    private Player playerOne;

    @ManyToOne
    @JoinColumn(name = "player_two_id", referencedColumnName = "player_id")
    private Player playerTwo;

    @ManyToOne
    @JoinColumn(name = "player_winner_id", referencedColumnName = "player_id")
    private Player playerWinner;

    public Match(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public Match(Player playerOne, Player playerTwo, Player playerWinner) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerWinner = playerWinner;
    }

    public Match(int id, Player playerOne, Player playerTwo, Player playerWinner) {
        this.id = id;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.playerWinner = playerWinner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public Player getPlayerWinner() {
        return playerWinner;
    }

    public Match() {
    }

    public void setPlayerWinner(Player winnerId) {
        this.playerWinner = winnerId;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", playerOne=" + playerOne +
                ", playerTwo=" + playerTwo +
                ", winnerId=" + playerWinner +
                '}';
    }
}
