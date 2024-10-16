package com.soccer.soccerTeamTalk.controllers;

import com.soccer.soccerTeamTalk.models.Game;
import com.soccer.soccerTeamTalk.security.sercives.implementation.GameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    GameServiceImpl gameServiceImpl;

    @PostMapping("/createGame")
    public ResponseEntity<Game> createGame(@Valid @RequestBody Game game){
        Game createdGame = gameServiceImpl.createGame(game);
       return ResponseEntity.ok(createdGame);
    }

    @GetMapping("/getAllGame")
    public ResponseEntity<List<Game>> getAllGame(){
        List<Game> games = gameServiceImpl.getAllGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/getGameById")
    public ResponseEntity<Optional<Game>> getGameById(@PathVariable("id") String id){
        Optional<Game> game = gameServiceImpl.getGameById(id);
        return ResponseEntity.ok(game);
    }

    @DeleteMapping("/deleteGame")
    public ResponseEntity<?> deleteGame(@PathVariable("id") String id){
        gameServiceImpl.deleteGame(id);
        return ResponseEntity.ok("training is successful deleted");
    }

}
