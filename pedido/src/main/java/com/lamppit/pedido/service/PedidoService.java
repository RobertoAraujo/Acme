package com.lamppit.pedido.service;

import com.lamppit.pedido.constants.PedidoStatus;
import com.lamppit.pedido.model.dto.PedidoDto;
import com.lamppit.pedido.model.dto.StatusDto;
import com.lamppit.pedido.model.entity.PedidoEntity;
import com.lamppit.pedido.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RelationNotFoundException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    private final ModelMapper modelMapper;

    public List<PedidoDto> obterTodos() throws RelationNotFoundException {
        List<PedidoDto> collect = repository.findAll().stream()
                .map(p -> modelMapper.map(p, PedidoDto.class))
                .collect(Collectors.toList());
        if (collect.isEmpty()) {
            return repository.findAll().stream()
                    .map(p -> modelMapper.map(p, PedidoDto.class))
                    .collect(Collectors.toList());
        }else{
            throw new RelationNotFoundException("não existe produtos");
        }
    }

    public PedidoDto obterPorId(Long id) {
        PedidoEntity pedido = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if (pedido.getItens().isEmpty()){
            return modelMapper.map(pedido, PedidoDto.class);
        }else {
         throw new EntityNotFoundException(" O id que vc esta procurando não existe na base !");
        }
    }

    public PedidoDto criarPedido(PedidoDto dto) {
        PedidoEntity pedido = modelMapper.map(dto, PedidoEntity.class);
        if (pedido.getItens().isEmpty()){
            pedido.setDataHora(LocalDateTime.now());
            pedido.setStatus(PedidoStatus.REALIZADO);
            pedido.getItens().forEach(item -> item.setPedido(pedido));
            PedidoEntity salvo = repository.save(pedido);
            return modelMapper.map(pedido, PedidoDto.class);

        }else {
            throw new EntityNotFoundException("Sem produtos pra ser enserido!");
        }

    }

    public PedidoDto atualizaStatus(Long id, StatusDto dto) {

        PedidoEntity pedido = repository.porIdComItens(id);

        if (pedido == null) {
            throw new EntityNotFoundException();
        }

        pedido.setStatus(dto.getStatus());
        repository.atualizaStatus(dto.getStatus(), pedido);
        return modelMapper.map(pedido, PedidoDto.class);
    }

    public void aprovaPagamentoPedido(Long id) {

        PedidoEntity pedido = repository.porIdComItens(id);

        if (pedido == null) {
            throw new EntityNotFoundException();
        }

        pedido.setStatus(PedidoStatus.PAGO);
        repository.atualizaStatus(PedidoStatus.PAGO, pedido);
    }
}
