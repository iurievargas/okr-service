package com.ilegra.okr.dto;

import java.util.Set;
import lombok.Data;

@Data
public class KeyResultDto {

  private Integer id;

  private String description;

  private Integer baseline;

  private Integer target;

  private Integer result;

  private Integer objectiveId;

  private Integer teamId;

  private Set<InitiativeDto> initiatives;
}
