package com.ilegra.okr.model.response;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ObjectiveResponseModel {

	private Integer id;
	private String title;
	private LocalDateTime createdDate;
	private Double progress;
	private Integer cycleId;
	private Integer objectiveFatherId;
	private Integer teamId;
}
