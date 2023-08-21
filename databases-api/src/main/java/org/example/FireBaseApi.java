package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@SuperBuilder
public class FireBaseApi {
    private Long id;
    private String gameName;
    private String gameType;
    private String multiplayer;
    private String platform;
    private Long age;
    private String wydawca;
    private String dateOfOut;
    private String transactions;
    private String motyw;
}
