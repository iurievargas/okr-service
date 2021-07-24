package com.ilegra.okr.service;

import com.ilegra.okr.dto.CycleDto;
import com.ilegra.okr.dto.InitiativeDto;
import com.ilegra.okr.entity.CycleEntity;
import com.ilegra.okr.entity.InitiativeEntity;
import com.ilegra.okr.model.InitiativeModel;
import com.ilegra.okr.model.TeamModel;
import com.ilegra.okr.repository.InitiativeRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitiativeService {

  private static final String NOT_FOUND_MESSAGE = "There is no initiative with this id";

  @Autowired
  private InitiativeRepository repository;

  @Autowired
  private ModelMapper mapper;

  public InitiativeDto save(InitiativeModel model) {
    return mapper
        .map(repository.save(mapper.map(model, InitiativeEntity.class)), InitiativeDto.class);
  }

  public InitiativeDto update(InitiativeModel model, Integer id) {

    Optional<InitiativeEntity> entity = repository.findById(id);

    if (entity.isEmpty()) {
      throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
    }

    InitiativeEntity initiativeEntity = mapper.map(model, InitiativeEntity.class);
    initiativeEntity.setId(id);

    return mapper
        .map(repository.save(initiativeEntity), InitiativeDto.class);
  }

  public void delete(Integer id) {

    Optional<InitiativeEntity> entity = repository.findById(id);

    if (entity.isEmpty()) {
      throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
    }

    repository.delete(entity.get());
  }

  public InitiativeDto getById(Integer id){

    Optional<InitiativeEntity> entity = repository.findById(id);

    if(entity.isEmpty()){
      throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
    }

    return mapper.map(entity.get(), InitiativeDto.class);
  }

  public List<InitiativeDto> getAll() {

    return this.repository.findAll()
        .stream()
        .map(entity -> mapper.map(entity, InitiativeDto.class))
        .collect(Collectors.toList());
  }
}
