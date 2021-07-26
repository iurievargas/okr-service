package com.ilegra.okr.repository;

import com.ilegra.okr.entity.ObjectiveEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectiveRepository extends JpaRepository<ObjectiveEntity, Integer> {

  List<ObjectiveEntity> findAllByCycleId(Integer cycleId);

  List<ObjectiveEntity> findAllByObjectiveFatherId(Integer objectiveFatherId);

}
