package com.ilegra.okr.model.request;

import lombok.Data;

@Data
public class InitiativeRequestModel {

  private String title;
  private String description;
  private String status;
  private Integer keyResultId;
}
