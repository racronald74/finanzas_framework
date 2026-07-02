package com.finanzasframework.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/*
 * Entidad que representa la tabla gasto.
 */
@Entity
@Table(name = "gasto")
public class Gasto {

    /*
     * Identificador único del gasto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//Atributos de la entidad Gasto
    /*
     * Monto del gasto.
     */
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    /*
     * Fecha del gasto.
     */
    @Column(nullable = false)
    private LocalDate fecha;

    /*
     * Descripción del gasto.
     */
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    /*
     * Origen del gasto.
     */
    @Column(length = 20)
    private String origen;

    /*
 * Usuario propietario del gasto.
 */
@ManyToOne
@JoinColumn(name = "id_usuario", nullable = false)
private Usuario usuario;
//Metodos getter y setter para la relación con Usuario
    public Gasto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {

    this.id = id;

}

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}