package com.lamppit.vitrine.controller;

import com.lamppit.vitrine.model.dto.VitrineDto;
import com.lamppit.vitrine.service.VitrineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/v1/vitrine")
public class VitrineController {

    @Autowired
    private VitrineService service;

    @PostMapping(path = "/insert")
    public ResponseEntity<VitrineDto> isertVitrine(@RequestBody VitrineDto dto, UriComponentsBuilder uriBuilder) {
        VitrineDto createVitrine = service.insertVitrine(dto);
        URI vitrine = uriBuilder.path("vitrine/{id}").buildAndExpand(createVitrine.getId()).toUri();

        return ResponseEntity.created(vitrine).body(createVitrine);
    }

}
