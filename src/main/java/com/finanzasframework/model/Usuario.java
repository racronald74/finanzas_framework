package com.finanzasframework.model;

/*
 * Modelo que representa un usuario del sistema.
 */
public class Usuario {

    /*
     * Correo electrónico del usuario.
     */
    private String correo;

    /*
     * Contraseña del usuario.
     */
    private String contrasena;
/*
* Constructor por defecto de la clase Usuario.
*/
    public Usuario() {
    }

    public Usuario(String correo, String contrasena) {
        this.correo = correo;
        this.contrasena = contrasena;
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