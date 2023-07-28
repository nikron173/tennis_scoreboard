CREATE SEQUENCE PLAYER_ID_SEQ;
CREATE TABLE PLAYERS (player_id bigint DEFAULT NEXT VALUE FOR PLAYER_ID_SEQ primary key not null,player_name varchar(50) unique not null);
CREATE SEQUENCE MATCH_ID_SEQ;
CREATE TABLE MATCHES (match_id bigint DEFAULT NEXT VALUE FOR MATCH_ID_SEQ primary key not null, player_one_id bigint not null, player_two_id bigint not null, player_winner_id bigint not null, foreign key (player_one_id) references PLAYERS(player_id), foreign key (player_two_id) references PLAYERS(player_id), foreign key (player_winner_id) references PLAYERS(player_id));