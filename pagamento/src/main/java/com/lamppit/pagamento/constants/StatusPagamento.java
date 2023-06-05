package com.lamppit.pagamento.constants;

public enum StatusPagamento {
    CRIADO("criado"),
    CONFIRMADO("confirmado"),
    CONFIRMADO_SEM_INTEGRACAO ("confirmado sem integração"),
    CANCELADO("Cancelado");

    private String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }
}
