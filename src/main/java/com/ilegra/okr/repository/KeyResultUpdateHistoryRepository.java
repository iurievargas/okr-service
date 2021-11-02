package com.ilegra.okr.repository;

import com.ilegra.okr.entity.KeyResultUpdateHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface KeyResultUpdateHistoryRepository extends JpaRepository<KeyResultUpdateHistoryEntity, Integer> {

    KeyResultUpdateHistoryEntity findTopByKeyResultIdOrderByIdDesc(Integer keyResultId);

    List<KeyResultUpdateHistoryEntity> findAllByKeyResultId(Integer keyResultId);

    @Transactional
    void deleteAllByKeyResultId(Integer keyResultId);
}
