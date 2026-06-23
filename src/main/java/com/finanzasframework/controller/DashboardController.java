package com.finanzasframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * Controlador para manejar las solicitudes relacionadas con el panel de control (dashboard).
 */
@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard() {

        return "dashboard";

    }

}