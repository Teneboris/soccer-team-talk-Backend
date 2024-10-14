package com.soccer.soccerTeamTalk.security.sercives;

import com.soccer.soccerTeamTalk.models.Training;

import java.util.List;
import java.util.Optional;

public interface TrainingService {



    Training createTraining(Training training);
     List<Training> getAllTraining();

     Optional<Training> getTrainingById(String id);

     void deleteTraining(String id);

     Training updateTrainingData(int id);
}
