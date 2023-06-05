package com.lamppit.pagamento.service;

import com.lamppit.pagamento.constants.StatusPagamento;
import com.lamppit.pagamento.model.dto.PagamentoDTO;
import com.lamppit.pagamento.model.entity.PagamentoEntity;
import com.lamppit.pagamento.model.http.PedidoClient;
import com.lamppit.pagamento.repository.PagamentoRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PagamentoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoService.class);

    @Autowired
    private PagamentoRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    private PedidoClient pedido;

    public Page<PagamentoDTO> obterTodos(Pageable pageable) {
        LOGGER.info("Listando todos os pagamentos");
        return repository.findAll(pageable)
                .map(p -> modelMapper.map(p, PagamentoDTO.class));
    }

    public PagamentoDTO obterPorId(Long id) {
        LOGGER.info("iniciado o processo de deletagem com id " + id);
        PagamentoEntity pagamento = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    public PagamentoDTO insertPagamento(PagamentoDTO dto) {
        PagamentoEntity pagamento = modelMapper.map(dto, PagamentoEntity.class);
        pagamento.setStatus(StatusPagamento.CRIADO);
        LOGGER.info("mudei o satus para criado" + pagamento.getStatus());
        repository.save(pagamento);
        LOGGER.info("Salvei no banco com o id" + pagamento.getId());

        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    public PagamentoDTO updatePagamento(Long id, PagamentoDTO dto) {
        PagamentoEntity pagamento = modelMapper.map(dto, PagamentoEntity.class);
        pagamento.setId(id);
        LOGGER.info("Update em pagamento com id" + pagamento.getId());
        repository.save(pagamento);

        return modelMapper.map(pagamento, PagamentoDTO.class);
    }

    public void deletPagamento(Long id) {
        LOGGER.info("deletei o pagamento com id" + id);
        repository.deleteById(id);
    }

    public void confirmarPagamento(Long id) {
        Optional<PagamentoEntity> pagamento = repository.findById(id);

        if (!pagamento.isPresent()) {
            throw new EntityNotFoundException();
        }

        pagamento.get().setStatus(StatusPagamento.CONFIRMADO);
        repository.save(pagamento.get());
        pedido.atualizaPagamento(pagamento.get().getPedidoId());
    }

    public void alteraStatus(Long id) {
        Optional<PagamentoEntity> pagamento = repository.findById(id);

        if (!pagamento.isPresent()) {
            throw new EntityNotFoundException();
        }

        pagamento.get().setStatus(StatusPagamento.CONFIRMADO_SEM_INTEGRACAO);
        repository.save(pagamento.get());

    }
}
