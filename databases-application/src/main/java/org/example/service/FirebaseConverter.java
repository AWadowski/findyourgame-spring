package org.example.service;


import org.example.FireBaseApi;
import org.example.GameApi;
import org.springframework.stereotype.Component;

@Component
public class FirebaseConverter {

        public FireBaseApi toEntity(GameApi api){
            return FireBaseApi.builder()
                    .id(api.getId())
                    .gameName(api.getGameName())
                    .gameType(api.getGameType())
                    .multiplayer(api.getMultiplayer())
                    .platform(api.getPlatform())
                    .age(api.getAge())
                    .wydawca(api.getWydawca())
                    .dateOfOut(api.getDateOfOut().toString())
                    .transactions(api.getTransactions())
                    .motyw(api.getMotyw())
                    .build();
        }
}
