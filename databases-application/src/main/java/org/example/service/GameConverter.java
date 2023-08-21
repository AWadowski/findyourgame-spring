package org.example.service;

import org.example.GameApi;
import org.example.GameEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author adam.wadowski
 * @since 29.05.2023
 */

@Component
public class GameConverter {

    public GameEntity toEntity(GameApi api){
        return GameEntity.builder()
                .id(api.getId())
                .gameName(api.getGameName())
                .gameType(api.getGameType())
                .multiplayer(api.getMultiplayer())
                .platform(api.getPlatform())
                .age(api.getAge())
                .wydawca(api.getWydawca())
                .dateOfOut(api.getDateOfOut())
                .transactions(api.getTransactions())
                .motyw(api.getMotyw())
                .description(api.getDescription())
                .build();
    }

    public GameApi fromEntity(GameEntity entity){
        return GameApi.builder()
                .id(entity.getId())
                .gameName(entity.getGameName())
                .gameType(entity.getGameType())
                .multiplayer(entity.getMultiplayer())
                .platform(entity.getPlatform())
                .age(entity.getAge())
                .wydawca(entity.getWydawca())
                .dateOfOut(entity.getDateOfOut())
                .transactions(entity.getTransactions())
                .motyw(entity.getMotyw())
                .description(entity.getDescription())
                .build();
    }

    public List<GameApi> fromEntityList(List<GameEntity> gameEntities){
        return gameEntities.stream()
                .map(this::fromEntity)
                .collect(Collectors.toList());
    }
}
