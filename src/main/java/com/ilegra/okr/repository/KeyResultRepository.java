package com.ilegra.okr.repository;

import com.ilegra.okr.entity.KeyResultEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyResultRepository extends JpaRepository<KeyResultEntity, Integer> {

  List<KeyResultEntity> findAllByObjectiveId(Integer objectiveId);
}
