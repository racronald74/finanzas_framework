package com.finanzasframework.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.finanzasframework.enums.TipoIngreso;

/*
 * Entidad que representa la tabla ingreso.
 */
@Entity
@Table(name = "ingreso")
public class Ingreso {

    /*
     * Identificador único del ingreso.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Monto del ingreso.
     */
    @NotNull(message = "El monto es obligatorio.")
    @Positive(message = "El monto debe ser mayor que cero.")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    /*
     * Fecha del ingreso.
     */
    @NotNull(message = "La fecha es obligatoria.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate fecha;

    /*
     * Descripción del ingreso.
     */
    @NotBlank(message = "La descripción es obligatoria.")
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    /*
     * Tipo de ingreso.
     */
    @NotNull(message = "Seleccione un tipo de ingreso.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoIngreso tipo;

    /*
     * Usuario propietario del ingreso.
     */
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public Ingreso() {
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

    public TipoIngreso getTipo() {
    return tipo;
    }

    public void setTipo(TipoIngreso tipo) {
    this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}