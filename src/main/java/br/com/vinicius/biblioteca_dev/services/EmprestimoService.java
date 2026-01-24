package br.com.vinicius.biblioteca_dev.services;

import br.com.vinicius.biblioteca_dev.entities.Emprestimo;
import br.com.vinicius.biblioteca_dev.repositories.EmprestimoRepository;
import br.com.vinicius.biblioteca_dev.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    public Emprestimo salvarEmprestimo(Emprestimo emprestimo) {
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucaoPrevista(LocalDate.now().plusDays(7));

        var livro = livroRepository.findById(emprestimo.getLivro().getId()).orElseThrow(
                () -> new RuntimeException("Livro não encontrado")
        );

        if ("EMPRESTADO".equals(livro.getStatus())) {
            throw new RuntimeException("Este livro já está emprestado!");
        }

        Emprestimo salvo = emprestimoRepository.saveAndFlush(emprestimo);
        livro.setStatus("EMPRESTADO");
        livroRepository.saveAndFlush(livro);

        return salvo;
    }

    public List<Emprestimo> listarTodosOsEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo buscarEmprestimoPorId(Long id) {
        return emprestimoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Empréstimo não encontrado")
        );
    }
}
