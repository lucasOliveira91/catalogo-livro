package com.snowlmanlabs.catalivros.domain.repository;

import com.snowlmanlabs.catalivros.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
}
