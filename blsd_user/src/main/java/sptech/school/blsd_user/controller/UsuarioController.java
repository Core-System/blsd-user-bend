package sptech.school.blsd_user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.blsd_user.dto.UsuarioRequest;
import sptech.school.blsd_user.entity.Usuario;
import sptech.school.blsd_user.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {

        List<Usuario> usuarios = service.listar();

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {

        Usuario usuario = service.buscarPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody UsuarioRequest request) {
        if (request.nome() == null || request.nome().isBlank() || request.email() == null || request.email().isBlank() || request.senha() == null || request.senha().isBlank()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = service.criar(new UsuarioRequest(request.nome(), request.email(), request.senha()));
        return ResponseEntity.status(201).body(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody UsuarioRequest request) {

        Usuario usuario = service.atualizar(id, request);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        Usuario usuario = service.buscarPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}