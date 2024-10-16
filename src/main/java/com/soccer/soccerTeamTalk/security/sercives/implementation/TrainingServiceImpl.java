package com.soccer.soccerTeamTalk.security.sercives.implementation;

import com.soccer.soccerTeamTalk.models.Training;
import com.soccer.soccerTeamTalk.repository.TrainingRepository;
import com.soccer.soccerTeamTalk.security.sercives.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    TrainingRepository trainingRepository;

    @Override
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> getAllTraining() {
        List<Training> trainings = trainingRepository.findAll();
        if(!trainings.isEmpty()){
            return trainings;
        }
        throw new RuntimeException("Trainings not found");
    }

    @Override
    public Optional<Training> getTrainingById(String id) {
        Optional<Training> training = trainingRepository.findById(id);
        if(training.isPresent()){
            return training;
        }
        throw new RuntimeException("Training not found");
    }

    @Override
    public void deleteTraining(String id) {
        if(trainingRepository.existsById(id)){
            trainingRepository.deleteById(id);
        }
        throw new RuntimeException("Training cannot be remove");
    }

    @Override
    public Training updateTrainingData(int id) {
        return null;
    }
}
