package org.example.service;

import com.google.firebase.database.DatabaseReference;
import lombok.RequiredArgsConstructor;
import org.example.GameApi;
import org.example.GameRepository;
import org.example.TakNieEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class GamesGenerator {
    private static final String[] GAME_TYPES = {"RPG", "FPS", "Adventure", "Strategy", "Sports"};
    private static final String[] PLATFORMS = {"PC", "Xbox", "PlayStation", "Nintendo", "Mobile"};
    private static final String[] WYDAWCAS = {"Ubisoft", "EA", "CD Projekt Red", "Bethesda", "Blizzard"};
    private static final String[] MOTYWS = {"Fantasy", "Sci-fi", "Horror", "Action", "Puzzle"};
    private static final String[] GAMENAME = {"Wiedźmin 3: Dziki Gon", "Cyberpunk 2077", "The Elder Scrolls V: Skyrim", "Grand Theft Auto V", "The Witcher 3: Wild Hunt", "The Legend of Zelda: Breath of the Wild", "The Witcher 3: Wild Hunt", "The Legend of Zelda: Breath of the Wild", "The Witcher 3: Wild Hunt", "The Legend of Zelda: Breath of the Wild", "The Witcher 3: Wild Hunt", "The Legend of Zelda: Breath of the Wild", "The Witcher 3: Wild Hunt", "The Legend of Zelda: Breath of the Wild", "The Witcher 3: Wild Hunt", "The Legend of Zelda: Breath of the Wild", "The Witcher 3: Wild Hunt", "The Legend of Zelda: Breath of the Wild", "The Witcher 3: Wild Hunt", "The Legend of Zelda: Breath of the Wild", "The Witcher 3: Wild Hunt", "The Legend of Zelda: Breath of the Wild", "The Witcher 3: Wild Hunt", "The Legend of Zelda: Breath of the Wild"};


    private static final Random RANDOM = new Random();
    private final DatabaseReference ref;
    private final GameRepository gameRepository;
    private final GameConverter gameConverter;


    public void generateAndSaveGames(int count) {
        for (int i = 0; i < count; i++) {
            GameApi gameApi = new GameApi(
                    GAMENAME[RANDOM.nextInt(GAMENAME.length)],
                    GAME_TYPES[RANDOM.nextInt(GAME_TYPES.length)],
                    TakNieEnum.NIE,
                    PLATFORMS[RANDOM.nextInt(PLATFORMS.length)],
                    (long) RANDOM.nextInt(100),
                    WYDAWCAS[RANDOM.nextInt(WYDAWCAS.length)],
                    LocalDateTime.of(2000 + RANDOM.nextInt(22), Month.of(1 + RANDOM.nextInt(12)), 1 + RANDOM.nextInt(28), 0, 0),
                    TakNieEnum.TAK,
                    MOTYWS[RANDOM.nextInt(MOTYWS.length)]
            );
            gameRepository.save(gameConverter.toEntity(gameApi));
            ref.push().setValueAsync(gameApi);
        }
    }
}
