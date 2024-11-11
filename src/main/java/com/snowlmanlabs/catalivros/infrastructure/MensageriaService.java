package com.snowlmanlabs.catalivros.infrastructure;

import com.snowlmanlabs.catalivros.domain.model.Livro;
import com.snowlmanlabs.catalivros.domain.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class MensageriaService {

    public void notificarAluguel(Livro livro, Usuario usuario) {
        System.out.println("Notificando aluguel do livro: " + livro.getTitulo() +
                " para o usuario: " + usuario.getNome());
    }
}
