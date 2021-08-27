package com.ilegra.okr.dto;

import lombok.Data;

@Data
public class KeyResultDto {

  private Integer id;
  private String description;
  private String labels;
  private Double baseline;
  private Double target;
  private Double result;
  private Integer objectiveId;
  private Integer teamId;
  private Double progress;
}
