package br.com.vinicius.biblioteca_dev.services;

import br.com.vinicius.biblioteca_dev.dto.UsuarioResponseDTO;
import br.com.vinicius.biblioteca_dev.entities.Usuario;
import br.com.vinicius.biblioteca_dev.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvarUsuario(Usuario usuario) {
        usuario.setDataDoCadastro(LocalDate.now());
        usuarioRepository.saveAndFlush(usuario);
    }

    public List<UsuarioResponseDTO> listarTodosOsUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(u -> new UsuarioResponseDTO(u.getId(), u.getNome(), u.getEmail()))
                .toList();
    }

    public UsuarioResponseDTO buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    public void deletarUsuarioPorId(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
    public Usuario atualizarUsuarioPorId(Long id, Usuario usuario) {
        Usuario usuarioEntity = usuarioRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado")
        );
        Usuario usuarioAtualizado = Usuario.builder()
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())
                .telefone(usuario.getTelefone() != null ? usuario.getTelefone() : usuarioEntity.getTelefone())
                .id(usuarioEntity.getId())
                .dataDoCadastro(usuarioEntity.getDataDoCadastro())
                .build();
        return usuarioRepository.saveAndFlush(usuarioAtualizado);
    }
}
