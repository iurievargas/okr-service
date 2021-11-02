package com.ilegra.okr.repository;

import com.ilegra.okr.entity.CycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CycleRepository extends JpaRepository<CycleEntity, Integer> {

    @Query("SELECT c FROM CycleEntity c ORDER BY c.startDate DESC")
    List<CycleEntity> findAllOrderByStartDateDescending();
}
