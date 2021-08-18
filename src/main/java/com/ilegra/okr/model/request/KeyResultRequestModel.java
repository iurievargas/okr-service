package com.ilegra.okr.model.request;

import lombok.Data;

@Data
public class KeyResultRequestModel {

  private String description;
  private Integer baseline;
  private Integer target;
  private Integer result;
  private Integer objectiveId;
  private Integer teamId;
}
