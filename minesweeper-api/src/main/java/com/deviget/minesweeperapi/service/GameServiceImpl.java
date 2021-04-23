package com.deviget.minesweeperapi.service;


import com.deviget.minesweeperapi.model.Cell;
import com.deviget.minesweeperapi.model.Game;
import com.deviget.minesweeperapi.model.GameStatus;
import com.deviget.minesweeperapi.model.requestDto.GameRequest;
import com.deviget.minesweeperapi.model.requestDto.MoveRequest;
import com.deviget.minesweeperapi.model.responseDto.CellResponse;
import com.deviget.minesweeperapi.model.responseDto.MoveResponse;
 import com.deviget.minesweeperapi.repository.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class GameServiceImpl implements GameService{
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private CellService cellService;

    @Autowired
    private UserService userService;

    @Override
    public Game newGame(GameRequest gameRequest) {
        if(!validate(gameRequest)){
            return null;
        }
        LocalDateTime date = LocalDateTime.now();
        ModelMapper modelMapper = new ModelMapper();
        Game game = modelMapper.map(gameRequest,Game.class);
        game.setGameStarDate(date);
        game.setLastMoveDate(date);
        game.setLastRestartDate(date);
        game.setGameTime(0L);
        game.setUser(userService.getUserById(gameRequest.getUserId()));
        game.setGameStatus(GameStatus.INPROCESS);
        Game gameCreated =  gameRepository.save(game);
        cellService.generateGameField(game);
        return gameCreated;
    }

    @Override
    public Game pauseOrResumeGame(Game game) {
        game.setPause(!game.isPause());
        if (game.isPause()){
            game.setGameTime(game.getGameTime() + ChronoUnit.SECONDS.between(game.getLastRestartDate(),LocalDateTime.now()));
        }else{
            game.setLastRestartDate(LocalDateTime.now());
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

    @Override
    public MoveResponse move(Game game, MoveRequest move) {
        List<Cell> cells = cellService.findAllByGameId(game.getId());
        MoveResponse response = new MoveResponse();
        Cell currentCell = cells.stream().filter(c -> c.getXPosition() == move.getXPosition()&&
                        c.getYPosition() == move.getYPosition())
                .findFirst().orElse(null);
        CellResponse[][] gameField = new CellResponse[game.getRows()][game.getColumns()];
        if(move.isFlag()){
            currentCell.setFlag(true);
            for(Cell cell : cells){
                CellResponse moveCell = new CellResponse();
                moveCell.setFlag(true);
                moveCell.setXPosition(move.getXPosition());
                moveCell.setYPosition(move.getYPosition());
                gameField[cell.getXPosition()][cell.getYPosition()] = moveCell;
            }
            cellService.saveMove(currentCell);
            response.setGameField(gameField);
            response.setGameId(game.getId());
            response.setGameStatus(GameStatus.INPROCESS.toString());
            response.setGameTime(game.getGameTime() + ChronoUnit.SECONDS.between(game.getLastRestartDate(),LocalDateTime.now()));
            return response;
        }
        if(currentCell.isMine()){
            game.setGameStatus(GameStatus.DEFEAT);
            game.setGameTime(game.getGameTime() + ChronoUnit.SECONDS.between(game.getLastRestartDate(),LocalDateTime.now()));
            for(Cell cell : cells){
                CellResponse moveCell = new CellResponse();
                moveCell.setFlag(true);
                if(cell.isMine()){
                    moveCell.setValue(cell.getValue());
                    moveCell.setMine(true);
                }
                moveCell.setXPosition(move.getXPosition());
                moveCell.setYPosition(move.getYPosition());
                gameField[cell.getXPosition()][cell.getYPosition()] = moveCell;
            }
        }




        return null;
    }

    private boolean validate(GameRequest game){
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
