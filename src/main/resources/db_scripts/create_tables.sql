CREATE TABLE IF NOT EXISTS players (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name varchar(100)
);

CREATE INDEX IF NOT EXISTS players_full_name_index ON players(full_name);

CREATE TABLE IF NOT EXISTS matches (
    id UUID PRIMARY KEY default random_uuid(),
    first_player_id BIGINT NOT NULL,
    second_player_id BIGINT NOT NULL,
    winner_player_id BIGINT NOT NULL
);

ALTER TABLE matches ADD CONSTRAINT fk_player_match_first_id FOREIGN KEY (first_player_id) REFERENCES players(id);
ALTER TABLE matches ADD CONSTRAINT fk_player_match_second_id FOREIGN KEY (second_player_id) REFERENCES players(id);
ALTER TABLE matches ADD CONSTRAINT fk_player_match_winner_id FOREIGN KEY (winner_player_id) REFERENCES players(id);
