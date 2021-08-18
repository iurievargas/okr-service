package com.ilegra.okr.model.request;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CycleRequestModel {

  private String title;
  private LocalDate startDate;
  private LocalDate endDate;
}
