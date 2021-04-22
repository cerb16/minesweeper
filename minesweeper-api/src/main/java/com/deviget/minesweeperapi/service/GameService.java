package com.deviget.minesweeperapi.service;

import com.deviget.minesweeperapi.model.Game;

import java.util.List;

public interface GameService {
    Game newGame(Game game);

    Game pauseOrResumeGame(Game game);

    List<Game> getAllGames();

    Game getGameById(Long gameId);

    List<Game> getAllGamesByUser(long userId);

    String deleteGame(Long gameId);

}
