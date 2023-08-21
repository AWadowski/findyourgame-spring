package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@SuperBuilder
public class GameApi {
    private Long id;
    private String gameName;
    private String gameType;
    private String multiplayer;
    private String platform;
    private Long age;
    private String wydawca;
    private LocalDate dateOfOut;
    private String transactions;
    private String motyw;
    private String description;
    private Long ranking;
}
