package com.ilegra.okr.api;


import com.ilegra.okr.dto.ObjectiveDto;
import com.ilegra.okr.model.request.ObjectiveRequestModel;
import com.ilegra.okr.model.response.KeyResultResponseModel;
import com.ilegra.okr.model.response.ObjectiveResponseModel;
import com.ilegra.okr.service.KeyResultService;
import com.ilegra.okr.service.ObjectiveService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/objectives")
public class ObjectiveApi {

    @Value("${AppSecret}")
    private String correctAppSecret;

    @Autowired
    private ObjectiveService objectiveService;

    @Autowired
    private KeyResultService keyResultService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<ObjectiveResponseModel> save(@RequestBody ObjectiveRequestModel model,
                                                       @RequestHeader String appSecret) {

        if (appSecret == null || !appSecret.equals(correctAppSecret))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        var dto = this.objectiveService.insert(mapper.map(model, ObjectiveDto.class));

        var response = mapper.map(this.objectiveService.getById(dto.getId()), ObjectiveResponseModel.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjectiveResponseModel> update(@RequestBody ObjectiveRequestModel model,
                                                         @PathVariable("id") Integer id,
                                                         @RequestHeader String appSecret) {

        if (appSecret == null || !appSecret.equals(correctAppSecret))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        var dto = this.objectiveService.update(mapper.map(model, ObjectiveDto.class), id);
        var response = mapper.map(dto, ObjectiveResponseModel.class);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id,
                                       @RequestHeader String appSecret) {

        if (appSecret == null || !appSecret.equals(correctAppSecret))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        this.objectiveService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjectiveResponseModel> getById(@PathVariable("id") Integer id,
                                                          @RequestHeader String appSecret) {

        if (appSecret == null || !appSecret.equals(correctAppSecret))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        var response = mapper.map(this.objectiveService.getById(id), ObjectiveResponseModel.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ObjectiveResponseModel>> getAll(@RequestHeader String appSecret) {

        if (appSecret == null || !appSecret.equals(correctAppSecret))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        var response = this.objectiveService
                .getAll()
                .stream()
                .map(dto -> mapper.map(dto, ObjectiveResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/key-results")
    public ResponseEntity<List<KeyResultResponseModel>> getAllKeyResultsById(@PathVariable("id") Integer id,
                                                                             @RequestHeader String appSecret) {

        if (appSecret == null || !appSecret.equals(correctAppSecret))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        var response = this.keyResultService
                .getAllByObjectiveId(id)
                .stream()
                .map(dto -> mapper.map(dto, KeyResultResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{objectiveFatherId}/objectives")
    public ResponseEntity<List<ObjectiveResponseModel>> getAllObjectivesByObjectiveFatherId(
            @PathVariable("objectiveFatherId") Integer objectiveFatherId,
            @RequestHeader String appSecret) {

        if (appSecret == null || !appSecret.equals(correctAppSecret))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        var response = this.objectiveService
                .getAllByObjectiveFatherId(objectiveFatherId)
                .stream()
                .map(dto -> mapper.map(dto, ObjectiveResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
