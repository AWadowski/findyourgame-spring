package org.example.service;

import org.example.GameApi;
import org.example.GameEntity;
import org.example.TakNieEnum;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author adam.wadowski
 * @since 29.05.2023
 */

@Component
public class GameConverter {

    public GameEntity toEntity(GameApi api){
        return GameEntity.builder()
                .gameName(api.getGameName())
                .gameType(api.getGameType())
                .multiplayer(api.getMultiplayer().getCode())
                .platform(api.getPlatform())
                .age(api.getAge())
                .wydawca(api.getWydawca())
                .dateOfOut(api.getDateOfOut())
                .transactions(api.getTransactions().getCode())
                .motyw(api.getMotyw())
                .build();
    }
}
