package com.deviget.minesweeperapi.service;

import com.deviget.minesweeperapi.model.Cell;
import com.deviget.minesweeperapi.model.Game;

import java.util.List;

public interface CellService {
    List<Cell> generateGameField(Game game);
}
