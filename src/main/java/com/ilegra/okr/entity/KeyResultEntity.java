package com.ilegra.okr.entity;

import lombok.Data;

import javax.persistence.*;

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

	@Column(name = "labels")
	private String labels;

	@Column(name = "baseline")
	private Double baseline;

	@Column(name = "target")
	private Double target;

	@Column(name = "value")
	private Double value;

	@Column(name = "objective_id")
	private Integer objectiveId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private TeamEntity team;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "key_result_type_id")
	private KeyResultTypeEntity type;

}
