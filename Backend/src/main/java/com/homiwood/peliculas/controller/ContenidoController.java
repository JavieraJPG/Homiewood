package com.homiwood.peliculas.controller;

import com.homiwood.peliculas.dto.CrearContenidoRequest;
import com.homiwood.peliculas.model.Contenido;
import com.homiwood.peliculas.service.ContenidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contenidos")
public class ContenidoController {

    private final ContenidoService contenidoService;

    public ContenidoController(ContenidoService contenidoService) {
        this.contenidoService = contenidoService;
    }

    @GetMapping
    public List<Contenido> listarContenidos() {
        return contenidoService.listarContenidos();
    }

    @GetMapping("/{id}")
    public Contenido buscarPorId(@PathVariable Long id) {
        return contenidoService.buscarPorId(id);
    }

    @GetMapping("/tipo/{tipoContenido}")
    public List<Contenido> buscarPorTipo(@PathVariable String tipoContenido) {
        return contenidoService.buscarPorTipo(tipoContenido);
    }

    @GetMapping("/buscar")
    public List<Contenido> buscarPorTitulo(@RequestParam String titulo) {
        return contenidoService.buscarPorTitulo(titulo);
    }

    @PostMapping
    public ResponseEntity<Contenido> crearContenido(@RequestBody CrearContenidoRequest request) {
        Contenido contenidoCreado = contenidoService.crearContenido(request);
        return ResponseEntity.ok(contenidoCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarContenido(@PathVariable Long id) {
        contenidoService.eliminarContenido(id);
        return ResponseEntity.ok("Contenido eliminado correctamente");
    }
}