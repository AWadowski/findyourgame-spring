package org.example.service;


import lombok.AllArgsConstructor;
import org.example.GameEntity;
import org.example.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GamesService {

    private final GameRepository gameRepository;
    public List<GameEntity> findAllMultiplayerGames() {
        return gameRepository.findAllMultiplayerGames();
    }

    public List<GameEntity> findByPlatform(String platform) {
        return gameRepository.findByPlatform(platform);
    }

    public List<GameEntity> findByGameType(String gameType) {
        return gameRepository.findByGameType(gameType);
    }
}
