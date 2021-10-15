package com.ilegra.okr.model.request;

import lombok.Data;

@Data
public class KeyResultRequestModel {

	private String description;
	private String labels;
	private Double baseline;
	private Double target;
	private Double value;
	private Integer objectiveId;
	private Integer teamId;
	private Integer keyResultTypeId;
}
