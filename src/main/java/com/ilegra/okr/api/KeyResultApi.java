package com.ilegra.okr.api;


import com.ilegra.okr.dto.KeyResultDto;
import com.ilegra.okr.model.request.KeyResultRequestModel;
import com.ilegra.okr.model.response.InitiativeResponseModel;
import com.ilegra.okr.model.response.KeyResultResponseModel;
import com.ilegra.okr.model.response.KeyResultUpdateHistoryResponseModel;
import com.ilegra.okr.service.InitiativeService;
import com.ilegra.okr.service.KeyResultService;
import com.ilegra.okr.service.KeyResultUpdateHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/key-results")
public class KeyResultApi {

	@Value("${AppSecret}")
	private String correctAppSecret;

	@Autowired
	private KeyResultService keyResultService;

	@Autowired
	private InitiativeService initiativeService;

	@Autowired
	private KeyResultUpdateHistoryService keyResultUpdateHistoryService;

	@Autowired
	private ModelMapper mapper;

	@PostMapping
	public ResponseEntity<KeyResultResponseModel> insert(@RequestBody KeyResultRequestModel model,
														 @RequestHeader String appSecret) {

		if (appSecret == null || !appSecret.equals(correctAppSecret))
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);

		var dto = this.keyResultService.insert(mapper.map(model, KeyResultDto.class));
		var response = mapper.map(dto, KeyResultResponseModel.class);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<KeyResultResponseModel> update(@RequestBody KeyResultRequestModel model,
														 @PathVariable("id") Integer id,
														 @RequestHeader String appSecret) {

		if (appSecret == null || !appSecret.equals(correctAppSecret))
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);

		var dto = this.keyResultService.update(mapper.map(model, KeyResultDto.class), id);
		var response = mapper.map(dto, KeyResultResponseModel.class);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id,
									   @RequestHeader String appSecret) {

		if (appSecret == null || !appSecret.equals(correctAppSecret))
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);

		this.keyResultService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<KeyResultResponseModel> getById(@PathVariable("id") Integer id,
														  @RequestHeader String appSecret) {

		if (appSecret == null || !appSecret.equals(correctAppSecret))
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);

		var response = mapper.map(this.keyResultService.getById(id), KeyResultResponseModel.class);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<KeyResultResponseModel>> getAll(@RequestHeader String appSecret) {

		if (appSecret == null || !appSecret.equals(correctAppSecret))
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);

		var response = this.keyResultService
				.getAll()
				.stream()
				.map(dto -> mapper.map(dto, KeyResultResponseModel.class))
				.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}/initiatives")
	public ResponseEntity<List<InitiativeResponseModel>> getAllInitiativesById(@PathVariable("id") Integer id,
																			   @RequestHeader String appSecret) {

		if (appSecret == null || !appSecret.equals(correctAppSecret))
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);

		var response = this.initiativeService
				.getAllByKeyResultId(id)
				.stream()
				.map(dto -> mapper.map(dto, InitiativeResponseModel.class))
				.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}/history")
	public ResponseEntity<List<KeyResultUpdateHistoryResponseModel>> getKeyResultHistory(@PathVariable("id") Integer id,
																						 @RequestHeader String appSecret) {

		if (appSecret == null || !appSecret.equals(correctAppSecret))
			return new ResponseEntity(HttpStatus.UNAUTHORIZED);

		var response = this.keyResultUpdateHistoryService
				.getAllByKeyResultId(id)
				.stream()
				.map(dto -> mapper.map(dto, KeyResultUpdateHistoryResponseModel.class))
				.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
