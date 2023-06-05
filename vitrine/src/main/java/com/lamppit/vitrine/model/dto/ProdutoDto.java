package com.lamppit.vitrine.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto {
    private Long id;
    @NotNull
    private String nome;
    @NotBlank
    private String descricao;
    @Positive
    private BigDecimal valor;
    private Long idVitrine;
}
