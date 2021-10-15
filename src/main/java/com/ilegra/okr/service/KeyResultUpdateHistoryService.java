package com.ilegra.okr.service;

import com.ilegra.okr.dto.KeyResultUpdateHistoryDto;
import com.ilegra.okr.entity.KeyResultUpdateHistoryEntity;
import com.ilegra.okr.repository.KeyResultUpdateHistoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeyResultUpdateHistoryService {

	@Autowired
	private KeyResultUpdateHistoryRepository repository;

	@Autowired
	private ModelMapper mapper;

	public KeyResultUpdateHistoryDto save(Integer keyResultId, Double newValue) {
		KeyResultUpdateHistoryDto dto = new KeyResultUpdateHistoryDto();

		dto.setKeyResultId(keyResultId);
		dto.setNewValue(newValue);
		dto.setUpdatedDate(LocalDateTime.now());

		return mapper
				.map(repository.save(mapper.map(dto, KeyResultUpdateHistoryEntity.class)), KeyResultUpdateHistoryDto.class);
	}

	public List<KeyResultUpdateHistoryDto> getAllByKeyResultId(Integer keyResultId) {

		return this.repository.findAllByKeyResultId(keyResultId)
				.stream()
				.map(entity -> mapper.map(entity, KeyResultUpdateHistoryDto.class))
				.collect(Collectors.toList());
	}
}
