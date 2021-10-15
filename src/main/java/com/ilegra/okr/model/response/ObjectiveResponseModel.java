package com.ilegra.okr.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ObjectiveResponseModel {

	private Integer id;
	private String title;
	private LocalDateTime createdDate;
	private Double progress;
	private Integer cycleId;
	private Integer objectiveFatherId;
	private TeamResponseModel team;
}
