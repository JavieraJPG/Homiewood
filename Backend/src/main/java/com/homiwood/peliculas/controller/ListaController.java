package com.homiwood.peliculas.controller;

import com.homiwood.peliculas.dto.CrearListaRequest;
import com.homiwood.peliculas.model.Lista;
import com.homiwood.peliculas.service.ListaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listas")
public class ListaController {

    private final ListaService listaService;

    public ListaController(ListaService listaService) {
        this.listaService = listaService;
    }

    @GetMapping
    public List<Lista> listarListas() {
        return listaService.listarListas();
    }

    @GetMapping("/{id}")
    public Lista buscarLista(@PathVariable Long id) {
        return listaService.buscarPorId(id);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Lista> listarPorUsuario(@PathVariable Long idUsuario) {
        return listaService.listarPorUsuario(idUsuario);
    }

    @PostMapping
    public ResponseEntity<Lista> crearLista(@RequestBody CrearListaRequest request) {
        Lista listaCreada = listaService.crearLista(request);
        return ResponseEntity.ok(listaCreada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarLista(@PathVariable Long id) {
        listaService.eliminarLista(id);
        return ResponseEntity.ok("Lista eliminada correctamente");
    }
}