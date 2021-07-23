package com.ilegra.okr.service;

import com.ilegra.okr.config.ModelMapperConfig;
import com.ilegra.okr.dto.TeamDto;
import com.ilegra.okr.repository.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<TeamDto> getAllTeams(){

		return this.teamRepository.findAll()
				.stream()
				.map(x -> modelMapper.map(x, TeamDto.class))
				.collect(Collectors.toList());
	}
}
