package com.ilegra.okr.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(schema = "okr", name = "key_result_type")
public class KeyResultTypeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "key_result_type_id")
	private Integer id;

	@Column(name = "description")
	private String description;

	@Column(name = "label")
	private String label;

}
