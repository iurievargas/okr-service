package com.ilegra.okr.service;

import com.ilegra.okr.dto.KeyResultDto;
import com.ilegra.okr.entity.KeyResultEntity;
import com.ilegra.okr.repository.KeyResultRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KeyResultService {

    private static final String NOT_FOUND_MESSAGE = "There is no key result with this id";

    @Autowired
    private KeyResultRepository repository;

    @Autowired
    private KeyResultUpdateHistoryService keyResultUpdateHistoryService;

    @Autowired
    private InitiativeService initiativeService;

    @Autowired
    private ModelMapper mapper;

    public KeyResultDto insert(KeyResultDto keyResultDto) {
        keyResultDto = mapper
                .map(repository.save(mapper.map(keyResultDto, KeyResultEntity.class)), KeyResultDto.class);

        this.keyResultUpdateHistoryService.save(keyResultDto.getId(), keyResultDto.getValue());

        return this.getById(keyResultDto.getId());
    }

    public KeyResultDto update(KeyResultDto keyResultDto, Integer id) {

        Optional<KeyResultEntity> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
        }

        KeyResultEntity keyResultEntity = mapper.map(keyResultDto, KeyResultEntity.class);
        keyResultEntity.setId(id);

        this.keyResultUpdateHistoryService.save(keyResultEntity.getId(), keyResultEntity.getValue());

        keyResultDto = mapper
                .map(repository.save(keyResultEntity), KeyResultDto.class);

        return this.calculateProgress(keyResultDto);

    }

    public void deleteAllByObjectiveId(Integer id) {

        this.repository.findAllByObjectiveId(id)
                .stream()
                .map(KeyResultEntity::getId)
                .peek(this::delete)
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {

        repository.findById(id)
                .stream()
                .map(KeyResultEntity::getId)
                .peek(initiativeService::deleteAllByKeyResultId)
                .peek(keyResultUpdateHistoryService::deleteAllByKeyResultId)
                .peek(repository::deleteById)
                .collect(Collectors.toList());
    }

    public KeyResultDto getById(Integer id) {

        Optional<KeyResultEntity> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
        }

        var keyResultDto = mapper.map(entity.get(), KeyResultDto.class);
        keyResultDto = this.calculateProgress(keyResultDto);

        return keyResultDto;

    }

    public List<KeyResultDto> getAll() {

        return this.repository.findAll()
                .stream()
                .map(entity -> mapper.map(entity, KeyResultDto.class))
                .map(this::calculateProgress)
                .collect(Collectors.toList());
    }

    public List<KeyResultDto> getAllByObjectiveId(Integer objectiveId) {

        return this.repository.findAllByObjectiveIdOrderByTeamNameAsc(objectiveId)
                .stream()
                .map(entity -> mapper.map(entity, KeyResultDto.class))
                .map(this::calculateProgress)
                .collect(Collectors.toList());
    }

    private KeyResultDto calculateProgress(KeyResultDto keyResult) {

        var baseline = keyResult.getBaseline();
        var target = keyResult.getTarget();
        var value = keyResult.getValue();

        var progress = 0.0;
        if (baseline.equals(target)) {
            progress = ((value - target) * 100) / target;
        } else {
            progress = ((value - baseline) / (target - baseline)) * 100;
        }

        progress = Math.round(progress);

        keyResult.setProgress(progress);

        return keyResult;
    }

}
