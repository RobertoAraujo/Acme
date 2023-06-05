package com.lamppit.vitrine.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vitrine")
public class VitrineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vitrine_id")
    private Long id;
    private String nomeLoja;
    private String cnpj;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id_fk")
    private EnderecoEntity endereco;

    @OneToOne(mappedBy = "vitrine", cascade = CascadeType.ALL)
    private ProdutoEntity produto;

}
