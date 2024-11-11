package com.snowlmanlabs.catalivros.domain.service;

import com.snowlmanlabs.catalivros.domain.model.Livro;
import com.snowlmanlabs.catalivros.domain.model.Usuario;
import com.snowlmanlabs.catalivros.domain.repository.LivroRepository;
import com.snowlmanlabs.catalivros.infrastructure.EmailService;
import com.snowlmanlabs.catalivros.infrastructure.MensageriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class AluguelService {

    private final LivroRepository livroRepository;
    private final EmailService emailService;
    private final MensageriaService mensageriaService;

    @Autowired
    public AluguelService(LivroRepository livroRepository, EmailService emailService, MensageriaService mensageriaService) {
        this.livroRepository = livroRepository;
        this.emailService = emailService;
        this.mensageriaService = mensageriaService;
    }

    @Transactional
    public void alugarLivro(Long livroId, Usuario usuario) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new NoSuchElementException("Livro nao encontrado"));

        if (!livro.isDisponivel()) {
            throw new IllegalStateException("Livro ja alugado");
        }

        livro.setDisponivel(false);
        livroRepository.save(livro);

        mensageriaService.notificarAluguel(livro, usuario);

        emailService.enviarConfirmacaoAluguel(usuario, livro);
    }
}
