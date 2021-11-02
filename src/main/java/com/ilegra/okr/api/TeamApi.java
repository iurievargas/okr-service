package com.ilegra.okr.api;

import com.ilegra.okr.dto.TeamDto;
import com.ilegra.okr.model.request.TeamRequestModel;
import com.ilegra.okr.model.response.TeamResponseModel;
import com.ilegra.okr.service.KeyResultService;
import com.ilegra.okr.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/teams")
public class TeamApi {

    @Value("${AppSecret}")
    private String correctAppSecret;

    @Autowired
    private TeamService teamService;

    @Autowired
    private KeyResultService keyResultService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    public ResponseEntity<TeamResponseModel> save(@RequestBody TeamRequestModel model,
                                                  @RequestHeader String appSecret) {

        if (appSecret == null || !appSecret.equals(correctAppSecret))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        var dto = this.teamService.insert(mapper.map(model, TeamDto.class));
        var response = mapper.map(dto, TeamResponseModel.class);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamResponseModel> update(@RequestBody TeamRequestModel model,
                                                    @PathVariable("id") Integer id,
                                                    @RequestHeader String appSecret) {

        if (appSecret == null || !appSecret.equals(correctAppSecret))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        var dto = this.teamService.update(mapper.map(model, TeamDto.class), id);
        var response = mapper.map(dto, TeamResponseModel.class);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id,
                                       @RequestHeader String appSecret) {

        if (appSecret == null || !appSecret.equals(correctAppSecret))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        this.teamService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseModel> getById(@PathVariable("id") Integer id,
                                                     @RequestHeader String appSecret) {

        if (appSecret == null || !appSecret.equals(correctAppSecret))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);

        var response = mapper.map(this.teamService.getById(id), TeamResponseModel.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TeamResponseModel>> getAll(@RequestHeader String appSecret) {

        if (appSecret == null || !appSecret.equals(correctAppSecret))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        
        var response = this.teamService
                .getAll()
                .stream()
                .map(entity -> mapper.map(entity, TeamResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
