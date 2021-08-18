package com.ilegra.okr.model.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ObjectiveResponseModel {

  private Integer id;
  private String title;
  private LocalDateTime createdDate;
  private String type;
  private Integer cycleId;
  private Integer objectiveFatherId;
}
