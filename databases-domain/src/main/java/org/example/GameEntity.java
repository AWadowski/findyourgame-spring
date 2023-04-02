package org.example;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "c##adam.games_list")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "c##adam.games_list_id_seq")

    private Long id;
    private String gameType;
    private String gameName;
    private boolean multiplayer;
    private String platform;
    private Long age;
    private String wydawca;
    private LocalDateTime dateOfOut;
    private boolean transactions;
    private String motyw;
}
