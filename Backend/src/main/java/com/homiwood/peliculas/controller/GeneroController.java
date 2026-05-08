package com.homiwood.peliculas.controller;

import com.homiwood.peliculas.model.Genero;
import com.homiwood.peliculas.service.GeneroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping
    public List<Genero> listarGeneros() {
        return generoService.listarGeneros();
    }

    @GetMapping("/{id}")
    public Genero buscarPorId(@PathVariable Long id) {
        return generoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Genero> crearGenero(@RequestBody Genero genero) {
        Genero generoCreado = generoService.crearGenero(genero);
        return ResponseEntity.ok(generoCreado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarGenero(@PathVariable Long id) {
        generoService.eliminarGenero(id);
        return ResponseEntity.ok("Género eliminado correctamente");
    }
}