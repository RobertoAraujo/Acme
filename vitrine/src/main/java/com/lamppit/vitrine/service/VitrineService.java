package com.lamppit.vitrine.service;

import com.lamppit.vitrine.model.dto.VitrineDto;
import com.lamppit.vitrine.model.entity.EnderecoEntity;
import com.lamppit.vitrine.model.entity.VitrineEntity;
import com.lamppit.vitrine.repository.VitrineRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VitrineService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VitrineService.class);

    @Autowired
    private VitrineRepository repository;

    @Autowired
    private EnderecoService enderecoService;

    public VitrineDto insertVitrine(VitrineDto dto) {
        VitrineEntity vitrine = new VitrineEntity();
        EnderecoEntity enderecoEntity = new EnderecoEntity();
        new ModelMapper().map(dto, vitrine);
        VitrineEntity vitrineSalva = repository.saveAndFlush(vitrine);
        BeanUtils.copyProperties(dto.getEndereco(), enderecoEntity);
        enderecoEntity.setVitrineId(vitrineSalva);
        enderecoService.insertEndereco(enderecoEntity);
        BeanUtils.copyProperties(vitrine, dto);

        return dto;
    }

}
