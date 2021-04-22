package com.deviget.minesweeperapi.repository;

import com.deviget.minesweeperapi.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game,Long> {
    List<Game> findAllByUserId(long userId);
}
