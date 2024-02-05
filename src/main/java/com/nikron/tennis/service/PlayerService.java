package com.nikron.tennis.service;

import com.nikron.tennis.dto.PlayerDto;
import com.nikron.tennis.entity.Player;
import com.nikron.tennis.exception.DuplicateResourceException;
import com.nikron.tennis.exception.NotFoundResourceException;
import com.nikron.tennis.mapper.PlayerMapper;
import com.nikron.tennis.repository.PlayerRepository;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

public class PlayerService {
    private final PlayerRepository playerRepository = PlayerRepository.getInstance();

    private final PlayerMapper playerMapper = PlayerMapper.getInstance();

    private final static PlayerService INSTANCE = new PlayerService();

    private PlayerService() {
    }

    public static PlayerService getInstance() {
        return INSTANCE;
    }

    public PlayerDto findById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()) {
            return playerMapper.convertToDto(player.get());
        }
        throw new NotFoundResourceException("Игрок с id " + id + " не найден",
                HttpServletResponse.SC_NOT_FOUND);
    }

    public PlayerDto findByName(String name) {
        Optional<Player> player = playerRepository.findByName(name);
        if (player.isPresent()) {
            return playerMapper.convertToDto(player.get());
        }
        throw new NotFoundResourceException("Игрок с наименованием " + name + " не найден",
                HttpServletResponse.SC_NOT_FOUND);
    }

    public PlayerDto save(PlayerDto dto) {
        Player player = playerMapper.convertToEntity(dto);
        if (playerRepository.findByName(player.getName()).isPresent()) {
            throw new DuplicateResourceException("Игрок с наименованием " + dto.getName() + " уже существует",
                    HttpServletResponse.SC_BAD_REQUEST);
        }
        playerRepository.save(player);
        return playerMapper.convertToDto(player);
    }

    public void delete(Long id) {
        if (playerRepository.findById(id).isEmpty()) {
            throw new NotFoundResourceException("Игрок с id " + id + " не найден",
                    HttpServletResponse.SC_NOT_FOUND);
        }
        playerRepository.delete(id);
    }
}
