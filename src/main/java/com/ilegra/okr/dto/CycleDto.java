package com.ilegra.okr.dto;

import java.time.LocalDate;
import java.util.Set;
import lombok.Data;

@Data
public class CycleDto {

  private Integer id;

  private String title;

  private LocalDate startDate;

  private LocalDate endDate;

  private Set<ObjectiveDto> objectives;
}
