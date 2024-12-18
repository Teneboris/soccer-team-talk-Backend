package com.soccer.soccerTeamTalk.repository;

import com.soccer.soccerTeamTalk.models.Training;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends MongoRepository<Training, String> {

    boolean existsById(String id);
}

