package com.lamppit.vitrine.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pessoa")
public class PessoaEntity {
    @ApiModelProperty(value = "Código da pessoa")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ApiModelProperty(value = "Nome da pessoa")
    @Column(nullable = false)
    private String nome;
}
