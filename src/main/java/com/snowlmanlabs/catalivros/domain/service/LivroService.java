package com.snowlmanlabs.catalivros.domain.service;

import com.snowlmanlabs.catalivros.domain.model.Livro;
import com.snowlmanlabs.catalivros.domain.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Page<Livro> listarLivros(Pageable pageable) {
        return livroRepository.findAll(pageable);
    }

    public Livro consultarLivro(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Livro nao encontrado"));
    }

    @Transactional
    public Livro criarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    @Transactional
    public Livro atualizarLivro(Long id, Livro livroAtualizado) {
        Livro livro = consultarLivro(id);
        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());
        return livroRepository.save(livro);
    }

    @Transactional
    public void removerLivro(Long id) {
        Livro livro = consultarLivro(id);
        livroRepository.delete(livro);
    }
}
