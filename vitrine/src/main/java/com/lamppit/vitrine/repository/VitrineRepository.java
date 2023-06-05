package com.lamppit.vitrine.repository;

import com.lamppit.vitrine.model.entity.VitrineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VitrineRepository extends JpaRepository<VitrineEntity, Long> {

}
