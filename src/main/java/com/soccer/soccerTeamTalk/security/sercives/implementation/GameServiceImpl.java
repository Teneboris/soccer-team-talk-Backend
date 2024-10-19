package com.soccer.soccerTeamTalk.security.sercives.implementation;

import com.soccer.soccerTeamTalk.models.Game;
import com.soccer.soccerTeamTalk.repository.GameRepository;
import com.soccer.soccerTeamTalk.security.sercives.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> games = gameRepository.findAll();
        return games;
    }

    @Override
    public Optional<Game> getGameById(String id) {
        return gameRepository.findById(id);
    }

    @Override
    public void deleteGame(String id) {

        if(gameRepository.existsById(id)){

            gameRepository.deleteById(id);
        }
    }

    @Override
    public Game updateGameData(int id) {
        return null;
    }

}
