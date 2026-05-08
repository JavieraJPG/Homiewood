package com.homiwood.peliculas.service;

import com.homiwood.peliculas.dto.CrearContenidoRequest;
import com.homiwood.peliculas.model.Contenido;
import com.homiwood.peliculas.repository.ContenidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContenidoService {

    private final ContenidoRepository contenidoRepository;

    public ContenidoService(ContenidoRepository contenidoRepository) {
        this.contenidoRepository = contenidoRepository;
    }

    public List<Contenido> listarContenidos() {
        return contenidoRepository.findAll();
    }

    public Contenido buscarPorId(Long id) {
        return contenidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contenido no encontrado"));
    }

    public List<Contenido> buscarPorTipo(String tipoContenido) {
        return contenidoRepository.findByTipoContenido(tipoContenido.toUpperCase());
    }

    public List<Contenido> buscarPorTitulo(String titulo) {
        return contenidoRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public Contenido crearContenido(CrearContenidoRequest request) {

        validarTipoContenido(request.getTipoContenido());

        if (request.getApiProvider() != null && request.getApiId() != null) {
            contenidoRepository.findByApiProviderAndApiId(request.getApiProvider(), request.getApiId())
                    .ifPresent(contenidoExistente -> {
                        throw new RuntimeException("Este contenido ya existe en la base de datos");
                    });
        }

        Contenido contenido = new Contenido();
        contenido.setTitulo(request.getTitulo());
        contenido.setTipoContenido(request.getTipoContenido().toUpperCase());
        contenido.setDescripcion(request.getDescripcion());
        contenido.setAnioEstreno(request.getAnioEstreno());
        contenido.setFechaEstreno(request.getFechaEstreno());
        contenido.setDuracionMinutos(request.getDuracionMinutos());
        contenido.setClasificacionEdad(request.getClasificacionEdad());
        contenido.setPosterUrl(request.getPosterUrl());
        contenido.setPais(request.getPais());
        contenido.setIdioma(request.getIdioma());
        contenido.setPresupuesto(request.getPresupuesto());
        contenido.setApiProvider(request.getApiProvider());
        contenido.setApiId(request.getApiId());

        return contenidoRepository.save(contenido);
    }

    public void eliminarContenido(Long id) {
        contenidoRepository.deleteById(id);
    }

    private void validarTipoContenido(String tipoContenido) {
        if (tipoContenido == null || tipoContenido.isBlank()) {
            throw new RuntimeException("El tipo de contenido es obligatorio");
        }

        String tipo = tipoContenido.toUpperCase();

        if (!tipo.equals("PELICULA") && !tipo.equals("SERIE")) {
            throw new RuntimeException("El tipo de contenido debe ser PELICULA o SERIE");
        }
    }
}