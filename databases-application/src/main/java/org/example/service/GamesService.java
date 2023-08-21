package org.example.service;


import lombok.AllArgsConstructor;
import org.example.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GamesService {

    private final GameRepository gameRepository;
    private final GameConverter gameConverter;
    private final FirebaseConfig firebaseConfig;
    private final SearchingRepository searchingRepository;


    public List<GameApi> findByCriteria(GameApi criteria) {
        List<GameApi> results = findByCriteriaInternal(criteria);
        findFromFirebase(criteria);
        if (results.size()<3) {
            if (criteria.getMotyw() != null) {
                criteria.setMotyw(null);
            } else if (criteria.getTransactions() != null) {
                criteria.setTransactions(null);
            } else if (criteria.getDateOfOut() != null) {
                criteria.setDateOfOut(null);
            } else if (criteria.getWydawca() != null) {
                criteria.setWydawca(null);
            } else if (criteria.getAge() != null) {
                criteria.setAge(null);
            } else if (criteria.getPlatform() != null) {
                criteria.setPlatform(null);
            } else if (criteria.getMultiplayer() != null) {
                criteria.setMultiplayer(null);
            } else if (criteria.getGameType() != null) {
                criteria.setGameType(null);
            }
            return findByCriteria(criteria);
        }
        results.sort((o1, o2) -> o2.getRanking().compareTo(o1.getRanking()));
        return results.subList(0,3);
    }

    private List<GameApi> findByCriteriaInternal(GameApi criteria) {
        SearchingEntity searchingEntity = new SearchingEntity();
        searchingEntity.setSearchingType("oracle");
        long startTime = System.nanoTime();
        List<GameEntity> results = new ArrayList<>();
        gameRepository.findAll().forEach(game -> {
            if ((criteria.getGameType() == null || game.getGameType().equals(criteria.getGameType())) &&
                    (criteria.getMultiplayer() == null || game.getMultiplayer().equals(criteria.getMultiplayer())) &&
                    (criteria.getPlatform() == null || game.getPlatform().equals(criteria.getPlatform())) &&
                    (criteria.getAge() == null || game.getAge() <= criteria.getAge()) &&
                    (criteria.getWydawca() == null || game.getWydawca().equals(criteria.getWydawca())) &&
                    (criteria.getDateOfOut() == null || game.getDateOfOut().equals(criteria.getDateOfOut())) &&
                    (criteria.getTransactions() == null || game.getTransactions().equals(criteria.getTransactions())) &&
                    (criteria.getMotyw() == null || game.getMotyw().equals(criteria.getMotyw()))) {
                results.add(game);
            }
        });
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        searchingEntity.setSearchingTime(String.valueOf(duration/1_000));
        searchingRepository.save(searchingEntity);
        return gameConverter.fromEntityList(results);
    }

    @Async
    protected void findFromFirebase(GameApi criteria){
        SearchingEntity searchingEntity = new SearchingEntity();
        searchingEntity.setSearchingType("firebase");
        long startTime = System.nanoTime();
        List<GameApi> results = new ArrayList<>();
        firebaseConfig.getFromFireBase().forEach(game -> {
            if ((criteria.getGameType() == null || game.getGameType().equals(criteria.getGameType())) &&
                    (criteria.getMultiplayer() == null || game.getMultiplayer().equals(criteria.getMultiplayer())) &&
                    (criteria.getPlatform() == null || game.getPlatform().equals(criteria.getPlatform())) &&
                    (criteria.getAge() == null || game.getAge() <= criteria.getAge()) &&
                    (criteria.getWydawca() == null || game.getWydawca().equals(criteria.getWydawca())) &&
                    (criteria.getDateOfOut() == null || game.getDateOfOut().equals(criteria.getDateOfOut())) &&
                    (criteria.getTransactions() == null || game.getTransactions().equals(criteria.getTransactions())) &&
                    (criteria.getMotyw() == null || game.getMotyw().equals(criteria.getMotyw()))) {
                results.add(game);
            }
        });
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        searchingEntity.setSearchingTime(String.valueOf(duration/1_000));
        searchingRepository.save(searchingEntity);
    }

    public void changeRanking(Long id, Long ranking){
        GameEntity gameEntity = gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono gry o podanym id"));
        gameEntity.setRanking(ranking);
        gameRepository.save(gameEntity);
    }
}
