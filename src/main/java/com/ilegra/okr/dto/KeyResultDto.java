package com.ilegra.okr.dto;

import lombok.Data;

@Data
public class KeyResultDto {

  private Integer id;

  private String description;

  private Integer baseline;

  private Integer target;

  private Integer result;

  private Integer objectiveId;

  private Integer teamId;
}
