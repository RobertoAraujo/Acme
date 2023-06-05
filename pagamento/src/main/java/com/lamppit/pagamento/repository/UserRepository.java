package com.lamppit.pagamento.repository;

import java.util.Optional;

import com.lamppit.pagamento.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String email);

}
