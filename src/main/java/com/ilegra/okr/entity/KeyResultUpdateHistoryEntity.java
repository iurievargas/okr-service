package com.ilegra.okr.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(schema = "okr", name = "key_result_update_history")
public class KeyResultUpdateHistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "key_result_update_history_id")
	private Integer id;

	@Column(name = "new_value")
	private Double newValue;

	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	@Column(name = "key_result_id")
	private Integer keyResultId;

}
