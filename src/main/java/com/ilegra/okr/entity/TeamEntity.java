package com.ilegra.okr.entity;

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
	@Column(name = "id")
	private Integer teamId;

	@Column(name = "name")
	private String teamName;
}
