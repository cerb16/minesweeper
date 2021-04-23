package com.deviget.minesweeperapi.model.requestDto;

import lombok.Data;

@Data
public class GameRequest {
    private  Long id;

    private Long userId;

    private Integer rows;

    private Integer columns;

    private Integer mines;

    private boolean pause;
}

