package com.lamppit.pedido.repository;

import com.lamppit.pedido.constants.PedidoStatus;
import com.lamppit.pedido.model.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update PedidoEntity p set p.status = :status where p = :pedido")
    void atualizaStatus(PedidoStatus status, PedidoEntity pedido);

    @Query(value = "SELECT p from PedidoEntity p LEFT JOIN FETCH p.itens where p.id = :id")
    PedidoEntity porIdComItens(Long id);


}
