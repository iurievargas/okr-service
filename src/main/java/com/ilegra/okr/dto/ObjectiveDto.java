package com.ilegra.okr.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ObjectiveDto {

    private Integer id;
    private String title;
    private String annotations;
    private LocalDateTime createdDate;
    private String type;
    private Integer cycleId;
    private Integer objectiveFatherId;
    private TeamDto team;
    private Double progress;
}
