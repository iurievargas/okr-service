package com.ilegra.okr.api;


import com.ilegra.okr.dto.KeyResultDto;
import com.ilegra.okr.dto.TeamDto;
import com.ilegra.okr.model.KeyResultModel;
import com.ilegra.okr.model.TeamModel;
import com.ilegra.okr.service.KeyResultService;
import com.ilegra.okr.service.TeamService;
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
@RequestMapping("/v1/teams")
public class TeamApi {

  @Autowired
  private TeamService service;

  @Autowired
  private KeyResultService keyResultService;

  @Autowired
  private ModelMapper mapper;

  @PostMapping
  public ResponseEntity<TeamModel> save(@RequestBody TeamModel model) {
    return new ResponseEntity<>(mapper.map(this.service.save(model), TeamModel.class),
        HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<TeamModel> update(
      @RequestBody TeamModel model,
      @PathVariable("id") Integer id) {
    return new ResponseEntity<>(mapper.map(this.service.update(model, id), TeamModel.class),
        HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    this.service.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/{id}")
  public ResponseEntity<TeamModel> getById(@PathVariable("id") Integer id) {

    TeamDto dto = this.service.getById(id);

    return new ResponseEntity<>(mapper.map(dto, TeamModel.class),
        HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<TeamModel>> getAll() {
    List<TeamDto> teams = this.service.getAll();

    if (teams.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(teams.stream()
        .map(entity -> mapper.map(entity, TeamModel.class))
        .collect(Collectors.toList()),
        HttpStatus.OK);
  }

  @GetMapping("/{id}/key-results")
  public ResponseEntity<List<KeyResultModel>> getAllKeyResultsById(@PathVariable("id") Integer id) {

    List<KeyResultDto> keyResults = this.keyResultService.getAllByObjectiveId(id);

    if (keyResults.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(keyResults.stream()
        .map(dto -> mapper.map(dto, KeyResultModel.class))
        .collect(Collectors.toList()),
        HttpStatus.OK);
  }
}
