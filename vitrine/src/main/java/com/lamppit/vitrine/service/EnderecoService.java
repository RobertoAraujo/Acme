package com.lamppit.vitrine.service;

import com.lamppit.vitrine.model.dto.EnderecoDto;
import com.lamppit.vitrine.model.entity.EnderecoEntity;
import com.lamppit.vitrine.model.entity.VitrineEntity;
import com.lamppit.vitrine.repository.EnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;


    public EnderecoDto insertEndereco(EnderecoDto dto) {
        VitrineEntity vitrine = new VitrineEntity();
        EnderecoEntity endereco = new EnderecoEntity();
        BeanUtils.copyProperties(dto, endereco);
        repository.saveAndFlush(endereco);
        BeanUtils.copyProperties(endereco, dto);
        return dto;
    }

    public void insertEndereco(EnderecoEntity enderecoEntity) {
        repository.saveAndFlush(enderecoEntity);
    }

}
