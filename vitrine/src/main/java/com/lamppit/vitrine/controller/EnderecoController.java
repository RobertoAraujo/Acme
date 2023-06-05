package com.lamppit.vitrine.controller;

import com.lamppit.vitrine.model.dto.EnderecoDto;
import com.lamppit.vitrine.model.dto.VitrineDto;
import com.lamppit.vitrine.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/v1/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @PostMapping(path = "/insert")
    public ResponseEntity<EnderecoDto> isertEndreco(
            @RequestBody EnderecoDto dto, UriComponentsBuilder uriBuilder) {
        EnderecoDto createEndereco = service.insertEndereco(dto);
        URI endereco = uriBuilder.path("endereco/{id}").buildAndExpand(createEndereco.getId()).toUri();
        return ResponseEntity.created(endereco).body(createEndereco);
    }
}
