package com.deviget.minesweeperapi.model.requestDto;


import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class MoveRequest {

    private Long id;

    private Integer xPosition;

    private Integer  yPosition;

    private Boolean flag;
}
