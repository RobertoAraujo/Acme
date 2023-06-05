package com.lamppit.vitrine.controller;

import com.lamppit.vitrine.model.dto.ProdutoDto;
import com.lamppit.vitrine.model.dto.VitrineDto;
import com.lamppit.vitrine.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/v1/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping(path = "/insert")
    public ResponseEntity<ProdutoDto> isertProduto(
            @RequestBody ProdutoDto dto, UriComponentsBuilder uriBuilder) {
        System.out.println(dto);
        ProdutoDto createVitrine = service.insertProduto(dto);
        URI vitrine = uriBuilder.path("vitrine/{id}").buildAndExpand(createVitrine.getId()).toUri();

        return ResponseEntity.created(vitrine).body(createVitrine);
    }
}
