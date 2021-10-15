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
	private ModelMapper mapper;

	public KeyResultDto insert(KeyResultDto keyResultDto) {
		KeyResultDto keyResult = mapper
				.map(repository.save(mapper.map(keyResultDto, KeyResultEntity.class)), KeyResultDto.class);

		this.keyResultUpdateHistoryService.save(keyResult.getId(), keyResult.getValue());

		return keyResult;
	}

	public KeyResultDto update(KeyResultDto keyResultDto, Integer id) {

		Optional<KeyResultEntity> entity = repository.findById(id);

		if (entity.isEmpty()) {
			throw new IllegalArgumentException(NOT_FOUND_MESSAGE);
		}

		KeyResultEntity keyResultEntity = mapper.map(keyResultDto, KeyResultEntity.class);
		keyResultEntity.setId(id);

		this.keyResultUpdateHistoryService.save(keyResultEntity.getId(), keyResultEntity.getValue());

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

		return this.repository.findAllByObjectiveId(objectiveId)
				.stream()
				.map(entity -> mapper.map(entity, KeyResultDto.class))
				.map(this::calculateProgress)
				.collect(Collectors.toList());
	}

	private KeyResultDto calculateProgress(KeyResultDto keyResult) {

		var baseline = keyResult.getBaseline();
		var target = keyResult.getTarget();
		var result = keyResult.getValue();

		var progress = ((result - baseline) / (target - baseline)) * 100;

		keyResult.setProgress(progress);

		return keyResult;
	}

}
