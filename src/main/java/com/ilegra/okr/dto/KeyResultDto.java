package com.ilegra.okr.dto;

import lombok.Data;

@Data
public class KeyResultDto {

    private Integer id;
    private String description;
    private String annotations;
    private String labels;
    private Double baseline;
    private Double target;
    private Double value;
    private Integer objectiveId;
    private TeamDto team;
    private Double progress;
    private KeyResultTypeDto type;
}
