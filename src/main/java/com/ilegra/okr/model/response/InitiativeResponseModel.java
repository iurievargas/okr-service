package com.ilegra.okr.model.response;

import lombok.Data;

@Data
public class InitiativeResponseModel {

  private Integer id;
  private String title;
  private String description;
  private String status;
  private Integer keyResultId;
}
