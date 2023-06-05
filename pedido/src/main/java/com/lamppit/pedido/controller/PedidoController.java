package com.lamppit.pedido.controller;

import com.lamppit.pedido.model.dto.PedidoDto;
import com.lamppit.pedido.model.dto.StatusDto;
import com.lamppit.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.management.relation.RelationNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping(path = "list")
    public List<PedidoDto> listarTodos() throws RelationNotFoundException {
        return service.obterTodos();
    }

    @GetMapping("pedido/{id}")
    public ResponseEntity<PedidoDto> listarPorId(@PathVariable @NotNull Long id) {
        PedidoDto dto = service.obterPorId(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/insert")
    public ResponseEntity<PedidoDto> realizaPedido(@RequestBody @Valid PedidoDto dto, UriComponentsBuilder uriBuilder) {
        PedidoDto pedidoRealizado = service.criarPedido(dto);

        URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoRealizado.getId()).toUri();

        return ResponseEntity.created(endereco).body(pedidoRealizado);

    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoDto> atualizaStatus(@PathVariable Long id, @RequestBody StatusDto status) {
        PedidoDto dto = service.atualizaStatus(id, status);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/pago")
    public ResponseEntity<Void> aprovaPagamento(@PathVariable @NotNull Long id) {
        service.aprovaPagamentoPedido(id);

        return ResponseEntity.ok().build();
    }
}
