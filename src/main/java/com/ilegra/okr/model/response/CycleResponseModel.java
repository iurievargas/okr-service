package com.ilegra.okr.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CycleResponseModel {

  private Integer id;
  private String title;
  private LocalDate startDate;
  private LocalDate endDate;
}
