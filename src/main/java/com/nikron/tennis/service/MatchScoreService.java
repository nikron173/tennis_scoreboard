package com.nikron.tennis.service;

import com.nikron.tennis.dto.PlayerDto;
import com.nikron.tennis.entity.MatchScore;
import com.nikron.tennis.entity.Player;
import com.nikron.tennis.entity.Score;
import com.nikron.tennis.exception.NotFoundResourceException;
import com.nikron.tennis.mapper.PlayerMapper;
import com.nikron.tennis.repository.MatchScoreRepository;
import com.nikron.tennis.repository.PlayerRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class MatchScoreService {

    private final MatchScoreRepository scoreRepository = MatchScoreRepository.getInstance();
    private final PlayerRepository playerRepository = PlayerRepository.getInstance();
    private final PlayerMapper playerMapper = PlayerMapper.getInstance();

    private static final MatchScoreService INSTANCE = new MatchScoreService();

    private MatchScoreService() {
    }

    public static MatchScoreService getInstance() {
        return INSTANCE;
    }

    public MatchScore create(PlayerDto firstPlayer, PlayerDto secondPlayer) {
        Player first;
        Player second;
        Optional<Player> firstOption = playerRepository.findByName(firstPlayer.getName());
        Optional<Player> secondOption = playerRepository.findByName(secondPlayer.getName());
        if (firstOption.isEmpty()) {
            first = playerRepository.save(playerMapper.convertToEntity(firstPlayer));
        } else {
            first = firstOption.get();
        }

        if (secondOption.isEmpty()) {
            second = playerRepository.save(playerMapper.convertToEntity(secondPlayer));
        } else {
            second = secondOption.get();
        }

        return scoreRepository.save(MatchScore.builder()
                .firstPlayer(first)
                .secondPlayer(second)
                .score(new Score())
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

    public MatchScore addPointFirstPlayer(UUID id) {
        MatchScore matchScore = scoreRepository.findById(id);
        matchScore.getScore().addPointFirstPlayer(1);


        return matchScore;
    }


    private boolean checkWinGame(int i) {
        return false;
    }
}
