package com.ilegra.okr.service;

import com.ilegra.okr.dto.KeyResultTypeDto;
import com.ilegra.okr.repository.KeyResultTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeyResultTypeService {

    @Autowired
    private KeyResultTypeRepository repository;

    @Autowired
    private ModelMapper mapper;

    public List<KeyResultTypeDto> getAll() {

        return this.repository.findAll()
                .stream()
                .map(entity -> mapper.map(entity, KeyResultTypeDto.class))
                .collect(Collectors.toList());
    }
}
