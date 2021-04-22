package com.deviget.minesweeperapi.service;

import com.deviget.minesweeperapi.model.Cell;
import com.deviget.minesweeperapi.model.Game;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CellServiceImpl implements CellService {
    @Override
    public List<Cell> generateGameField(Game game) {
        List<Cell> gameField = new ArrayList<>();
        int gameCells = game.getRows() * game.getColumns();
        Set<Integer> minesPosition = new LinkedHashSet<>();
        Random rdm = new Random();
        while (minesPosition.size() <= game.getMines()){
            minesPosition.add(rdm.nextInt(gameCells)+1);
        }
        for(int i = 1; i <= game.getRows(); i++){
            for(int j = 1; j<=game.getColumns();j++){
                Cell cell = new Cell();
                cell.setXPosition(j);
                cell.setYPosition(i);
                cell.setGame(game);
                if(minesPosition.contains(i*j)){
                    cell.setMine(true);
                    cell.setValue(-1);
                }
                gameField.add(cell);
            }
        }

        return  gameField;
    }
}
