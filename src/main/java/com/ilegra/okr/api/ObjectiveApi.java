package com.ilegra.okr.api;


import com.ilegra.okr.dto.ObjectiveDto;
import com.ilegra.okr.dto.ObjectiveFilterDto;
import com.ilegra.okr.model.ErrorMessageModel;
import com.ilegra.okr.model.ObjectiveModel;
import com.ilegra.okr.service.ObjectiveService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/objective")
public class ObjectiveApi {

  @Autowired
  private ObjectiveService service;

  @Autowired
  private ModelMapper mapper;

  @PostMapping
  public ResponseEntity<ObjectiveModel> save(@RequestBody ObjectiveModel model) {
    return new ResponseEntity<>(mapper.map(this.service.save(model), ObjectiveModel.class),
        HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ObjectiveModel> update(
      @RequestBody ObjectiveModel model,
      @PathVariable("id") Integer id) {
    return new ResponseEntity<>(mapper.map(this.service.update(model, id), ObjectiveModel.class),
        HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    this.service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ObjectiveModel> getById(@PathVariable("id") Integer id) {
    ObjectiveDto dto = this.service.getById(id);

    return new ResponseEntity<>(mapper.map(dto, ObjectiveModel.class),
        HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<ObjectiveModel>> getAll(ObjectiveFilterDto filterDto) {

    List<ObjectiveDto> keyResults = this.service.getAll(filterDto);

    if (keyResults.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(keyResults.stream()
        .map(dto -> mapper.map(dto, ObjectiveModel.class))
        .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  @ExceptionHandler(value = {IllegalArgumentException.class})
  public ResponseEntity<ErrorMessageModel> validateNotFound(IllegalArgumentException ex) {

    ErrorMessageModel errorMessageModel = new ErrorMessageModel();
    errorMessageModel.setCode(HttpStatus.NO_CONTENT);
    errorMessageModel.setMessage(ex.getMessage());

    return new ResponseEntity<>(errorMessageModel, HttpStatus.NO_CONTENT);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<String> validateInternalServerError() {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

}