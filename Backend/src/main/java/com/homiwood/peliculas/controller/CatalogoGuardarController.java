package com.homiwood.peliculas.controller;

import com.homiwood.peliculas.dto.GuardarContenidoExternoRequest;
import com.homiwood.peliculas.model.Contenido;
import com.homiwood.peliculas.service.CatalogoGuardarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoGuardarController {

    private final CatalogoGuardarService catalogoGuardarService;

    public CatalogoGuardarController(CatalogoGuardarService catalogoGuardarService) {
        this.catalogoGuardarService = catalogoGuardarService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<Contenido> guardarContenidoExterno(
            @RequestBody GuardarContenidoExternoRequest request
    ) {
        Contenido contenidoGuardado = catalogoGuardarService.guardarContenidoExterno(request);
        return ResponseEntity.ok(contenidoGuardado);
    }
}