package br.com.vinicius.biblioteca_dev.controllers;

import br.com.vinicius.biblioteca_dev.dto.EmprestimoRequestDTO;
import br.com.vinicius.biblioteca_dev.dto.EmprestimoResponseDTO;
import br.com.vinicius.biblioteca_dev.entities.Emprestimo;
import br.com.vinicius.biblioteca_dev.services.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<EmprestimoResponseDTO> emprestarLivro(@RequestBody EmprestimoRequestDTO dto) {
        return ResponseEntity.ok(emprestimoService.salvarEmprestimo(dto));
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoResponseDTO>> listarTodosOsEmprestimos() {
        List<EmprestimoResponseDTO> emprestimos = emprestimoService.listarTodosOsEmprestimos();
        if (emprestimos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(emprestimos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDTO> buscarEmprestimoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.buscarEmprestimoPorId(id));
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Emprestimo> devolverLivro(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.devolverEmprestimoPorId(id));
    }
}
