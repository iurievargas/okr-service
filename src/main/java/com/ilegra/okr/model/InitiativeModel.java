package com.ilegra.okr.model;

import lombok.Data;

@Data
public class InitiativeModel {

  private Integer id;

  private String title;

  private String description;

  private String status;

  private Integer keyResultId;
}
