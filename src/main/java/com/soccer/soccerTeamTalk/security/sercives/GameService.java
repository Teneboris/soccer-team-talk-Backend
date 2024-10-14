package com.soccer.soccerTeamTalk.security.sercives;

import com.soccer.soccerTeamTalk.models.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    Game createGame(Game game);
     List<Game> getAllGames();

     Optional<Game> getGameById(String game);

     void deleteGame(String id);

     Game updateGameData(int id);
}
