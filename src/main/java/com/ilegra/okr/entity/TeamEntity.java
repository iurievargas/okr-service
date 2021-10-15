package com.ilegra.okr.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "okr", name = "team")
public class TeamEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
	private Integer id;

	@Column(name = "name")
	private String name;
}
