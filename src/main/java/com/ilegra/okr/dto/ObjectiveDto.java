package com.ilegra.okr.dto;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Data;

@Data
public class ObjectiveDto {

  private Integer id;

  private String title;

  private LocalDateTime createdDate;

  private String type;

  private Integer cycleId;

  private Set<KeyResultDto> keyResults;
}
