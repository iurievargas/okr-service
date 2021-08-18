package com.ilegra.okr.model.response;

import lombok.Data;

@Data
public class KeyResultResponseModel {

  private Integer id;
  private String description;
  private Integer baseline;
  private Integer target;
  private Integer result;
  private Integer objectiveId;
  private Integer teamId;
}
