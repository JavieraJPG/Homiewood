package com.homiwood.peliculas.dto;

public class AgregarMiembroGrupoRequest {

    private Long idUsuario;
    private String rol;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}