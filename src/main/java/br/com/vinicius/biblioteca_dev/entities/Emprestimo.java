package br.com.vinicius.biblioteca_dev.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Entity
@Table(name = "tb_emprestimos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private String nomeCliente;
}
