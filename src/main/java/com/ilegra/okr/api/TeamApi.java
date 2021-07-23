package com.ilegra.okr.api;


import com.ilegra.okr.dto.TeamDto;
import com.ilegra.okr.model.TeamModel;
import com.ilegra.okr.service.TeamService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/teams")
public class TeamApi {

	@Autowired
	TeamService teamService;

	@Autowired
	ModelMapper modelMapper;

	@GetMapping("")
	public ResponseEntity getTeams(){
		try {

			List<TeamDto> teams = this.teamService.getAllTeams();

			if(teams.size() == 0)
				return new ResponseEntity(HttpStatus.NO_CONTENT);

			List<TeamModel> result = teams
					.stream()
					.map(x -> modelMapper.map(x, TeamModel.class))
					.collect(Collectors.toList());

			return new ResponseEntity(result, HttpStatus.OK);
		}
		catch(Exception ex){
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
