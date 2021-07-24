package com.ilegra.okr.api;


import com.ilegra.okr.dto.InitiativeDto;
import com.ilegra.okr.dto.KeyResultDto;
import com.ilegra.okr.model.InitiativeModel;
import com.ilegra.okr.model.KeyResultModel;
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
@RequestMapping("/v1/key-result")
public class KeyResultApi {

  @Autowired
  private KeyResultService service;

  @Autowired
  private InitiativeService initiativeService;

  @Autowired
  private ModelMapper mapper;

  @PostMapping
  public ResponseEntity<KeyResultModel> save(@RequestBody KeyResultModel model) {
    return new ResponseEntity<>(mapper.map(this.service.save(model), KeyResultModel.class),
        HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<KeyResultModel> update(
      @RequestBody KeyResultModel model,
      @PathVariable("id") Integer id) {
    return new ResponseEntity<>(mapper.map(this.service.update(model, id), KeyResultModel.class),
        HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    this.service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<KeyResultModel> getById(@PathVariable("id") Integer id) {
    KeyResultDto dto = this.service.getById(id);

    return new ResponseEntity<>(mapper.map(dto, KeyResultModel.class),
        HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<KeyResultModel>> getAll() {
    List<KeyResultDto> keyResults = this.service.getAll();

    if (keyResults.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(keyResults.stream()
        .map(dto -> mapper.map(dto, KeyResultModel.class))
        .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  @GetMapping("/{id}/initiatives")
  public ResponseEntity<List<InitiativeModel>> getAllInitiativesById(
      @PathVariable("id") Integer id) {
    List<InitiativeDto> initiatives = this.initiativeService.getAllByKeyResultId(id);

    if (initiatives.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(initiatives.stream()
        .map(dto -> mapper.map(dto, InitiativeModel.class))
        .collect(Collectors.toList()),
        HttpStatus.OK);
  }
}
