package com.ilegra.okr.api;


import com.ilegra.okr.dto.KeyResultDto;
import com.ilegra.okr.model.response.InitiativeResponseModel;
import com.ilegra.okr.model.request.KeyResultRequestModel;
import com.ilegra.okr.model.response.KeyResultResponseModel;
import com.ilegra.okr.service.InitiativeService;
import com.ilegra.okr.service.KeyResultService;
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
@RequestMapping("/v1/key-results")
public class KeyResultApi {

	@Autowired
	private KeyResultService keyResultService;

	@Autowired
	private InitiativeService initiativeService;

	@Autowired
	private ModelMapper mapper;

	@PostMapping
	public ResponseEntity<KeyResultResponseModel> insert(@RequestBody KeyResultRequestModel model) {

		var dto = this.keyResultService.insert(mapper.map(model, KeyResultDto.class));
		var response = mapper.map(dto, KeyResultResponseModel.class);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<KeyResultResponseModel> update(@RequestBody KeyResultRequestModel model,
														 @PathVariable("id") Integer id) {

		var dto = this.keyResultService.update(mapper.map(model, KeyResultDto.class), id);
		var response = mapper.map(dto, KeyResultResponseModel.class);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {

		this.keyResultService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<KeyResultResponseModel> getById(@PathVariable("id") Integer id) {

		var response = mapper.map(this.keyResultService.getById(id), KeyResultResponseModel.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<KeyResultResponseModel>> getAll() {

		var response = this.keyResultService
				.getAll()
				.stream()
				.map(dto -> mapper.map(dto, KeyResultResponseModel.class))
				.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}/initiatives")
	public ResponseEntity<List<InitiativeResponseModel>> getAllInitiativesById(@PathVariable("id") Integer id) {

		var response = this.initiativeService
				.getAllByKeyResultId(id)
				.stream()
				.map(dto -> mapper.map(dto, InitiativeResponseModel.class))
				.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
