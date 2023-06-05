package com.lamppit.pagamento.controller;

import com.lamppit.pagamento.model.dto.PagamentoDTO;
import com.lamppit.pagamento.service.PagamentoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping("/v1/pagamentos")
public class PagamentoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoController.class);

    @Autowired
    private PagamentoService service;

    @GetMapping(path = "list")
    public ResponseEntity<Page<PagamentoDTO>> obterTodosOsPagamento(@PageableDefault(size = 10) Pageable pageable) {
        Page<PagamentoDTO> pagamentoDTOS = service.obterTodos(pageable);
        return ResponseEntity.ok().body(pagamentoDTOS);
    }

    @GetMapping(path = "/list/{id}")
    public ResponseEntity<PagamentoDTO> detalhar(@PathVariable @NotNull Long id) {
        PagamentoDTO pagamentoDTOId = service.obterPorId(id);
        return ResponseEntity.ok().body(pagamentoDTOId);
    }

    @PostMapping(path = "/insert")
    public ResponseEntity<PagamentoDTO> insert(
            @RequestBody @Valid PagamentoDTO dto, UriComponentsBuilder uriComponentsBuilder) {
        PagamentoDTO pagamento = service.insertPagamento(dto);
        URI endreco = uriComponentsBuilder.path("pagamento/{id}").buildAndExpand(pagamento.getId()).toUri();

        return ResponseEntity.created(endreco).body(pagamento);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<PagamentoDTO> updatePagamentoId(
            @PathVariable @NotNull Long id, @RequestBody @Valid PagamentoDTO dto) {
        PagamentoDTO pagamento = service.updatePagamento(id, dto);
        return ResponseEntity.ok().body(pagamento);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity deletePagamentoId(@PathVariable Long id) {
        service.deletPagamento(id);
        LOGGER.info("Pagamento deletado com sucesso! " + id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/confirmar")
    @CircuitBreaker(name = "atualizaPedido", fallbackMethod = "pagamentoAutorizadoComIntegracaoPendente")
    public void confirmarPagamento(@PathVariable @NotNull Long id) {
        service.confirmarPagamento(id);
    }

    public void pagamentoAutorizadoComIntegracaoPendente(Long id, Exception e) {
        service.alteraStatus(id);
    }
}
