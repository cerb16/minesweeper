package com.deviget.minesweeperapi.model.requestDto;

import lombok.Data;

@Data
public class MoveRequest {
    private int xPosition;

    private int yPosition;

    private boolean flag;
}
