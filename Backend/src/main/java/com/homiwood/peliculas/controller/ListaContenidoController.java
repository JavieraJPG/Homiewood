package com.homiwood.peliculas.controller;

import com.homiwood.peliculas.dto.AgregarContenidoListaRequest;
import com.homiwood.peliculas.model.ListaContenido;
import com.homiwood.peliculas.service.ListaContenidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listas")
public class ListaContenidoController {

    private final ListaContenidoService listaContenidoService;

    public ListaContenidoController(ListaContenidoService listaContenidoService) {
        this.listaContenidoService = listaContenidoService;
    }

    @GetMapping("/{idLista}/contenidos")
    public List<ListaContenido> listarContenidoDeLista(@PathVariable Long idLista) {
        return listaContenidoService.listarContenidoDeLista(idLista);
    }

    @PostMapping("/{idLista}/contenidos")
    public ResponseEntity<ListaContenido> agregarContenidoALista(
            @PathVariable Long idLista,
            @RequestBody AgregarContenidoListaRequest request
    ) {
        ListaContenido resultado = listaContenidoService.agregarContenidoALista(idLista, request);
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/contenidos/{idListaContenido}")
    public ResponseEntity<String> eliminarContenidoDeLista(@PathVariable Long idListaContenido) {
        listaContenidoService.eliminarContenidoDeLista(idListaContenido);
        return ResponseEntity.ok("Contenido eliminado de la lista correctamente");
    }
}