package com.lamppit.pedido.model.dto;

import com.lamppit.pedido.constants.PedidoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {
    private PedidoStatus status;
}
