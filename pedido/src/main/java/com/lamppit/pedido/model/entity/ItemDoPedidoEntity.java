package com.lamppit.pedido.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "item_do_pedido")
@Data
public class ItemDoPedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private Integer quantidade;

    private String descricao;

    @ManyToOne(optional = false)
    private PedidoEntity pedido;

}
