package com.ilegra.okr.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KeyResultUpdateHistoryDto {

	private Integer id;
	private Double newValue;
	private LocalDateTime updatedDate;
	private Integer keyResultId;
}
