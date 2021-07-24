package com.ilegra.okr.service;

import com.ilegra.okr.dto.KeyResultDto;
import com.ilegra.okr.dto.ObjectiveDto;
import com.ilegra.okr.entity.KeyResultEntity;
import com.ilegra.okr.entity.ObjectiveEntity;
import com.ilegra.okr.model.KeyResultModel;
import com.ilegra.okr.model.TeamModel;
import com.ilegra.okr.repository.KeyResultRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyResultService {

  private static final String NOT_FOUND_MESSAGE = "There is no key result with this id";

  @Autowired
  private KeyResultRepository repository;

  @Autowired
  private ModelMapper mapper;

  public KeyResultDto save(KeyResultModel model) {
    return mapper
        .map(repository.save(mapper.map(model, KeyResultEntity.class)), KeyResultDto.class);
  }

  public KeyResultDto update(KeyResultModel model, Integer id) {

    Optional<KeyResultEntity> entity = repository.findById(id);

    if (entity.isEmpty()) {
      throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
    }

    KeyResultEntity keyResultEntity = mapper.map(model, KeyResultEntity.class);
    keyResultEntity.setId(id);

    return mapper
        .map(repository.save(keyResultEntity), KeyResultDto.class);
  }

  public void delete(Integer id) {

    Optional<KeyResultEntity> entity = repository.findById(id);

    if (entity.isEmpty()) {
      throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
    }

    repository.delete(entity.get());
  }

  public KeyResultDto getById(Integer id) {

    Optional<KeyResultEntity> entity = repository.findById(id);

    if (entity.isEmpty()) {
      throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
    }

    return mapper.map(entity.get(), KeyResultDto.class);
  }

  public List<KeyResultDto> getAll() {

    return this.repository.findAll()
        .stream()
        .map(entity -> mapper.map(entity, KeyResultDto.class))
        .collect(Collectors.toList());
  }

  public List<KeyResultDto> getAllByObjectiveId(Integer objectiveId) {

    return this.repository.findAllByObjectiveId(objectiveId)
        .stream()
        .map(entity -> mapper.map(entity, KeyResultDto.class))
        .collect(Collectors.toList());
  }

}
