package com.ilegra.okr.api;


import com.ilegra.okr.dto.CycleDto;
import com.ilegra.okr.dto.ObjectiveDto;
import com.ilegra.okr.model.CycleModel;
import com.ilegra.okr.model.ErrorMessageModel;
import com.ilegra.okr.model.ObjectiveModel;
import com.ilegra.okr.service.CycleService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/v1/cycle")
public class CycleApi {

  @Autowired
  private CycleService service;

  @Autowired
  private ObjectiveService objectiveService;

  @Autowired
  private ModelMapper mapper;

  @PostMapping
  public ResponseEntity<CycleModel> save(@RequestBody CycleModel model) {
    return new ResponseEntity<>(mapper.map(this.service.save(model), CycleModel.class),
        HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CycleModel> update(
      @RequestBody CycleModel model,
      @PathVariable("id") Integer id) {
    return new ResponseEntity<>(mapper.map(this.service.update(model, id), CycleModel.class),
        HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    this.service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CycleModel> getById(@PathVariable("id") Integer id) {
    CycleDto dto = this.service.getById(id);

    return new ResponseEntity<>(mapper.map(dto, CycleModel.class),
        HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<CycleModel>> getAll() {

    List<CycleDto> cycles = this.service.getAll();

    if (cycles.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(cycles.stream()
        .map(dto -> mapper.map(dto, CycleModel.class))
        .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  @GetMapping("/{id}/objectives")
  public ResponseEntity<List<ObjectiveModel>> getAllObjectivesById(@PathVariable("id") Integer id) {

    List<ObjectiveDto> objectives = this.objectiveService.getAllByCycleId(id);

    if (objectives.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(objectives.stream()
        .map(dto -> mapper.map(dto, ObjectiveModel.class))
        .collect(Collectors.toList()),
        HttpStatus.OK);
  }
}
