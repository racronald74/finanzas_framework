package com.finanzasframework.enums;

/*
 * Tipos de ingreso permitidos en el sistema.
 */
public enum TipoIngreso {

    FIJO("Fijo"),

    ADICIONAL("Adicional");

    private final String descripcion;

    TipoIngreso(String descripcion) {

        this.descripcion = descripcion;

    }

    public String getDescripcion() {

        return descripcion;

    }

}