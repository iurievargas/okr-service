package com.ilegra.okr.api;


import com.ilegra.okr.dto.KeyResultDto;
import com.ilegra.okr.dto.ObjectiveDto;
import com.ilegra.okr.model.request.KeyResultRequestModel;
import com.ilegra.okr.model.request.ObjectiveRequestModel;
import com.ilegra.okr.model.response.KeyResultResponseModel;
import com.ilegra.okr.model.response.ObjectiveResponseModel;
import com.ilegra.okr.service.KeyResultService;
import com.ilegra.okr.service.ObjectiveService;
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
@RequestMapping("/v1/objectives")
public class ObjectiveApi {

	@Autowired
	private ObjectiveService objectiveService;

	@Autowired
	private KeyResultService keyResultService;

	@Autowired
	private ModelMapper mapper;

	@PostMapping
	public ResponseEntity<ObjectiveResponseModel> save(@RequestBody ObjectiveRequestModel model) {

		var dto = this.objectiveService.insert(mapper.map(model, ObjectiveDto.class));
		var response = mapper.map(dto, ObjectiveResponseModel.class);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ObjectiveResponseModel> update(@RequestBody ObjectiveRequestModel model,
														 @PathVariable("id") Integer id) {

		var dto = this.objectiveService.update(mapper.map(model, ObjectiveDto.class), id);
		var response = mapper.map(dto, ObjectiveResponseModel.class);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {

		this.objectiveService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ObjectiveResponseModel> getById(@PathVariable("id") Integer id) {

		var response = mapper.map(this.objectiveService.getById(id), ObjectiveResponseModel.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ObjectiveResponseModel>> getAll() {

		var response = this.objectiveService
				.getAll()
				.stream()
				.map(dto -> mapper.map(dto, ObjectiveResponseModel.class))
				.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}/key-results")
	public ResponseEntity<List<KeyResultResponseModel>> getAllKeyResultsById(@PathVariable("id") Integer id) {

		var response = this.keyResultService
				.getAllByObjectiveId(id)
				.stream()
				.map(dto -> mapper.map(dto, KeyResultResponseModel.class))
				.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{objectiveFatherId}/objectives")
	public ResponseEntity<List<ObjectiveResponseModel>> getAllObjectivesByObjectiveFatherId(
			@PathVariable("objectiveFatherId") Integer objectiveFatherId) {

		var response = this.objectiveService
				.getAllByObjectiveFatherId(objectiveFatherId)
				.stream()
				.map(dto -> mapper.map(dto, ObjectiveResponseModel.class))
				.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
