package com.deviget.minesweeperapi.model.responseDto;

import lombok.Data;

@Data
public class CellResponse {
    private long id;
    private int xPosition;
    private int yPosition;
    private Integer value;
    private Boolean mine;
    private Boolean flag;
    private Boolean revealed;

}
