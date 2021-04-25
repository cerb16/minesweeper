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
        cellService.generateGameField(gameCreated);
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
        ModelMapper modelMapper = new ModelMapper();
        CellResponse[][] gameField = new CellResponse[game.getRows()][game.getColumns()];
        List<Cell> cells = cellService.findAllByGameId(game.getId());
        MoveResponse response = new MoveResponse();
        Cell currentCell;
        if(move.getId()!=null && move.getId() != 0){
            currentCell = cells.stream().filter(c -> c.getId()==move.getId()).findFirst().orElse(null);
        }else{
           currentCell = cells.stream().filter(c -> c.getXPosition() == move.getXPosition()
                    && c.getYPosition() == move.getYPosition()).findFirst().orElse(null);
        }


        LocalDateTime date = LocalDateTime.now();
        long gameTime = game.getGameTime() + ChronoUnit.SECONDS.between(game.getLastRestartDate(),date);

        if(currentCell==null)
            return null;


        if(move.getFlag() !=null && (move.getFlag()!= currentCell.isFlag() || currentCell.isFlag() )){
            currentCell.setFlag(!currentCell.isFlag());
            cellService.saveMove(currentCell);
            response.setGameStatus(GameStatus.INPROCESS.toString());
            for(Cell cell : cells){
                CellResponse moveCell = modelMapper.map(cell,CellResponse.class);
                if(!cell.isRevealed()){
                    moveCell.setValue(null);
                    moveCell.setMine(null);
                }
                gameField[cell.getYPosition()-1][cell.getXPosition()-1] = moveCell;
            }
        }else
        if(currentCell.isMine()){
            game.setGameStatus(GameStatus.DEFEAT);
            game.setGameTime(gameTime);
            response.setGameStatus(game.getGameStatus().toString());
            for(Cell cell : cells){
                CellResponse moveCell = modelMapper.map(cell,CellResponse.class);
                moveCell.setRevealed(true);
                cell.setRevealed(true);
                cell.setFlag(false);
                moveCell.setFlag(false);
                gameField[cell.getYPosition()-1][cell.getXPosition()-1] = moveCell;
            }
            cellService.saveAll(cells);
        }else
        if(currentCell.getValue() > 0){
            currentCell.setRevealed(true);
            cellService.saveMove(currentCell);
            for(Cell cell : cells){
                CellResponse moveCell = modelMapper.map(cell,CellResponse.class);
                if(!cell.isRevealed()){
                    moveCell.setValue(null);
                    moveCell.setMine(null);
                }
                gameField[cell.getYPosition()-1][cell.getXPosition()-1] = moveCell;
            }
            long moves = cells.stream().filter(c->c.isRevealed()).count();
            if(moves+game.getMines()== cells.size()){
                response.setGameStatus(GameStatus.VICTORY.toString());
                game.setGameStatus(GameStatus.VICTORY);
            }else{
                response.setGameStatus(GameStatus.INPROCESS.toString());
            }

        }else
        if(currentCell.getValue() == 0){
            List<Cell> gameFieldUpdated = cellService.saveAll(this.revealBlock(cells,currentCell,game));
            for(Cell cell : gameFieldUpdated){
                CellResponse moveCell = modelMapper.map(cell,CellResponse.class);
                if(!cell.isRevealed()){
                    moveCell.setValue(null);
                    moveCell.setMine(null);
                }
                gameField[cell.getYPosition()-1][cell.getXPosition()-1] = moveCell;
            }
            long moves = cells.stream().filter(c->c.isRevealed()).count();
            if(moves+game.getMines()== cells.size()){
                response.setGameStatus(GameStatus.VICTORY.toString());
                game.setGameStatus(GameStatus.VICTORY);
            }else{
                response.setGameStatus(GameStatus.INPROCESS.toString());
            }
        }


        game.setLastMoveDate(date);
        gameRepository.save(game);
        response.setGameField(gameField);
        response.setGameId(game.getId());
        response.setGameTime(gameTime);

        return response;
    }

    @Override
    public MoveResponse getGameField(Game game) {
        ModelMapper modelMapper = new ModelMapper();
        CellResponse[][] gameField = new CellResponse[game.getRows()][game.getColumns()];
        List<Cell> cells = cellService.findAllByGameId(game.getId());
        MoveResponse response = new MoveResponse();
        LocalDateTime date = LocalDateTime.now();
        long gameTime = game.getGameTime() + ChronoUnit.SECONDS.between(game.getLastRestartDate(),date);
        for(Cell cell : cells){
            CellResponse moveCell = modelMapper.map(cell,CellResponse.class);
            if(!cell.isRevealed()){
                moveCell.setValue(null);
                moveCell.setMine(null);
            }
            gameField[cell.getYPosition()-1][cell.getXPosition()-1] = moveCell;
        }
        String[] gameFieldView = new String[game.getRows()];

        for(int i = 0; i < game.getRows();i++)
        {
            StringBuilder entry = new StringBuilder();
            for(int j = 0; j < game.getColumns();j++)
            {
                if(gameField[i][j].getFlag()){
                    entry.append("F ");
                }else if(!gameField[i][j].getRevealed()){
                    entry.append("H ");
                }else{
                    switch (gameField[i][j].getValue()){
                        case -1:
                            entry.append("M ");
                            break;
                        case 0:
                            entry.append("0 ");
                            break;
                        default:entry.append(gameField[i][j].getValue()+" ");
                    }
                }

            }
            gameFieldView[i] = entry.toString();
        }
        response.setGameStatus(game.getGameStatus().toString());
        response.setGameFieldView(gameFieldView);
        game.setLastMoveDate(date);
        response.setGameField(gameField);
        response.setGameId(game.getId());
        response.setGameTime(gameTime);

        return response;
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

    private List<Cell> revealBlock(List<Cell> gameField, Cell cell,Game game){
        cell.setRevealed(true);
        int x = 0;
        int y = 0;
        int posAd = 0;
        for (int i = -1; i <=1;i++){
            for (int j = -1; j <= 1; j++){
                x = cell.getXPosition() + j;
                y = cell.getYPosition() + i;
                if(x > 0 && x <= game.getColumns() && y > 0 && y <= game.getRows()){
                    posAd = ((y-1)*game.getColumns())+x;
                    Cell cellN = gameField.get(posAd-1);
                    if(cellN.getValue() == 0 && !cellN.isRevealed()){
                        revealBlock(gameField,cellN,game);
                    }
                    if(cellN.getValue()>0){
                        cellN.setRevealed(true);
                    }
                }
            }
        }
        return gameField;
    }

}
