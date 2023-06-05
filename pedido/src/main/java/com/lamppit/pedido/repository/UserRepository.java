package com.lamppit.pedido.repository;

import java.util.Optional;

import com.lamppit.vitrine.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String email);

}
