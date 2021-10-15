package com.ilegra.okr.model.response;

import lombok.Data;

@Data
public class KeyResultResponseModel {

	private Integer id;
	private String description;
	private String labels;
	private Integer baseline;
	private Double target;
	private Double value;
	private Double progress;
	private Integer objectiveId;
	private TeamResponseModel team;
	private KeyResultTypeResponseModel type;
}
