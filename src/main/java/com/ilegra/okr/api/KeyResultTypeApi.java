package com.ilegra.okr.api;


import com.ilegra.okr.model.response.KeyResultTypeResponseModel;
import com.ilegra.okr.service.KeyResultTypeService;
import com.ilegra.okr.service.KeyResultUpdateHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/key-result-types")
public class KeyResultTypeApi {

    @Value("${AppSecret}")
    private String correctAppSecret;

    @Autowired
    private KeyResultTypeService keyResultTypeService;

    @Autowired
    private KeyResultUpdateHistoryService keyResultUpdateHistoryService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<KeyResultTypeResponseModel>> getAll(@RequestHeader String appSecret) {

        if (appSecret == null || !appSecret.equals(correctAppSecret))
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        
        var response = this.keyResultTypeService
                .getAll()
                .stream()
                .map(dto -> mapper.map(dto, KeyResultTypeResponseModel.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
