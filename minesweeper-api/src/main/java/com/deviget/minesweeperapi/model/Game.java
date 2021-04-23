package com.deviget.minesweeperapi.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(value = EnumType.STRING)
    @Column(name="game_status")
    private GameStatus gameStatus;

    @Column(name = "rows")
    private Integer rows;

    @Column(name = "columns")
    private Integer columns;

    @Column(name = "mines")
    private Integer mines;

    @Column(name = "pause")
    private boolean pause;

    @Column (name ="game_start_date")
    private LocalDateTime gameStarDate;

    @Column (name ="last_restart_date")
    private LocalDateTime lastRestartDate;

    @Column (name ="last_move_date")
    private LocalDateTime lastMoveDate;

    @Column (name ="game_time")
    private Long gameTime;

}
