package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GameApi {
    private String gameName;
    private String gameType;
    private TakNieEnum multiplayer;
    private String platform;
    private Long age;
    private String wydawca;
    private LocalDateTime dateOfOut;
    private TakNieEnum transactions;
    private String motyw;

}
