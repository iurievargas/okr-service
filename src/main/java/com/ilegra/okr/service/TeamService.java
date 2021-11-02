package com.ilegra.okr.service;

import com.ilegra.okr.dto.TeamDto;
import com.ilegra.okr.entity.TeamEntity;
import com.ilegra.okr.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private static final String NOT_FOUND_MESSAGE = "There is no team with this id";

    @Autowired
    private TeamRepository repository;

    @Autowired
    private ModelMapper mapper;

    public TeamDto insert(TeamDto dto) {
        return mapper
                .map(repository.save(mapper.map(dto, TeamEntity.class)), TeamDto.class);
    }

    public TeamDto update(TeamDto dto, Integer id) {

        Optional<TeamEntity> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
        }

        TeamEntity teamEntity = mapper.map(dto, TeamEntity.class);
        teamEntity.setId(id);

        return mapper
                .map(repository.save(teamEntity), TeamDto.class);
    }

    public void delete(Integer id) {

        Optional<TeamEntity> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
        }

        repository.delete(entity.get());
    }

    public TeamDto getById(Integer id) {

        Optional<TeamEntity> entity = repository.findById(id);

        if (entity.isEmpty()) {
            throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
        }

        return mapper.map(entity.get(), TeamDto.class);
    }

    public List<TeamDto> getAll() {

        return this.repository.findAllOrderByNameAscending()
                .stream()
                .map(entity -> mapper.map(entity, TeamDto.class))
                .collect(Collectors.toList());
    }
}
