package com.finanzasframework.enums;

/*
 * Tipos de origen permitidos para un gasto.
 */
public enum OrigenGasto {

    MANUAL("Manual"),

    PAGO("Pago"),

    OBLIGACION("Obligación");

    private final String descripcion;

    OrigenGasto(String descripcion) {

        this.descripcion = descripcion;

    }

    public String getDescripcion() {

        return descripcion;

    }

}