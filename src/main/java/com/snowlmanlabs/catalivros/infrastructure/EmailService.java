package com.snowlmanlabs.catalivros.infrastructure;


import com.snowlmanlabs.catalivros.domain.model.Livro;
import com.snowlmanlabs.catalivros.domain.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void enviarConfirmacaoAluguel(Usuario usuario, Livro livro) {

        System.out.println("Enviando email para " + usuario.getEmail() +
                " confirmando o aluguel do livro: " + livro.getTitulo());
    }
}
