package com.finanzasframework.entity;

import jakarta.persistence.*;

/*
 * Entidad que representa la tabla usuario.
 */
@Entity
@Table(name = "usuario")
public class Usuario {

    /*
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Nombre del usuario.
     */
    @Column(nullable = false, length = 100)
    private String nombre;

    /*
     * Correo electrónico.
     */
    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    /*
     * Contraseña.
     */
    @Column(nullable = false, length = 255)
    private String contrasena;

    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}