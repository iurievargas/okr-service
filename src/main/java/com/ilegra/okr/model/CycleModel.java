package com.ilegra.okr.model;

import java.time.LocalDate;
import java.util.Set;
import lombok.Data;

@Data
public class CycleModel {

  private Integer id;

  private String title;

  private LocalDate startDate;

  private LocalDate endDate;

  private Set<ObjectiveModel> objectives;
}
