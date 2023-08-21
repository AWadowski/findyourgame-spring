package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.GameApi;
import org.example.GameRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class GamesGenerator {
    private static final String[] GAME_TYPES = {
            "RPG",
            "FPS",
            "Adventure",
            "Strategy",
            "MOBA",
            "Sports",
            "Racing",
            "Simulation",
            "Horror",
            "Platformer",
            "Puzzle",
            "Fighting",
            "Battle Royale",
            "MMORPG",
            "Roguelike"
    };
    private static final String[] PLATFORMS = {
            "PC",
            "XBOX One",
            "XBOX Series X",
            "PlayStation 4",
            "PlayStation 5",
            "Nintendo",
            "Google",
            "Mobile",
            "VR",
            "Steam"
    };
    private static final String[] WYDAWCAS = {
            "Electronic Arts",
            "Ubisoft",
            "Activision Blizzard",
            "Nintendo",
            "Square Enix",
            "Capcom",
            "Bethesda Softworks",
            "Rockstar Games",
            "Konami",
            "SEGA",
            "Bandai Namco"
    };

    private static final String[] MOTYWS = {
            "Apocalypse",
            "Medieval",
            "Space",
            "Cyberpunk",
            "Pirates",
            "Western",
            "Egyptian",
            "Zombies",
            "Steampunk",
            "Magic"
    };
    private static final String[] GAMENAME = {
        "Chronicles of Eldoria",
                "Galactic Rebellion",
                "Mystic Forest: The Awakening",
                "Deserted Echoes",
                "Abyssal Realms",
                "Guardians of the Lost Empire",
                "Solaris Odyssey",
                "Temporal Paradox",
                "Whispers of the Ancients",
                "Nemesis: The Dark Enigma",
                "Celestial Frontiers",
                "Dragon's Descent",
                "Ecliptic Warriors",
                "Frozen Tides: The Icy Revenge",
                "Lunar Legacy",
                "Phoenix Resurgence",
                "Quantum Quest",
                "Riftwalkers",
                "Stellar Shadows",
                "The Last Valkyrie",
                "Uncharted Cosmos",
                "Vengeance of the Void",
                "Warlords of Wraithwood",
                "Xenon Xcape",
                "Yggdrasil Yonder",
                "Zephyr's Zenith",
                "Arcane Alchemy",
                "Blighted Bastions",
                "Crimson Catalyst",
                "Duskwoven Dynasty",
                "Ethereal Embers",
                "Frostfire Fables",
                "Gilded Guardians",
                "Harbinger's Hunt",
                "Infernal Illusions",
                "Jade Journeys",
                "Knight's Knavery",
                "Luminous Labyrinths",
                "Mystical Mirage",
                "Nether Nomads",
                "Oblivion Oath",
                "Primal Paragons",
                "Quasar Quandary",
                "Runebound Raiders",
                "Spectral Sovereigns",
                "Titan's Tempest",
                "Umbra Unleashed",
                "Vortex Vanguard",
                "Wanderers of the Wasteland",
                "Xanadu Xplorers",
                "Yonder Yarns",
                "Zenith's Ziggurat",
                "Astral Ascendancy",
                "Blade's Bounty",
                "Celestial Cipher",
                "Dawn's Desolation",
                "Elysium Echo",
                "Feral Frontiers",
                "Grim Grimoire",
                "Hallowed Horizons",
                "Inferno Isles",
                "Jewel of Janthar",
                "Kairos Kingdom",
                "Lunar Lament",
                "Maelstrom Mysteries",
                "Nexus Navigators",
                "Omen's Odyssey",
                "Pandemonium Paradox",
                "Quest for Quasar",
                "Riven Realms",
                "Stardust Saga",
                "Tempest Trials",
                "Utopia Unbound",
                "Valkyrie's Voyage",
                "Whispering Wilds",
                "Xenoblade Xanadu",
                "Ys: Yonder Seas",
                "Zealot's Zenith",
                "Arcadia's Ascent",
                "Bane's Blessing",
                "Celestia Chronicles",
                "Dreadnaught's Drift",
                "Eon's Edge",
                "Fates' Fusion",
                "Ghoul's Gambit",
                "Harmony's Haunt",
                "Icarus Incursion",
                "Journey of the Jaded",
                "Karma's Kingdom",
                "Legacy of the Lost",
                "Mystic's Mandate",
                "Nirvana Nexus",
                "Oracle's Omen",
                "Pilgrimage of the Pure",
                "Quest for Quantum",
                "Riddle of the Rune",
                "Starlight's Shadow",
                "Twilight's Triumph"
    };


    private static final Random RANDOM = new Random();
    private final FirebaseConfig firebaseConfig;
    private final GameRepository gameRepository;
    private final GameConverter gameConverter;
    private final FirebaseConverter firebaseConverter;


    public void generateAndSaveGames() {
        for (int i = 0; i < 97; i++) {
            GameApi gameApi = new GameApi(
                    null,
                    GAMENAME[i],
                    GAME_TYPES[RANDOM.nextInt(GAME_TYPES.length)],
                    "Nie",
                    PLATFORMS[RANDOM.nextInt(PLATFORMS.length)],
                    (long) RANDOM.nextInt(100),
                    WYDAWCAS[RANDOM.nextInt(WYDAWCAS.length)],
                    LocalDate.of(2000 + RANDOM.nextInt(22), Month.of(1 + RANDOM.nextInt(12)), 1 + RANDOM.nextInt(28)),
                    "Tak",
                    MOTYWS[RANDOM.nextInt(MOTYWS.length)],
                    generateRandomString(1000)
                    ,0L
            );
            gameApi = gameConverter.fromEntity(gameRepository.save(gameConverter.toEntity(gameApi)));
            firebaseConfig.addToFirebase(firebaseConverter.toEntity(gameApi));
        }
    }
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder result = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            result.append(characters.charAt(randomIndex));
        }

        return result.toString();
    }
}
