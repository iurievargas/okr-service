package com.ilegra.okr.repository;

import com.ilegra.okr.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {

    @Query("SELECT t FROM TeamEntity t ORDER BY t.name ASC")
    List<TeamEntity> findAllOrderByNameAscending();
}
