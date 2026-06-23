package com.finanzasframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * Controlador encargado de mostrar
 * la pantalla de inicio de sesión.
 */
@Controller
public class LoginController {

    /*
     * Muestra la vista login.html.
     */
    @GetMapping("/")
    public String login() {
        return "login";
    }

}