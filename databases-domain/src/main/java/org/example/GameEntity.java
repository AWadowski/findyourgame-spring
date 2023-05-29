package org.example;


import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "c##adam.games_list")
@SuperBuilder
@RequiredArgsConstructor
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
    private LocalDateTime dateOfOut;
    private String transactions;
    private String motyw;
}
