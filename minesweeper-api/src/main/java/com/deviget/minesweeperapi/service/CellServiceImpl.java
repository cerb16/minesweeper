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
                else {
                    cell.setMine(false);
                    cell.setValue(0);
                }
                gameField.add(cell);
            }
        }

        for(int minePosition: minesPosition){
            Cell c = gameField.get(minePosition-1);
            int x = 0;
            int y = 0;
            int posAd = 0;
            for (int i = -1; i <=1;i++){
                for (int j = -1; j <= 1; j++){
                    x = c.getXPosition() + j;
                    y = c.getYPosition() + i;
                    if(x > 0 && x <= game.getColumns() && y > 0 && y <= game.getRows()){
                        posAd = ((y-1)*game.getColumns())+x;
                        Cell cellN = gameField.get(posAd-1);
                        gameField.get(posAd-1).setValue(cellN.isMine()?cellN.getValue():cellN.getValue()+1);
                    }
                }
            }

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
    @Override
    public List<Cell> saveAll(List<Cell> cells){
        return cellRepository.saveAll(cells);
    }
}
