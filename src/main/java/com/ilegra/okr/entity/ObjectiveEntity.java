package com.ilegra.okr.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "annotations")
    private String annotations;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "cycle_id")
    private Integer cycleId;

    @Column(name = "objective_father_id")
    private Integer objectiveFatherId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private TeamEntity team;
}
