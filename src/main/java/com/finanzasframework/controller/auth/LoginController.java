package com.finanzasframework.controller.auth;

import com.finanzasframework.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
 * Controlador encargado del inicio de sesión.
 */
@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {

        this.loginService = loginService;

    }

    /*
     * Página principal.
     */
    @GetMapping("/")
    public String inicio() {

        return "redirect:/login";

    }

    /*
     * Mostrar formulario de inicio de sesión.
     */
    @GetMapping("/login")
    public String mostrarLogin() {

        return "auth/login";

    }

    /*
     * Procesar formulario.
     */
    @PostMapping("/login")
    public String autenticar(

            @RequestParam String correo,
            @RequestParam String contrasena,

            Model model) {

        if (loginService.validarUsuario(correo, contrasena)) {

            return "redirect:/dashboard";

        }

        /*
         * Si las credenciales son incorrectas,
         * se muestra un mensaje de error.
         */
        model.addAttribute(
                "error",
                "Correo o contraseña incorrectos"
        );

        model.addAttribute("correo", correo);

        return "auth/login";

    }

}