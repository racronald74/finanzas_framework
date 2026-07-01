package com.finanzasframework.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
//Atributos de la entidad Usuario
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
    
    /*
 * Lista de gastos asociados al usuario.
 */
@OneToMany(
        mappedBy = "usuario",
        cascade = CascadeType.ALL,
        orphanRemoval = true
)
private List<Gasto> gastos = new ArrayList<>();
//Metodos getter y setter para la relación con Gasto
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

    public List<Gasto> getGastos() {
    return gastos;
}

public void setGastos(List<Gasto> gastos) {
    this.gastos = gastos;
}

}