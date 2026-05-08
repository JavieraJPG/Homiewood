package com.homiwood.peliculas.controller;

import com.homiwood.peliculas.dto.GuardarYAgregarContenidoRequest;
import com.homiwood.peliculas.model.ListaContenido;
import com.homiwood.peliculas.service.CatalogoListaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/listas")
public class CatalogoListaController {

    private final CatalogoListaService catalogoListaService;

    public CatalogoListaController(CatalogoListaService catalogoListaService) {
        this.catalogoListaService = catalogoListaService;
    }

    @PostMapping("/{idLista}/contenidos/externo")
    public ResponseEntity<ListaContenido> guardarYAgregarContenido(
            @PathVariable Long idLista,
            @RequestBody GuardarYAgregarContenidoRequest request
    ) {
        ListaContenido resultado = catalogoListaService.guardarYAgregarALista(idLista, request);
        return ResponseEntity.ok(resultado);
    }
}