package com.finanzasframework.dto;

/**
 * Datos mínimos de un usuario para seleccionarlo en la demostración JDBC.
 */
public record UsuarioJdbcOpcion(Long id, String nombre, String correo) {
}
