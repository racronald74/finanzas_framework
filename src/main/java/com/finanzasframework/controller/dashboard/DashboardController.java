package com.finanzasframework.controller.dashboard;

import com.finanzasframework.service.IndicadorFinancieroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para manejar las solicitudes relacionadas con el panel de control.
 */
@Controller
public class DashboardController {

    private final IndicadorFinancieroService indicadorFinancieroService;
//Constructor para inyectar la dependencia de IndicadorFinancieroService.
    public DashboardController(
            IndicadorFinancieroService indicadorFinancieroService) {

        this.indicadorFinancieroService = indicadorFinancieroService;

    }
//Metodo para manejar la solicitud GET al panel de control y mostrar el saldo disponible.
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute(
        "totalIngresos",
        indicadorFinancieroService.obtenerTotalIngresos()
);

        model.addAttribute(
        "totalGastos",
        indicadorFinancieroService.obtenerTotalGastos()
);

        model.addAttribute(
        "saldoDisponible",
        indicadorFinancieroService.obtenerSaldoDisponible()
);

        return "dashboard/dashboard";

    }

}