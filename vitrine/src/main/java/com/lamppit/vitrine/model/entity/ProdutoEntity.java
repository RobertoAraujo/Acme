package com.lamppit.vitrine.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produto")
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal valor;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vitrine_id_fk", nullable = false)
    private VitrineEntity vitrine;


}
