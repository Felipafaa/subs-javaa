package com.feliperosa.catalogo_livros.controller;


import com.feliperosa.catalogo_livros.entity.Livro;
import com.feliperosa.catalogo_livros.repository.LivroRepository;
import com.feliperosa.catalogo_livros.specification.LivroSpecification;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository repository;

    public LivroController(LivroRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Livro> criar(@Valid @RequestBody Livro livro) {
        return ResponseEntity.ok(repository.save(livro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @Valid @RequestBody Livro livroAtualizado) {
        return repository.findById(id)
                .map(livro -> {
                    livro.setTitulo(livroAtualizado.getTitulo());
                    livro.setAutor(livroAtualizado.getAutor());
                    livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
                    livro.setIsbn(livroAtualizado.getIsbn());
                    return ResponseEntity.ok(repository.save(livro));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public Page<Livro> listar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) Integer anoInicial,
            @RequestParam(required = false) Integer anoFinal,
            Pageable pageable
    ) {
        Specification<Livro> spec = Specification
                .where(LivroSpecification.tituloContains(titulo))
                .and(LivroSpecification.autorContains(autor))
                .and(LivroSpecification.anoPublicacaoBetween(anoInicial, anoFinal));

        return repository.findAll(spec, pageable);
    }
}
