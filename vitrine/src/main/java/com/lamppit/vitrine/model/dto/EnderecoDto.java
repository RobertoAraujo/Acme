package com.lamppit.vitrine.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class EnderecoDto {

    private Long id;
    @NotNull(message = "Não pode ser null")
    @Pattern(regexp = "\\d{5}-\\d{3}")
    private String cep;
    @NotBlank
    private String bairro;
    @NotBlank
    private String rua;
    @NotBlank
    private String uf;
    @NotBlank
    private String cidade;
}
