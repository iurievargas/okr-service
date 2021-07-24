package com.ilegra.okr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@ManyToOne
	@JoinColumn(name="objective_id")
	private ObjectiveEntity objective;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="team_id")
	private TeamEntity team;

	@OneToMany(mappedBy = "keyResult", orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<InitiativeEntity> initiatives;
}
