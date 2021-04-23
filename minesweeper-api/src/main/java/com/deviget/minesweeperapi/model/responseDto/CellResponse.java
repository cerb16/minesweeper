package com.deviget.minesweeperapi.model.responseDto;

import lombok.Data;

@Data
public class CellResponse {
    private int xPosition;
    private int yPosition;
    private long value;
    private boolean mine;
    private boolean flag;

}
