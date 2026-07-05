package com.finanzasframework.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import com.finanzasframework.enums.OrigenGasto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

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
    @NotNull(message = "El monto es obligatorio.")
@Positive(message = "El monto debe ser mayor que cero.")
@Column(nullable = false, precision = 10, scale = 2)
private BigDecimal monto;

    /*
     * Fecha del gasto.
     */
    @NotNull(message = "La fecha es obligatoria.")
@Column(nullable = false)
private LocalDate fecha;

    /*
     * Descripción del gasto.
     */
    @NotBlank(message = "La descripción es obligatoria.")
@Size(
        min = 5,
        max = 255,
        message = "La descripción debe tener entre 5 y 255 caracteres."
)
@Column(columnDefinition = "TEXT")
private String descripcion;

 /*
 * Origen del gasto.
 */
@NotNull(message = "Seleccione un origen.")
@Enumerated(EnumType.STRING)
@Column(length = 20)
private OrigenGasto origen;

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

    public OrigenGasto getOrigen() {
        return origen;
    }

    public void setOrigen(OrigenGasto origen) {
        this.origen = origen;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}