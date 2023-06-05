package com.lamppit.vitrine.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class VitrineDto {

    private Long id;

    @NotNull
    private String nomeLoja;
    @NotBlank
    private String cnpj;

    private EnderecoDto endereco;

}
