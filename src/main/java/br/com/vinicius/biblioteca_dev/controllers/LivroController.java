package br.com.vinicius.biblioteca_dev.controllers;

import br.com.vinicius.biblioteca_dev.entities.Livro;
import br.com.vinicius.biblioteca_dev.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro){
        Livro novoLivro = livroService.salvar(livro);
        return ResponseEntity.ok(novoLivro);
    }

    @GetMapping("/{titulo}")
    public ResponseEntity<List<Livro>> listar(@PathVariable String titulo){
        List<Livro> livros = livroService.buscarLivroPorTitulo(titulo);
        return ResponseEntity.ok(livros);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<Livro>> listarDisponiveis() {
        List<Livro> disponiveis = livroService.listarDisponiveis();
        return ResponseEntity.ok(disponiveis);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Long id){
        Livro livro = livroService.buscarLivroPorId(id);
        return ResponseEntity.ok(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livro){
        Livro livroAtualizado = livroService.atualizarLivroPorId(id, livro);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        livroService.deletarLivroPorId(id);
        return ResponseEntity.ok().build();
    }
}
