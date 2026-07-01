package com.finanzasframework.service;

import org.springframework.stereotype.Service;

import com.finanzasframework.entity.Usuario;
import com.finanzasframework.repository.UsuarioRepository;

import java.util.Optional;

/*
 * Servicio encargado de validar
 * el inicio de sesión.
 */
@Service
public class LoginService {

    private final UsuarioRepository usuarioRepository;

    public LoginService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /*
 * Valida las credenciales del usuario.
 */
public boolean validarUsuario(String correo,
                              String contrasena) {

    Optional<Usuario> usuario =
            usuarioRepository.findByCorreo(correo);

    if (usuario.isPresent()) {

        return usuario.get()
                .getContrasena()
                .equals(contrasena);

    }

    return false;

}

}