package com.ilegra.okr.repository;

import com.ilegra.okr.entity.InitiativeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface InitiativeRepository extends JpaRepository<InitiativeEntity, Integer> {

    List<InitiativeEntity> findAllByKeyResultId(Integer keyResultId);

    @Transactional
    void deleteByKeyResultId(Integer keyResultId);

}
