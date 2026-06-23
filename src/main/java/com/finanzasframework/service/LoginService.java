package com.finanzasframework.service;

import org.springframework.stereotype.Service;

/*
 * Servicio encargado de validar
 * el inicio de sesión.
 */
@Service
public class LoginService {

    /*
     * Valida las credenciales del usuario.
     */
    /*public boolean autenticar(
            String correo,
            String contrasena) {

        return correo.equals("admin@finanzas.com")
                && contrasena.equals("123456");

    }

    public boolean validarUsuario(String correo, String contrasena) {
        return autenticar(correo, contrasena);*/

        /*
     * Usuario de demostración.
     */
    /*
 * NOTA:
 * Las credenciales están precargadas únicamente
 * para fines de demostración y evaluación del proyecto.
 * En una versión productiva serán consultadas
 * desde la base de datos.
 */
    private final String correoDemo = "admin@finanzas.com";

    private final String contrasenaDemo = "123456";

    /*
     * Validar credenciales.
     */
    public boolean validarUsuario(String correo,
                                  String contrasena) {

        return correo.equals(correoDemo)
                && contrasena.equals(contrasenaDemo);
    }

}