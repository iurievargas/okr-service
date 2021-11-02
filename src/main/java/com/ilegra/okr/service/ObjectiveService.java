package com.ilegra.okr.service;

import com.ilegra.okr.dto.KeyResultDto;
import com.ilegra.okr.dto.ObjectiveDto;
import com.ilegra.okr.entity.ObjectiveEntity;
import com.ilegra.okr.repository.ObjectiveRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObjectiveService {

    private static final String NOT_FOUND_MESSAGE = "There is no objective with this id";
    private static final Double PROGRESS_LIMIT = 100.00;

    @Autowired
    private ObjectiveRepository repository;

    @Autowired
    private KeyResultService keyResultService;

    @Autowired
    private ModelMapper mapper;

    public ObjectiveDto insert(ObjectiveDto dto) {

        dto.setCreatedDate(LocalDateTime.now());

        var objectiveDto = mapper
                .map(repository.save(mapper.map(dto, ObjectiveEntity.class)), ObjectiveDto.class);

        objectiveDto = this.getById(objectiveDto.getId());

        return this.calculateProgress(objectiveDto);
    }

    public ObjectiveDto update(ObjectiveDto dto, Integer id) {

        Optional<ObjectiveEntity> entity = repository.findById(id);

        if (entity.isEmpty())
            throw new IllegalArgumentException(NOT_FOUND_MESSAGE);

        ObjectiveEntity objectiveEntity = entity.get();

        dto.setId(id);
        dto.setCreatedDate(objectiveEntity.getCreatedDate());

        objectiveEntity = mapper.map(dto, ObjectiveEntity.class);

        var objectiveDto = mapper
                .map(repository.save(objectiveEntity), ObjectiveDto.class);

        objectiveDto = this.getById(objectiveDto.getId());

        return this.calculateProgress(objectiveDto);
    }

    public void delete(Integer id) {

        repository.findById(id)
                .stream()
                .map(ObjectiveEntity::getId)
                .peek(keyResultService::deleteAllByObjectiveId)
                .peek(this::deleteAllByObjectiveFatherId)
                .peek(repository::deleteById)
                .collect(Collectors.toList());
    }


    public void deleteAllByCycleId(Integer cycleId) {

        repository.findAllByCycleIdAndObjectiveFatherIdIsNull(cycleId)
                .stream()
                .map(ObjectiveEntity::getId)
                .peek(keyResultService::deleteAllByObjectiveId)
                .peek(this::deleteAllByObjectiveFatherId)
                .peek(repository::deleteById)
                .collect(Collectors.toList());
    }

    private void deleteAllByObjectiveFatherId(Integer objectiveFatherId) {

        this.repository.findAllByObjectiveFatherId(objectiveFatherId)
                .stream()
                .map(ObjectiveEntity::getId)
                .peek(this::delete)
                .collect(Collectors.toList());
    }

    public ObjectiveDto getById(Integer id) {

        Optional<ObjectiveEntity> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
        }

        var objectiveDto = mapper.map(entity.get(), ObjectiveDto.class);

        return this.calculateProgress(objectiveDto);
    }

    public List<ObjectiveDto> getAll() {
        return this.repository.findAll()
                .stream()
                .map(entity -> mapper.map(entity, ObjectiveDto.class))
                .map(this::calculateProgress)
                .collect(Collectors.toList());
    }

    public List<ObjectiveDto> getAllByCycleId(Integer cycleId) {
        return repository.findAllByCycleIdAndObjectiveFatherIdIsNull(cycleId)
                .stream()
                .map(entity -> mapper.map(entity, ObjectiveDto.class))
                .map(this::calculateProgress)
                .collect(Collectors.toList());
    }

    public List<ObjectiveDto> getAllByObjectiveFatherId(Integer objectiveFatherId) {
        return repository.findAllByObjectiveFatherIdOrderByTeamNameAsc(objectiveFatherId)
                .stream()
                .map(entity -> mapper.map(entity, ObjectiveDto.class))
                .map(this::calculateProgress)
                .collect(Collectors.toList());
    }

    private ObjectiveDto calculateProgress(ObjectiveDto objectiveDto) {

        var listOfProgress = this.getAllKeyResultsProgressByObjectiveId(objectiveDto.getId());

        this.getAllByObjectiveFatherId(objectiveDto.getId())
                .stream()
                .map(ObjectiveDto::getId)
                .peek(id -> listOfProgress.addAll(this.getAllKeyResultsProgressByObjectiveId(id)))
                .collect(Collectors.toList());

        var progress = 0.00;
        var count = listOfProgress.size();
        if (count > 0) {
            var sum = listOfProgress.stream().reduce(0.0, Double::sum);
            progress = sum / count;
        }

        objectiveDto.setProgress(progress);

        return objectiveDto;
    }

    private List<Double> getAllKeyResultsProgressByObjectiveId(Integer objectiveId) {

        return this.keyResultService.getAllByObjectiveId(objectiveId)
                .stream()
                .map(KeyResultDto::getProgress)
                .map(this::prepareProgressToObjectiveProgress)
                .collect(Collectors.toList());
    }

    private Double prepareProgressToObjectiveProgress(Double progress) {

        if (progress > PROGRESS_LIMIT)
            return PROGRESS_LIMIT;
        return progress;
    }
}
