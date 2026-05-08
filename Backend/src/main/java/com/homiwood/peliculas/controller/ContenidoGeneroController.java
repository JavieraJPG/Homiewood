package com.homiwood.peliculas.controller;

import com.homiwood.peliculas.dto.AgregarGeneroContenidoRequest;
import com.homiwood.peliculas.model.ContenidoGenero;
import com.homiwood.peliculas.service.ContenidoGeneroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContenidoGeneroController {

    private final ContenidoGeneroService contenidoGeneroService;

    public ContenidoGeneroController(ContenidoGeneroService contenidoGeneroService) {
        this.contenidoGeneroService = contenidoGeneroService;
    }

    @GetMapping("/contenidos/{idContenido}/generos")
    public List<ContenidoGenero> listarGenerosDeContenido(@PathVariable Long idContenido) {
        return contenidoGeneroService.listarGenerosDeContenido(idContenido);
    }

    @PostMapping("/contenidos/{idContenido}/generos")
    public ResponseEntity<ContenidoGenero> agregarGeneroAContenido(
            @PathVariable Long idContenido,
            @RequestBody AgregarGeneroContenidoRequest request
    ) {
        ContenidoGenero resultado = contenidoGeneroService.agregarGeneroAContenido(idContenido, request);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/generos/{idGenero}/contenidos")
    public List<ContenidoGenero> listarContenidosPorGenero(@PathVariable Long idGenero) {
        return contenidoGeneroService.listarContenidosPorGenero(idGenero);
    }

    @DeleteMapping("/contenido-generos/{idContenidoGenero}")
    public ResponseEntity<String> eliminarRelacion(@PathVariable Long idContenidoGenero) {
        contenidoGeneroService.eliminarRelacion(idContenidoGenero);
        return ResponseEntity.ok("Género eliminado del contenido correctamente");
    }
}