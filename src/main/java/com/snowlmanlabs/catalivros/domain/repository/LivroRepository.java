package com.snowlmanlabs.catalivros.domain.repository;

import com.snowlmanlabs.catalivros.domain.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
