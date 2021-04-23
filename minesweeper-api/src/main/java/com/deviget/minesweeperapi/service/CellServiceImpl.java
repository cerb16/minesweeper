package com.deviget.minesweeperapi.service;

import com.deviget.minesweeperapi.model.Cell;
import com.deviget.minesweeperapi.model.Game;
import com.deviget.minesweeperapi.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class CellServiceImpl implements CellService {

    @Autowired
    private CellRepository cellRepository;

    @Override
    public List<Cell> generateGameField(Game game) {
        List<Cell> gameField = new ArrayList<>();
        int gameCells = game.getRows() * game.getColumns();
        Set<Integer> minesPosition = new LinkedHashSet<>();
        Random rdm = new Random();
        while (minesPosition.size() < game.getMines()){
            minesPosition.add(rdm.nextInt(gameCells)+1);
        }
        for(int i = 1; i <= game.getRows(); i++){
            for(int j = 1; j<=game.getColumns();j++){
                Cell cell = new Cell();
                cell.setXPosition(j);
                cell.setYPosition(i);
                cell.setGame(game);
                if(minesPosition.contains(((i-1)* game.getColumns())+j)){
                    cell.setMine(true);
                    cell.setValue(-1);
                }
                gameField.add(cell);
            }
        }
        gameField = cellRepository.saveAll(gameField);
        for(Cell cell: gameField){

        }

        return cellRepository.saveAll(gameField);
    }

    @Override
    public List<Cell> findAllByGameId(Long id) {
        return cellRepository.findAllByGameId(id);
    }

    @Override
    public long saveMove(Cell cell) {
       return cellRepository.save(cell).getId();
    }
}
