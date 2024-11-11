package com.snowlmanlabs.catalivros.application.controller;

import com.snowlmanlabs.catalivros.domain.model.Livro;
import com.snowlmanlabs.catalivros.domain.model.Usuario;
import com.snowlmanlabs.catalivros.domain.service.AluguelService;
import com.snowlmanlabs.catalivros.domain.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/livros")
@Validated
public class LivroController {

    private final LivroService livroService;
    private final AluguelService aluguelService;

    @Autowired
    public LivroController(LivroService livroService, AluguelService aluguelService) {
        this.livroService = livroService;
        this.aluguelService = aluguelService;
    }

    @GetMapping
    public ResponseEntity<Page<Livro>> listarLivros(Pageable pageable) {
        Page<Livro> livros = livroService.listarLivros(pageable);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> consultarLivro(@PathVariable Long id) {
        Livro livro = livroService.consultarLivro(id);
        return ResponseEntity.ok(livro);
    }

    @PostMapping
    public ResponseEntity<Livro> criarLivro(@Valid @RequestBody Livro livro) {
        Livro novoLivro = livroService.criarLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id, @Valid @RequestBody Livro livroAtualizado) {
        Livro livro = livroService.atualizarLivro(id, livroAtualizado);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLivro(@PathVariable Long id) {
        livroService.removerLivro(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/alugar")
    public ResponseEntity<Void> alugarLivro(@PathVariable Long id) {
        aluguelService.alugarLivro(id);
        return ResponseEntity.ok().build();
    }
}
