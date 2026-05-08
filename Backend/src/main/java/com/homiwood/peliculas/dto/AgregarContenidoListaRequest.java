package com.homiwood.peliculas.dto;

public class AgregarContenidoListaRequest {

    private Long idContenido;
    private Integer posicion;
    private String estado;
    private String notaUsuario;

    public Long getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(Long idContenido) {
        this.idContenido = idContenido;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNotaUsuario() {
        return notaUsuario;
    }

    public void setNotaUsuario(String notaUsuario) {
        this.notaUsuario = notaUsuario;
    }
}