package com.ilegra.okr.repository;

import com.ilegra.okr.entity.ObjectiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ObjectiveRepository extends JpaRepository<ObjectiveEntity, Integer> {

    List<ObjectiveEntity> findAllByCycleIdAndObjectiveFatherIdIsNull(Integer cycleId);

    List<ObjectiveEntity> findAllByObjectiveFatherIdOrderByTeamNameAsc(Integer objectiveFatherId);

    List<ObjectiveEntity> findAllByObjectiveFatherId(Integer objectiveFatherId);

    @Transactional
    void deleteAllByObjectiveFatherId(Integer objectiveFatherId);

    @Transactional
    void deleteAllByCycleId(Integer cycleId);


}
