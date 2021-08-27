package com.ilegra.okr.model.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ObjectiveRequestModel {

  private String title;
  private Integer cycleId;
  private Integer objectiveFatherId;
  private Integer teamId;
}
