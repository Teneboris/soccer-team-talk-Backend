package com.soccer.soccerTeamTalk.controllers;

import com.soccer.soccerTeamTalk.models.Training;
import com.soccer.soccerTeamTalk.security.sercives.TrainingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/soccer/training")
public class TrainingController {

    @Autowired
    TrainingServiceImpl trainingService;

    @PostMapping("/createTraining")
    public ResponseEntity<Training> createTraining(@Valid @RequestBody Training training){
        System.out.println("check training");
        Training createdTraining = trainingService.createTraining(training);
        return ResponseEntity.ok(createdTraining);
    }

    @GetMapping("/getAllTraining")
    public ResponseEntity<List<Training>> getAllTraining(){
        List<Training> trainings = trainingService.getAllTraining();
        return ResponseEntity.ok(trainings);
    }

    @GetMapping("/getTrainingById/{id}")
    public ResponseEntity<Optional<Training>> getTrainingById(@PathVariable("id") String id){
        Optional<Training> training = trainingService.getTrainingById(id);
        return ResponseEntity.ok(training);
    }

    @DeleteMapping("/deleteTraining/{id}")
    public ResponseEntity<?> deleteTraining(@PathVariable("id") String id){
        trainingService.deleteTraining(id);
        return ResponseEntity.ok("training is successful deleted");
    }


}
