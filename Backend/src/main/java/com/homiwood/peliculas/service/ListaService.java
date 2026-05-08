package com.homiwood.peliculas.service;

import com.homiwood.peliculas.dto.CrearListaRequest;
import com.homiwood.peliculas.model.Lista;
import com.homiwood.peliculas.model.Usuario;
import com.homiwood.peliculas.repository.ListaRepository;
import com.homiwood.peliculas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListaService {

    private final ListaRepository listaRepository;
    private final UsuarioRepository usuarioRepository;

    public ListaService(ListaRepository listaRepository, UsuarioRepository usuarioRepository) {
        this.listaRepository = listaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Lista> listarListas() {
        return listaRepository.findAll();
    }

    public List<Lista> listarPorUsuario(Long idUsuario) {
        return listaRepository.findByUsuarioIdUsuario(idUsuario);
    }

    public Lista buscarPorId(Long id) {
        return listaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lista no encontrada"));
    }

    public Lista crearLista(CrearListaRequest request) {

        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Lista lista = new Lista();
        lista.setUsuario(usuario);
        lista.setTitulo(request.getTitulo());
        lista.setDescripcion(request.getDescripcion());

        if (request.getVisibilidad() == null || request.getVisibilidad().isBlank()) {
            lista.setVisibilidad("PUBLICA");
        } else {
            lista.setVisibilidad(request.getVisibilidad());
        }

        return listaRepository.save(lista);
    }

    public void eliminarLista(Long id) {
        listaRepository.deleteById(id);
    }
}