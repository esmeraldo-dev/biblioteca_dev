package br.com.vinicius.biblioteca_dev.services;

import br.com.vinicius.biblioteca_dev.dto.EmprestimoRequestDTO;
import br.com.vinicius.biblioteca_dev.dto.EmprestimoResponseDTO;
import br.com.vinicius.biblioteca_dev.entities.Emprestimo;
import br.com.vinicius.biblioteca_dev.entities.Livro;
import br.com.vinicius.biblioteca_dev.entities.Usuario;
import br.com.vinicius.biblioteca_dev.repositories.EmprestimoRepository;
import br.com.vinicius.biblioteca_dev.repositories.LivroRepository;
import br.com.vinicius.biblioteca_dev.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public EmprestimoResponseDTO salvarEmprestimo(EmprestimoRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Livro livro = livroRepository.findById(dto.livroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (emprestimoRepository.existsByUsuarioIdAndDataDevolucaoPrevistaBeforeAndDataDevolucaoRealIsNull(usuario.getId(), LocalDate.now())) {
            throw new RuntimeException("Usuário possui empréstimos em atraso e não pode realizar novos empréstimos.");
        }

        if (emprestimoRepository.existsByLivroIdAndDataDevolucaoRealIsNull(livro.getId())) {
            throw new RuntimeException("Este livro já está com outro leitor no momento.");
        }

        Emprestimo emprestimo = Emprestimo.builder()
                .usuario(usuario)
                .livro(livro)
                .dataEmprestimo(LocalDate.now())
                .dataDevolucaoPrevista(LocalDate.now().plusDays(7))
                .build();

        Emprestimo salvo = emprestimoRepository.saveAndFlush(emprestimo);
        livro.setStatus("EMPRESTADO");
        livroRepository.saveAndFlush(livro);

        return new EmprestimoResponseDTO(
                salvo.getId(),
                usuario.getNome(),
                livro.getTitulo(),
                salvo.getDataEmprestimo(),
                salvo.getDataDevolucaoPrevista(),
                0.0
        );
    }

    public List<Emprestimo> listarTodosOsEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo buscarEmprestimoPorId(Long id) {
        return emprestimoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Empréstimo não encontrado")
        );
    }

    public Emprestimo devolverEmprestimoPorId(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        if(emprestimo.getDataDevolucaoReal() != null) {
            throw new RuntimeException("Este empréstimo já foi devolvido!");
        }

        LocalDate hoje = LocalDate.now();
        emprestimo.setDataDevolucaoReal(LocalDate.now());

        if (hoje.isAfter(emprestimo.getDataDevolucaoPrevista())) {
            long diasDeAtraso = ChronoUnit.DAYS.between(emprestimo.getDataDevolucaoPrevista(), hoje);
            double valorMulta = diasDeAtraso * 2.0;
            System.out.println("🚨 ALERTA DE ATRASO: Usuário " + emprestimo.getUsuario().getNome() +
                    " atrasou " + diasDeAtraso + " dias. Multa: R$ " + valorMulta);
        } else {
            System.out.println("✅ Devolução no prazo para: " + emprestimo.getUsuario().getNome());
        }

        var livro = emprestimo.getLivro();
        livro.setStatus("DISPONIVEL");
        livroRepository.save(livro);

        return emprestimoRepository.saveAndFlush(emprestimo);
    }
}
