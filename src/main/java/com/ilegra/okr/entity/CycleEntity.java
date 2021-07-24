package com.ilegra.okr.entity;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(schema = "okr", name = "cycle")
public class CycleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cycle_id")
	private Integer id;

	@Column(name = "title")
	private String title;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@OneToMany(mappedBy = "cycle", orphanRemoval = true, cascade = CascadeType.ALL)
	private Set<ObjectiveEntity> objectives;
}
