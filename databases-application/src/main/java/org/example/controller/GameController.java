package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.GameEntity;
import org.example.GameRepository;
import org.example.service.GamesGenerator;
import org.example.service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@AllArgsConstructor
public class GameController {

    private final GamesService gameService;
    private final GamesGenerator gamesGenerator;


    @GetMapping("/multiplayer")
    public List<GameEntity> getAllMultiplayerGames() {
        return gameService.findAllMultiplayerGames();
    }

    @GetMapping("/platform/{platform}")
    public List<GameEntity> getGamesByPlatform(@PathVariable String platform) {
        return gameService.findByPlatform(platform);
    }

    @GetMapping("/type/{gameType}")
    public List<GameEntity> getGamesByGameType(@PathVariable String gameType) {
        return gameService.findByGameType(gameType);
    }

    @PostMapping
    public void addGame(){
        gamesGenerator.generateAndSaveGames(1000);
    }
}

