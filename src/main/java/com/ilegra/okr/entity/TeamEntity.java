package com.ilegra.okr.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(schema = "okr", name = "team")
public class TeamEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "team_id")
	private Integer id;

	@Column(name = "name")
	private String teamName;

	@OneToMany(mappedBy = "team", orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<KeyResultEntity> keyResults;
}
