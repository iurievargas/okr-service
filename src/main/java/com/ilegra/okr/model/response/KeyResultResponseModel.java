package com.ilegra.okr.model.response;

import lombok.Data;

@Data
public class KeyResultResponseModel {

  private Integer id;
  private String description;
  private String labels;
  private Integer baseline;
  private Double target;
  private Double result;
  private Double progress;
  private Integer objectiveId;
  private Integer teamId;
}
