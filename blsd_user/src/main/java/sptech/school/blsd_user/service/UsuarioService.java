package sptech.school.blsd_user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sptech.school.blsd_user.dto.UsuarioRequest;
import sptech.school.blsd_user.entity.Usuario;
import sptech.school.blsd_user.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listar() {
        return repository.findAll();
    }
    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Usuario criar(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        return repository.save(usuario);
    }

    public Usuario atualizar(Long id, UsuarioRequest request) {

        Usuario usuario = repository.findById(id).orElse(null);

        if (usuario != null) {
            usuario.setNome(request.nome());
            usuario.setEmail(request.email());
            usuario.setSenha(request.senha());
            return repository.save(usuario);
        }
        return null;
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}