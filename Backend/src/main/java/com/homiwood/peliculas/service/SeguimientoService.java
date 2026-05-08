package com.homiwood.peliculas.service;

import com.homiwood.peliculas.dto.CrearSeguimientoRequest;
import com.homiwood.peliculas.model.Seguimiento;
import com.homiwood.peliculas.model.Usuario;
import com.homiwood.peliculas.repository.SeguimientoRepository;
import com.homiwood.peliculas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeguimientoService {

    private final SeguimientoRepository seguimientoRepository;
    private final UsuarioRepository usuarioRepository;

    public SeguimientoService(
            SeguimientoRepository seguimientoRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.seguimientoRepository = seguimientoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Seguimiento seguirUsuario(Long idSeguido, CrearSeguimientoRequest request) {

        if (request.getIdSeguidor() == null) {
            throw new RuntimeException("El idSeguidor es obligatorio");
        }

        Long idSeguidor = request.getIdSeguidor();

        if (idSeguidor.equals(idSeguido)) {
            throw new RuntimeException("Un usuario no puede seguirse a sí mismo");
        }

        Usuario seguidor = usuarioRepository.findById(idSeguidor)
                .orElseThrow(() -> new RuntimeException("Usuario seguidor no encontrado"));

        Usuario seguido = usuarioRepository.findById(idSeguido)
                .orElseThrow(() -> new RuntimeException("Usuario seguido no encontrado"));

        boolean yaSigue = seguimientoRepository.existsBySeguidorIdUsuarioAndSeguidoIdUsuario(
                idSeguidor,
                idSeguido
        );

        if (yaSigue) {
            throw new RuntimeException("Este usuario ya sigue a ese usuario");
        }

        Seguimiento seguimiento = new Seguimiento();
        seguimiento.setSeguidor(seguidor);
        seguimiento.setSeguido(seguido);

        return seguimientoRepository.save(seguimiento);
    }

    public List<Seguimiento> listarUsuariosQueSigo(Long idUsuario) {
        return seguimientoRepository.findBySeguidorIdUsuario(idUsuario);
    }

    public List<Seguimiento> listarMisSeguidores(Long idUsuario) {
        return seguimientoRepository.findBySeguidoIdUsuario(idUsuario);
    }

    public long contarUsuariosQueSigo(Long idUsuario) {
        return seguimientoRepository.countBySeguidorIdUsuario(idUsuario);
    }

    public long contarMisSeguidores(Long idUsuario) {
        return seguimientoRepository.countBySeguidoIdUsuario(idUsuario);
    }

    public void dejarDeSeguir(Long idSeguidor, Long idSeguido) {

        Seguimiento seguimiento = seguimientoRepository
                .findBySeguidorIdUsuarioAndSeguidoIdUsuario(idSeguidor, idSeguido)
                .orElseThrow(() -> new RuntimeException("Seguimiento no encontrado"));

        seguimientoRepository.delete(seguimiento);
    }
}