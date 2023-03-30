package org.example;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class GameApi {
    private String gameType;
    private boolean multiplayer;
    private String platform;
    private Long age;
    private String wydawca;
    private LocalDateTime dateOfOut;
    private boolean transactions;
    private String motyw;

}
