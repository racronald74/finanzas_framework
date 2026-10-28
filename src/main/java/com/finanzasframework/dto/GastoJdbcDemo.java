package com.finanzasframework.dto;

import com.finanzasframework.enums.OrigenGasto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Datos utilizados exclusivamente por la demostración de JDBC explícito.
 * Esta clase no es una entidad JPA y no representa la tabla gasto del módulo principal.
 */
public class GastoJdbcDemo {

    private Long id;

    @NotNull(message = "El monto es obligatorio.")
    @Positive(message = "El monto debe ser mayor que cero.")
    private BigDecimal monto;

    @NotNull(message = "La fecha es obligatoria.")
    private LocalDate fecha;

    @NotBlank(message = "La descripción es obligatoria.")
    @Size(min = 5, max = 255, message = "La descripción debe tener entre 5 y 255 caracteres.")
    private String descripcion;

    @NotNull(message = "Seleccione un origen.")
    private OrigenGasto origen;

    @NotNull(message = "Seleccione un usuario.")
    private Long usuarioId;

    /* Nombre mostrado solo en el listado de la demostración. */
    private String usuarioNombre;

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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }
}
