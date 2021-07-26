package com.ilegra.okr.model;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ObjectiveModel {

  private Integer id;

  private String title;

  private LocalDateTime createdDate;

  private String type;

  private Integer cycleId;

  private Integer objectiveFatherId;
}
