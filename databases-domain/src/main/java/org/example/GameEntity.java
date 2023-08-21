package org.example;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "GAMES_LIST")
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "games_list_id_seq")
    @SequenceGenerator(name = "games_list_id_seq", sequenceName = "games_list_id_seq", allocationSize = 1)
    private Long id;
    private String gameType;
    private String gameName;
    private String multiplayer;
    private String platform;
    private Long age;
    private String wydawca;
    private LocalDate dateOfOut;
    private String transactions;
    private String motyw;
    private String description;
}
