package com.nikron.tennisscoreboard.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PLAYERS")
public class Player implements Serializable {

    @Id
    @GeneratedValue(generator = "PLAYER_ID_SEQ")
    @Column(name = "player_id")
    private int id;

    @Column(name = "player_name")
    private String username;

    @OneToMany
    @JoinColumns({
            @JoinColumn(name = "player_one_id", referencedColumnName = "player_id"),
            @JoinColumn(name = "player_two_id", referencedColumnName = "player_id"),
            @JoinColumn(name = "player_winner_id", referencedColumnName = "player_id"),
    })
    private List<Match> matches;

    public Player(String username) {
        this.username = username;
    }

    public Player(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Player(int id, String username) {
        this.id = id;
        this.username = username;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
