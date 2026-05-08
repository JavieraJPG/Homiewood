package com.homiwood.peliculas.repository;

import com.homiwood.peliculas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}