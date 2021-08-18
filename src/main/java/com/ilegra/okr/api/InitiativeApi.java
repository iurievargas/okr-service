package com.ilegra.okr.api;


import com.ilegra.okr.dto.InitiativeDto;
import com.ilegra.okr.entity.InitiativeEntity;
import com.ilegra.okr.model.request.InitiativeRequestModel;
import com.ilegra.okr.model.response.InitiativeResponseModel;
import com.ilegra.okr.service.InitiativeService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/initiatives")
public class InitiativeApi {

	@Autowired
	private InitiativeService initiativeService;

	@Autowired
	private ModelMapper mapper;

	@PostMapping
	public ResponseEntity<InitiativeResponseModel> insert(@RequestBody InitiativeRequestModel model)
	{
		var initiativeDto = this.initiativeService.save(mapper.map(model, InitiativeDto.class));
		var response = mapper.map(initiativeDto, InitiativeResponseModel.class);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<InitiativeResponseModel> update(@RequestBody InitiativeResponseModel model,
														  @PathVariable("id") Integer id) {

		var initiativeDto = this.initiativeService.update(mapper.map(model, InitiativeDto.class), id);
		var response = mapper.map(initiativeDto, InitiativeResponseModel.class);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {

		this.initiativeService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<InitiativeResponseModel> getById(@PathVariable("id") Integer id) {

		var response = mapper.map(this.initiativeService.getById(id), InitiativeResponseModel.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<InitiativeResponseModel>> getAll() {

		var response = this.initiativeService
				.getAll()
				.stream()
				.map(dto -> mapper.map(dto, InitiativeResponseModel.class))
				.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
