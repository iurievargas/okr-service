package com.ilegra.okr.repository;

import com.ilegra.okr.entity.KeyResultUpdateHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeyResultUpdateHistoryRepository extends JpaRepository<KeyResultUpdateHistoryEntity, Integer> {

	List<KeyResultUpdateHistoryEntity> findAllByKeyResultId(Integer keyResultId);
}
