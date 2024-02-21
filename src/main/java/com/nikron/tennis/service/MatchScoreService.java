package com.nikron.tennis.service;

import com.nikron.tennis.dto.PlayerDto;
import com.nikron.tennis.entity.Match;
import com.nikron.tennis.entity.MatchScore;
import com.nikron.tennis.entity.Player;
import com.nikron.tennis.entity.Score;
import com.nikron.tennis.exception.BadRequestException;
import com.nikron.tennis.exception.NotFoundResourceException;
import com.nikron.tennis.mapper.PlayerMapper;
import com.nikron.tennis.repository.MatchRepository;
import com.nikron.tennis.repository.MatchScoreRepository;
import com.nikron.tennis.repository.PlayerRepository;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class MatchScoreService {

    private final MatchScoreRepository scoreRepository = MatchScoreRepository.getInstance();
    private final PlayerRepository playerRepository = PlayerRepository.getInstance();
    private final PlayerMapper playerMapper = PlayerMapper.getInstance();

    private final MatchRepository matchRepository = MatchRepository.getInstance();

    private static final MatchScoreService INSTANCE = new MatchScoreService();

    private MatchScoreService() {
    }

    public static MatchScoreService getInstance() {
        return INSTANCE;
    }

    public MatchScore create(PlayerDto firstPlayer, PlayerDto secondPlayer) {
        Optional<Player> firstOption = playerRepository.findByName(firstPlayer.getName());
        Player first = firstOption.orElseGet(
                () -> playerRepository.save(playerMapper.convertToEntity(firstPlayer))
        );
        Optional<Player> secondOption = playerRepository.findByName(secondPlayer.getName());
        Player second = secondOption.orElseGet(
                () -> playerRepository.save(playerMapper.convertToEntity(secondPlayer))
        );
        if (first.getName().equals(second.getName())) {
            throw new BadRequestException("Матч не может состоять с одним и тем же участником",
                    HttpServletResponse.SC_BAD_REQUEST);
        }
        return scoreRepository.save(MatchScore.builder()
                .firstPlayer(first)
                .secondPlayer(second)
                .build()
        );
    }

    public MatchScore findById(UUID id) {
        MatchScore matchScore = scoreRepository.findById(id);
        if (Objects.nonNull(matchScore)) return matchScore;
        throw new NotFoundResourceException("Матч с id " + id + " не найден",
                HttpServletResponse.SC_NOT_FOUND);
    }

    public MatchScore delete(UUID id) {
        MatchScore matchScore = scoreRepository.findById(id);
        if (Objects.nonNull(matchScore)) {
            scoreRepository.delete(id);
            return matchScore;
        }
        throw new NotFoundResourceException("Матч с id " + id + " не найден",
                HttpServletResponse.SC_NOT_FOUND);
    }

    public MatchScore game(UUID id, String player) {
        MatchScore matchScore = findById(id);
        if (matchScore.getFirstPlayer().getName().equals(player)) {
            step(matchScore,
                    matchScore.getFirstPlayerScore(),
                    matchScore.getSecondPlayerScore(),
                    player
            );
        } else if (matchScore.getSecondPlayer().getName().equals(player)) {
            step(matchScore,
                    matchScore.getSecondPlayerScore(),
                    matchScore.getFirstPlayerScore(),
                    player
            );
        } else {
            throw new BadRequestException("В матче с идентификатором " + id + " нет игрока с именем " + player,
                    HttpServletResponse.SC_BAD_REQUEST);
        }
        return matchScore;
    }

    private void step(MatchScore matchScore, Score one, Score two, String player) {
        one.addPoint();
        if (matchScore.isTieBreak()) {
            if (checkTieBreak(one, two)) {
                one.addSet();
                resetGame(matchScore);
                resetPoint(matchScore);
                matchScore.setTieBreak(false);
            }
        } else {
            if (checkWinGame(one, two)) {
                one.addGame();
                resetPoint(matchScore);
            }
            if (one.getGame() == two.getGame() && one.getGame() == 6) {
                matchScore.setTieBreak(true);
                return;
            }
            if (checkWinSet(one, two)) {
                one.addSet();
                resetGame(matchScore);
            }
        }
        if (checkWin(one)) {
            matchScore.setWinnerPlayer(player.equals("first_player") ?
                    matchScore.getFirstPlayer() : matchScore.getSecondPlayer());
            Match match = Match.builder()
                    .firstPlayer(matchScore.getFirstPlayer())
                    .secondPlayer(matchScore.getSecondPlayer())
                    .winnerPlayer(matchScore.getWinnerPlayer())
                    .build();
            matchRepository.save(match);
            delete(matchScore.getId());
        }
    }

    private boolean checkTieBreak (Score scoreOne, Score scoreTwo) {
        return scoreOne.getPoint() >= 7 &&
                Math.abs(scoreOne.getPoint() - scoreTwo.getPoint()) >= 2 &&
                scoreOne.getPoint() > scoreTwo.getPoint();
    }

    private boolean checkWinGame(Score scoreOne, Score scoreTwo) {
        return scoreOne.getPoint() >= 4 &&
                Math.abs(scoreOne.getPoint() - scoreTwo.getPoint()) >= 2 &&
                scoreOne.getPoint() > scoreTwo.getPoint();
    }

    private boolean checkWinSet(Score scoreOne, Score scoreTwo) {
        return (scoreOne.getGame() == 6 || scoreOne.getGame() == 7)
                && scoreOne.getGame() > scoreTwo.getGame()
                && Math.abs(scoreOne.getGame() - scoreTwo.getGame()) >= 2;
    }

    private boolean checkWin(Score score) {
        return score.getSet() == 2;
    }

    private void resetPoint(MatchScore matchScore) {
        matchScore.getFirstPlayerScore().resetPoint();
        matchScore.getSecondPlayerScore().resetPoint();
    }

    private void resetGame(MatchScore matchScore) {
        matchScore.getFirstPlayerScore().resetGame();
        matchScore.getSecondPlayerScore().resetGame();
    }

    public Map<UUID, MatchScore> findAll() {
        return scoreRepository.getMatches();
    }
}
