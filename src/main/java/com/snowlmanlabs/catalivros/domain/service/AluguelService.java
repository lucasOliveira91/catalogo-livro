package com.snowlmanlabs.catalivros.domain.service;

import com.snowlmanlabs.catalivros.domain.model.Livro;
import com.snowlmanlabs.catalivros.domain.model.Usuario;
import com.snowlmanlabs.catalivros.domain.repository.LivroRepository;
import com.snowlmanlabs.catalivros.domain.repository.UserRepository;
import com.snowlmanlabs.catalivros.infrastructure.EmailService;
import com.snowlmanlabs.catalivros.infrastructure.MensageriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class AluguelService {

    private final LivroRepository livroRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final MensageriaService mensageriaService;

    @Autowired
    public AluguelService(LivroRepository livroRepository, UserRepository userRepository, EmailService emailService, MensageriaService mensageriaService) {
        this.livroRepository = livroRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.mensageriaService = mensageriaService;
    }

    @Transactional
    public void alugarLivro(Long livroId) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Usuario entity = new Usuario(null, "default@teste.com.br", authentication.getPrincipal().toString());
        Usuario usuario = userRepository.save(entity);

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
