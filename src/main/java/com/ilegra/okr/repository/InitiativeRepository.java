package com.ilegra.okr.repository;

import com.ilegra.okr.entity.InitiativeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InitiativeRepository extends JpaRepository<InitiativeEntity, Integer> {

  List<InitiativeEntity> findAllByKeyResultId(Integer keyResultId);

}
