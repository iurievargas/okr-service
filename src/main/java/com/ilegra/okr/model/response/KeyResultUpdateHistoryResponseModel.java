package com.ilegra.okr.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KeyResultUpdateHistoryResponseModel {

	private Integer id;
	private Double newValue;
	private LocalDateTime updatedDate;
	private Integer keyResultId;
}
