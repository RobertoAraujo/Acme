package com.lamppit.vitrine.service;

import com.lamppit.vitrine.model.dto.ProdutoDto;
import com.lamppit.vitrine.model.dto.VitrineDto;
import com.lamppit.vitrine.model.entity.ProdutoEntity;
import com.lamppit.vitrine.model.entity.VitrineEntity;
import com.lamppit.vitrine.repository.ProdutoRepository;
import com.lamppit.vitrine.repository.VitrineRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private VitrineRepository vitrineRepository;

    public ProdutoDto insertProduto(ProdutoDto dto) {
        VitrineEntity vitrine = vitrineRepository.findById(dto.getIdVitrine()).get();
        if (dto != null && vitrine != null) {
            ProdutoEntity produto = new ProdutoEntity();
            BeanUtils.copyProperties(dto, produto);

            produto.setVitrine(vitrine);

            repository.saveAndFlush(produto);
            BeanUtils.copyProperties(produto, dto);
        }else {
            throw new EntityNotFoundException("não pode inser o produto sem um loja");
        }
        return dto;
    }
}
