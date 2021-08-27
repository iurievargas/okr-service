package com.ilegra.okr.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "okr", name = "objective")
public class ObjectiveEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "objective_id")
  private Integer id;

  @Column(name = "title")
  private String title;

  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @Column(name = "cycle_id")
  private Integer cycleId;

  @Column(name = "objective_father_id")
  private Integer objectiveFatherId;

  @Column(name = "team_id")
  private Integer teamId;
}
