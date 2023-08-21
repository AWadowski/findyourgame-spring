package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.GameApi;
import org.example.GameEntity;
import org.example.GameRepository;
import org.example.service.GameConverter;
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
    private final GameConverter gameConverter;


    @PostMapping("/findByCriteria")
    public List<GameApi> findByCriteria(@RequestBody GameApi gameApi) {
        return gameService.findByCriteria(gameApi);
    }

    @GetMapping("/multiplayer")
    public List<GameApi> getAllMultiplayerGames() {
        return gameService.findAllMultiplayerGames();
    }

    @GetMapping("/platform/{platform}")
    public List<GameApi> getGamesByPlatform(@PathVariable String platform) {
        return gameService.findByPlatform(platform);
    }

    @GetMapping("/type/{gameType}")
    public List<GameApi> getGamesByGameType(@PathVariable String gameType) {
        return gameService.findByGameType(gameType);
    }

    @PostMapping
    public void addGame(){
        gamesGenerator.generateAndSaveGames();
    }
}

