package com.soccer.soccerTeamTalk.dto;

import com.soccer.soccerTeamTalk.models.Game;
import com.soccer.soccerTeamTalk.models.Training;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrainingGameDTO {

    private String id;
    private Training training;
    private Game game;

}
