package com.ilegra.okr.api;


import com.ilegra.okr.model.InitiativeModel;
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
@RequestMapping("/v1/initiative")
public class InitiativeApi {

  @Autowired
  private InitiativeService service;

  @Autowired
  private ModelMapper mapper;

  @PostMapping
  public ResponseEntity<InitiativeModel> save(@RequestBody InitiativeModel model) {
    return new ResponseEntity<>(mapper.map(this.service.save(model), InitiativeModel.class),
        HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<InitiativeModel> update(
      @RequestBody InitiativeModel model,
      @PathVariable("id") Integer id) {
    return new ResponseEntity<>(mapper.map(this.service.update(model, id), InitiativeModel.class),
        HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    this.service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<InitiativeModel> getById(@PathVariable("id") Integer id) {
    return new ResponseEntity<>(mapper.map(this.service.getById(id), InitiativeModel.class),
        HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<InitiativeModel>> getAll() {
    return new ResponseEntity<>(this.service.getAll().stream()
        .map(dto -> mapper.map(dto, InitiativeModel.class))
        .collect(Collectors.toList()),
        HttpStatus.OK);
  }
}
