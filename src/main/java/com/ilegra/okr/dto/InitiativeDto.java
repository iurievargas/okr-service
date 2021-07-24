package com.ilegra.okr.dto;

import lombok.Data;

@Data
public class InitiativeDto {

  private Integer id;

  private String title;

  private String description;

  private String status;

  private Integer keyResultId;
}
