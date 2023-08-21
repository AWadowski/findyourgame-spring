package org.example.controller;

import lombok.AllArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
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


    @PostMapping("/findByCriteria")
    public List<GameApi> findByCriteria(@RequestBody GameApi gameApi) {
        return gameService.findByCriteria(gameApi);
    }

    @PostMapping
    public void addGame(){
        gamesGenerator.generateAndSaveGames();
    }

    @PutMapping("/changeRanking/{id}/{ranking}")
    public void changeRanking(@PathVariable Long id, @PathVariable Long ranking){
        gameService.changeRanking(id,ranking);
    }
}

