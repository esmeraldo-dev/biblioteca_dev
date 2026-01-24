package br.com.vinicius.biblioteca_dev.controllers;

import br.com.vinicius.biblioteca_dev.entities.Emprestimo;
import br.com.vinicius.biblioteca_dev.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<Emprestimo> emprestarLivro(@RequestBody Emprestimo emprestimo) {
        Emprestimo novoEmprestimo = emprestimoService.salvarEmprestimo(emprestimo);
        return ResponseEntity.ok(novoEmprestimo);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listarTodosOsEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoService.listarTodosOsEmprestimos();
        if (emprestimos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(emprestimos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarEmprestimoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.buscarEmprestimoPorId(id));
    }
}
