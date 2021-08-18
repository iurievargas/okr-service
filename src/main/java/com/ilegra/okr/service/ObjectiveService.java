package com.ilegra.okr.service;

import com.ilegra.okr.dto.ObjectiveDto;
import com.ilegra.okr.entity.ObjectiveEntity;
import com.ilegra.okr.model.response.ObjectiveResponseModel;
import com.ilegra.okr.repository.ObjectiveRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectiveService {

  private static final String NOT_FOUND_MESSAGE = "There is no objective with this id";

  @Autowired
  private ObjectiveRepository repository;

  @Autowired
  private ModelMapper mapper;

  public ObjectiveDto insert(ObjectiveDto dto) {

  	dto.setCreatedDate(LocalDateTime.now());

    return mapper
        .map(repository.save(mapper.map(dto, ObjectiveEntity.class)), ObjectiveDto.class);
  }

  public ObjectiveDto update(ObjectiveDto dto, Integer id) {

    Optional<ObjectiveEntity> entity = repository.findById(id);

    if (entity.isEmpty()) {
      throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
    }

    ObjectiveEntity objectiveEntity = mapper.map(dto, ObjectiveEntity.class);
    objectiveEntity.setId(id);

    return mapper
        .map(repository.save(objectiveEntity), ObjectiveDto.class);
  }

  public void delete(Integer id) {

    Optional<ObjectiveEntity> entity = repository.findById(id);

    if (entity.isEmpty()) {
      throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
    }

    repository.delete(entity.get());
  }

  public ObjectiveDto getById(Integer id) {

    Optional<ObjectiveEntity> entity = repository.findById(id);

    if (entity.isEmpty()) {
      throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
    }

    return mapper.map(entity.get(), ObjectiveDto.class);
  }

  public List<ObjectiveDto> getAll() {
    return this.repository.findAll()
        .stream()
        .map(entity -> mapper.map(entity, ObjectiveDto.class))
        .collect(Collectors.toList());
  }

  public List<ObjectiveDto> getAllByCycleId(Integer cycleId) {
    return repository.findAllByCycleId(cycleId)
        .stream()
        .map(entity -> mapper.map(entity, ObjectiveDto.class))
        .collect(Collectors.toList());
  }

  public List<ObjectiveDto> getAllByObjectiveFatherId(Integer objectiveFatherId) {
    return repository.findAllByObjectiveFatherId(objectiveFatherId)
        .stream()
        .map(entity -> mapper.map(entity, ObjectiveDto.class))
        .collect(Collectors.toList());
  }

}
