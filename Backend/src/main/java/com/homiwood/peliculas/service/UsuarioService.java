package com.homiwood.peliculas.service;

import com.homiwood.peliculas.model.Usuario;
import com.homiwood.peliculas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import com.homiwood.peliculas.exception.DuplicateResourceException;
import com.homiwood.peliculas.exception.NotFoundException;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario crearUsuario(Usuario usuario) {

        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new DuplicateResourceException("El username ya está registrado");
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new DuplicateResourceException("El email ya está registrado");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}