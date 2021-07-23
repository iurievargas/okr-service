package com.ilegra.okr.repository;

import com.ilegra.okr.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends JpaRepository<TeamEntity, Integer> { }
