package com.ilegra.okr.service;

import com.ilegra.okr.dto.CycleDto;
import com.ilegra.okr.entity.CycleEntity;
import com.ilegra.okr.repository.CycleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CycleService {

    private static final String NOT_FOUND_MESSAGE = "There is no cycle with this id";

    @Autowired
    private CycleRepository repository;

    @Autowired
    private ObjectiveService objectiveService;

    @Autowired
    private ModelMapper mapper;

    public CycleDto insert(CycleDto dto) {
        return mapper
                .map(repository.save(mapper.map(dto, CycleEntity.class)), CycleDto.class);
    }

    public CycleDto update(CycleDto dto, Integer id) {

        Optional<CycleEntity> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
        }

        CycleEntity cycleEntity = mapper.map(dto, CycleEntity.class);
        cycleEntity.setId(id);

        return mapper
                .map(repository.save(cycleEntity), CycleDto.class);
    }

    public void delete(Integer id) {

        repository.findById(id)
                .stream()
                .map(CycleEntity::getId)
                .peek(objectiveService::deleteAllByCycleId)
                .peek(repository::deleteById)
                .collect(Collectors.toList());
    }

    public CycleDto getById(Integer id) {

        Optional<CycleEntity> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
        }

        return mapper.map(entity.get(), CycleDto.class);
    }

    public List<CycleDto> getAll() {

        return this.repository.findAllOrderByStartDateDescending()
                .stream()
                .map(entity -> mapper.map(entity, CycleDto.class))
                .collect(Collectors.toList());
    }
}
