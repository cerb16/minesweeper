package com.deviget.minesweeperapi.model.requestDto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class GameRequest {
    private  Long id;

    @NotNull(message = "UserId can´t be null")
    private Long userId;

    @Min(value = 2, message = "The minimum rows values is 2")
    @NotNull(message = "UserId can´t be null")
    private Integer rows;

    @Min(value = 2, message = "The minimum rows values is 2")
    @NotNull(message = "UserId can´t be null")
    private Integer columns;

    @Min(value = 1, message = "The minimum rows values is 1")
    @NotNull(message = "UserId can´t be null")
    private Integer mines;

    private boolean pause;
}

