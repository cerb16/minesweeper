package com.deviget.minesweeperapi.repository;

import com.deviget.minesweeperapi.model.Cell;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CellRepository extends JpaRepository<Cell,Long> {
    List<Cell> findAllByGameId(long gameId);
}
