package com.soccer.soccerTeamTalk.repository;

import com.soccer.soccerTeamTalk.models.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
    boolean existsById(String id);
}
