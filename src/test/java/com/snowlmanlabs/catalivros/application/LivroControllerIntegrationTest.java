package com.snowlmanlabs.catalivros.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowlmanlabs.catalivros.domain.model.Livro;
import com.snowlmanlabs.catalivros.domain.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
class LivroControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LivroRepository livroRepository;

    @BeforeEach
    void setUp() {
        livroRepository.deleteAll();
    }

    @Test
    void deveCriarLivroComSucesso() throws Exception {
        Livro livro = new Livro("Java Spring", "Autor Exemplo", true);

        ResultActions result = mockMvc.perform(post("/api/v1/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(livro)));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.titulo", is("Java Spring")))
                .andExpect(jsonPath("$.autor", is("Autor Exemplo")))
                .andExpect(jsonPath("$.disponivel", is(true)));
    }

    @Test
    void deveListarLivros() throws Exception {
        livroRepository.save(new Livro("Java Spring", "Autor Exemplo", true));
        livroRepository.save(new Livro("Kotlin Basics", "Outro Autor", true));

        ResultActions result = mockMvc.perform(get("/api/v1/livros")
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].titulo", is("Java Spring")))
                .andExpect(jsonPath("$.content[1].titulo", is("Kotlin Basics")));
    }

    @Test
    void deveConsultarLivroPorId() throws Exception {
        Livro livroSalvo = livroRepository.save(new Livro("Java Spring", "Autor Exemplo", true));

        ResultActions result = mockMvc.perform(get("/api/v1/livros/{id}", livroSalvo.getId())
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(livroSalvo.getId().intValue())))
                .andExpect(jsonPath("$.titulo", is("Java Spring")))
                .andExpect(jsonPath("$.autor", is("Autor Exemplo")));
    }

    @Test
    void deveAtualizarLivro() throws Exception {
        Livro livroSalvo = livroRepository.save(new Livro( "Java Spring", "Autor Exemplo", true));
        Livro livroAtualizado = new Livro("Java Spring Boot", "Autor Exemplo", true);

        ResultActions result = mockMvc.perform(put("/api/v1/livros/{id}", livroSalvo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(livroAtualizado)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(livroSalvo.getId().intValue())))
                .andExpect(jsonPath("$.titulo", is("Java Spring Boot")))
                .andExpect(jsonPath("$.autor", is("Autor Exemplo")));
    }

    @Test
    void deveRemoverLivro() throws Exception {
        Livro livroSalvo = livroRepository.save(new Livro("Java Spring", "Autor Exemplo", true));

        ResultActions result = mockMvc.perform(delete("/api/v1/livros/{id}", livroSalvo.getId())
                .contentType(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
    }
}
