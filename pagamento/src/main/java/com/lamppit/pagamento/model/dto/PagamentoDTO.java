package com.lamppit.pagamento.model.dto;


import com.lamppit.pagamento.constants.StatusPagamento;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PagamentoDTO {
    private Long id;
    private BigDecimal valor;
    private String nome;
    private String numero;
    private String expiracao;
    private String codigo;
    private StatusPagamento status;
    private Long formaDePagamentoId;
    private Long pedidoId;
}
