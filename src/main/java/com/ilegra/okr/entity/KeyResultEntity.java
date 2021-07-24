package com.ilegra.okr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "okr", name = "key_result")
public class KeyResultEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "key_result_id")
  private Integer id;

  @Column(name = "description")
  private String description;

  @Column(name = "baseline")
  private Integer baseline;

  @Column(name = "target")
  private Integer target;

  @Column(name = "result")
  private Integer result;

  @Column(name = "objective_id")
  private Integer objectiveId;

  @Column(name = "team_id")
  private Integer teamId;
}
