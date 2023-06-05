package com.lamppit.vitrine.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cep;
    private String bairro;
    private String rua;
    private String uf;
    private String cidade;

    @OneToOne(mappedBy = "endereco", optional = false)
    private VitrineEntity vitrineId;
}
