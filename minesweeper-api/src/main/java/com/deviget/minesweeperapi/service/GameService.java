package com.deviget.minesweeperapi.service;

import com.deviget.minesweeperapi.model.Game;
import com.deviget.minesweeperapi.model.requestDto.GameRequest;
import com.deviget.minesweeperapi.model.requestDto.MoveRequest;
import com.deviget.minesweeperapi.model.responseDto.MoveResponse;

import java.util.List;

public interface GameService {
    Game newGame(GameRequest game);

    Game pauseOrResumeGame(Game game);

    List<Game> getAllGames();

    Game getGameById(Long gameId);

    List<Game> getAllGamesByUser(long userId);

    String deleteGame(Long gameId);

    MoveResponse move(Game game, MoveRequest move);

    MoveResponse getGameField(Game game);
}
