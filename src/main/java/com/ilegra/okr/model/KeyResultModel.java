package com.ilegra.okr.model;

import lombok.Data;

@Data
public class KeyResultModel {

  private Integer id;

  private String description;

  private Integer baseline;

  private Integer target;

  private Integer result;

  private Integer objectiveId;

  private Integer teamId;
}
