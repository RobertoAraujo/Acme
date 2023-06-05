package com.lamppit.pedido.model.entity;

import com.lamppit.pedido.constants.PedidoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pedido")
    private List<ItemDoPedidoEntity> itens = new ArrayList<>();
}
