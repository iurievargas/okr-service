package com.ilegra.okr.api;


import com.ilegra.okr.dto.CycleDto;
import com.ilegra.okr.model.request.CycleRequestModel;
import com.ilegra.okr.model.response.CycleResponseModel;
import com.ilegra.okr.model.response.ObjectiveResponseModel;
import com.ilegra.okr.service.CycleService;
import com.ilegra.okr.service.ObjectiveService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/cycles")
public class CycleApi {

	@Autowired
	private CycleService cycleService;

	@Autowired
	private ObjectiveService objectiveService;

	@Autowired
	private ModelMapper mapper;

	@PostMapping
	public ResponseEntity<CycleResponseModel> save(@RequestBody CycleRequestModel model) {

		var dto = this.cycleService.save(mapper.map(model, CycleDto.class));
		var response = mapper.map(dto, CycleResponseModel.class);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CycleResponseModel> update(@RequestBody CycleRequestModel model,
													 @PathVariable("id") Integer id) {

		var dto = this.cycleService.update(mapper.map(model, CycleDto.class), id);
		var response = mapper.map(dto, CycleResponseModel.class);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {

		this.cycleService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CycleResponseModel> getById(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(mapper.map(this.cycleService.getById(id), CycleResponseModel.class),
				HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<CycleResponseModel>> getAll() {
		var response = this.cycleService
				.getAll()
				.stream()
				.map(dto -> mapper.map(dto, CycleResponseModel.class))
				.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}/objectives")
	public ResponseEntity<List<ObjectiveResponseModel>> getAllObjectivesById(@PathVariable("id") Integer id) {

		var response = this.objectiveService
				.getAllByCycleId(id)
				.stream()
				.map(dto -> mapper.map(dto, ObjectiveResponseModel.class))
				.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
