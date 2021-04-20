package com.deviget.minesweeperapi.repository;

import com.deviget.minesweeperapi.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Long> {
}
