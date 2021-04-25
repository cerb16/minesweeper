package com.deviget.minesweeperapi.model.responseDto;

import lombok.Data;

import java.util.List;

@Data
public class MoveResponse {
    private long gameId;
    private String gameStatus;
    private CellResponse[][] gameField;
    private String[] gameFieldView;
    private long gameTime;

}
