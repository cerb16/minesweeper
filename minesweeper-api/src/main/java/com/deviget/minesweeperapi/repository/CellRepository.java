package com.deviget.minesweeperapi.repository;

import com.deviget.minesweeperapi.model.Cell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CellRepository extends JpaRepository<Cell,Long> {
}
