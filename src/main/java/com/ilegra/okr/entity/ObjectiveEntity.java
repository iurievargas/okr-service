package com.ilegra.okr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
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

	@Column(name = "type")
	private String type;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="cycle_id")
	private CycleEntity cycle;

	@OneToMany(mappedBy = "objective", orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<KeyResultEntity> keyResults;
}
