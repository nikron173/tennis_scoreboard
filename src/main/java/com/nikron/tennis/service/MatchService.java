package com.nikron.tennis.service;

import com.nikron.tennis.dto.MatchDto;
import com.nikron.tennis.entity.Match;
import com.nikron.tennis.entity.Player;
import com.nikron.tennis.exception.NotFoundResourceException;
import com.nikron.tennis.mapper.MatchMapper;
import com.nikron.tennis.repository.MatchRepository;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MatchService {

    private final Integer pageSize = 3;

    private static final MatchService INSTANCE = new MatchService();
    private final MatchRepository matchRepository = MatchRepository.getInstance();
    private final MatchMapper matchMapper = MatchMapper.getInstance();

    private MatchService() {
    }

    public static MatchService getInstance() {
        return INSTANCE;
    }

    public Integer lastPageSize() {
        return matchRepository.lastPageNumber(pageSize);
    }

    public Integer lastPageSizeByPlayerName(String playerName) {
        return matchRepository.lastPageNumberPlayerName(pageSize, playerName);
    }

    public List<MatchDto> findAll() {
        return matchRepository.findAll().stream()
                .map(matchMapper::convertToDto)
                .toList();
    }

    public MatchDto findById(UUID id) {
        Optional<Match> match = matchRepository.findById(id);
        if (match.isPresent()) {
            return matchMapper.convertToDto(match.get());
        }
        throw new NotFoundResourceException("Матч с id " + id + " не найден",
                HttpServletResponse.SC_NOT_FOUND);
    }

    public List<MatchDto> findByPlayerName(String playerName, int page) {
        return matchRepository.findMatchByPlayerNamePageSize(pageSize, playerName, page)
                .stream().map(matchMapper::convertToDto).toList();
    }
}
