package com.deviget.minesweeperapi.controller;


import com.deviget.minesweeperapi.model.Game;
import com.deviget.minesweeperapi.model.GameStatus;
import com.deviget.minesweeperapi.model.requestDto.GameRequest;
import com.deviget.minesweeperapi.model.requestDto.MoveRequest;
import com.deviget.minesweeperapi.service.GameService;
import com.deviget.minesweeperapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/${spring.data.rest.base-path}/api/game")
public class GameController {
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> newGame(@Valid @RequestBody GameRequest game) {
        if(userService.getUserById(game.getUserId())==null){
            return new ResponseEntity<>("User not exist, please use a valid one", HttpStatus.BAD_REQUEST);
        }
        Game gameCreated = gameService.newGame(game);
        if(gameCreated != null){
            return new ResponseEntity<>(gameCreated, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("You have an error setting the game configs", HttpStatus.BAD_REQUEST);


    }

    @PutMapping("/{gameId}/pause-resume")
    public ResponseEntity<?> pauseOrResumeGame(@PathVariable Long gameId) {
        Game game = gameService.getGameById(gameId);
        if(game != null && game.getGameStatus() == GameStatus.INPROCESS){
            return new ResponseEntity<>(gameService.pauseOrResumeGame(game), HttpStatus.OK);
        }
        return new ResponseEntity<>("This game can't be pause-resume", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{gameId}/move")
    public ResponseEntity<?> move (@PathVariable Long gameId, @RequestBody MoveRequest move) {
        Game game = gameService.getGameById(gameId);
        if(move.getId()== null && (move.getYPosition() == null && move.getYPosition() == null)){
            return new ResponseEntity<>("You must indicate which cell you are playing", HttpStatus.BAD_REQUEST);
        }
        if(game != null && game.getGameStatus() == GameStatus.INPROCESS && !game.isPause()){
            return new ResponseEntity<>(gameService.move(game,move), HttpStatus.OK);
        }
        return new ResponseEntity<>("This game is paused, it don't exist or it is over", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{gameId}/gamefield")
    public  ResponseEntity<?> getGameField(@PathVariable Long gameId){
        Game game = gameService.getGameById(gameId);
        if(game != null){
            return new ResponseEntity<>(gameService.getGameField(game), HttpStatus.OK);
        }
        return new ResponseEntity<>("This game not exist ", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<?> getGameById( @PathVariable Long gameId) {
        return ResponseEntity.ok(gameService.getGameById(gameId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByGameName( @PathVariable long userId) {
        return ResponseEntity.ok(gameService.getAllGamesByUser(userId));
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<?> deleteGame( @PathVariable Long gameId) {
        return ResponseEntity.ok(gameService.deleteGame(gameId));
    }
}
