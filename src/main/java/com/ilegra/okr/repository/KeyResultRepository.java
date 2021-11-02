package com.ilegra.okr.repository;

import com.ilegra.okr.entity.KeyResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface KeyResultRepository extends JpaRepository<KeyResultEntity, Integer> {

    List<KeyResultEntity> findAllByObjectiveIdOrderByTeamNameAsc(Integer objectiveId);

    List<KeyResultEntity> findAllByObjectiveId(Integer objectiveId);

    @Transactional
    void deleteAllByObjectiveId(Integer objectiveId);
}
