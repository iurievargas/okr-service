package com.ilegra.okr.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class CycleDto {

  private Integer id;

  private String title;

  private LocalDate startDate;

  private LocalDate endDate;
}
