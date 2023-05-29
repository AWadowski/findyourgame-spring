package org.example;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<GameEntity, Long> {

    @Query("SELECT g FROM GameEntity g WHERE g.multiplayer = 'true'")
    List<GameEntity> findAllMultiplayerGames();

    @Query("SELECT g FROM GameEntity g WHERE g.platform = :platform")
    List<GameEntity> findByPlatform(String platform);

    @Query(value = "SELECT * FROM games_list WHERE game_type = :gameType", nativeQuery = true)
    List<GameEntity> findByGameType(String gameType);
}
