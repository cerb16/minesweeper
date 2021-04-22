package com.deviget.minesweeperapi.service;


import com.deviget.minesweeperapi.model.Game;
import com.deviget.minesweeperapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;


public class GameServiceImpl implements GameService{
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CellService cellService;

    @Override
    public Game newGame(Game game) {
        if(validate(game)){
            return null;
        }
        game.setGameStarDate(LocalDateTime.now());
        Game gameCreated =  gameRepository.save(game);
        cellService.generateGameField(game);
        return gameCreated;
    }

    @Override
    public Game pauseOrResumeGame(Game game) {
        game.setPause(!game.isPause());
        if (game.isPause()){
            game.setGameTime(game.getGameTime() + ChronoUnit.SECONDS.between(game.getLastRestartDate(),LocalDateTime.now()));
        }
        return gameRepository.save(game);

    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game getGameById(Long gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }

    @Override
    public List<Game> getAllGamesByUser(long userId) {
        return gameRepository.findAllByUserId(userId);
    }

    @Override
    public String deleteGame(Long gameId) {
        if(getGameById(gameId) != null){
            gameRepository.deleteById(gameId);
            return "Game deleted successfully";
        }else{
            return "Game does not exist";
        }

    }

    private boolean validate(Game game){
        if(game.getRows() < 2){
            return false;
        }
        if(game.getColumns() < 2){
            return false;
        }
        if(game.getMines() < 1){
            return false;
        }
        if((game.getRows()*game.getColumns()) <= game.getMines()){
            return false;
        }

        return true;
    }

}
