package com.finanzasframework.controller.gasto;

import com.finanzasframework.service.GastoService;
import com.finanzasframework.service.IndicadorFinancieroService;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.finanzasframework.entity.Gasto;
import com.finanzasframework.entity.Usuario;
import com.finanzasframework.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import com.finanzasframework.enums.OrigenGasto;

/*
 * Controlador encargado de la gestión de gastos.
 */
@Controller
public class GastoController {

    private final GastoService gastoService;
    private final UsuarioRepository usuarioRepository;
    private final IndicadorFinancieroService indicadorFinancieroService;
//Constructor para inyectar las dependencias necesarias en el controlador.
    public GastoController(
        GastoService gastoService,
        UsuarioRepository usuarioRepository,
        IndicadorFinancieroService indicadorFinancieroService) {

    this.gastoService = gastoService;
    this.usuarioRepository = usuarioRepository;
    this.indicadorFinancieroService = indicadorFinancieroService;
}
    /*
     * Mostrar listado de gastos.
     */
    @GetMapping("/gastos")
public String listarGastos(Model model) {

    model.addAttribute(
            "gastos",
            gastoService.listarTodos()
    );

    model.addAttribute(
            "totalGastos",
            indicadorFinancieroService.obtenerTotalGastos()
    );

    return "gastos/listar";
}

    /*
 * Mostrar formulario de registro de gastos.
 */
@GetMapping("/gastos/nuevo")
public String nuevoGasto(Model model) {

Gasto gasto = new Gasto();

gasto.setFecha(LocalDate.now());

model.addAttribute("gasto", gasto);

model.addAttribute(
        "origenes",
        OrigenGasto.values()
);

return "gastos/formulario";

}

/*
 * Registrar un nuevo gasto.
 */
@PostMapping("/gastos")
public String guardarGasto(
        @Valid @ModelAttribute("gasto") Gasto gasto,
        BindingResult bindingResult,
        Model model,
        RedirectAttributes redirectAttributes) {

    if (bindingResult.hasErrors()) {

    model.addAttribute(
            "origenes",
            OrigenGasto.values()
    );

    return "gastos/formulario";
}

    boolean esEdicion = gasto.getId() != null;

    Usuario usuario = usuarioRepository
            .findByCorreo("admin@finanzas.com")
            .orElseThrow();

    if (esEdicion) {

        Gasto gastoExistente =
                gastoService.buscarPorId(gasto.getId());

        gasto.setUsuario(gastoExistente.getUsuario());

    } else {

        gasto.setUsuario(usuario);

    }

    gastoService.registrarGasto(gasto);

    if (esEdicion) {

        redirectAttributes.addFlashAttribute(
                "mensajeExito",
                "Gasto actualizado correctamente"
        );

    } else {

        redirectAttributes.addFlashAttribute(
        "mensajeExito",
        "Gasto registrado correctamente"
        );

    }

    return "redirect:/gastos";

}

/*
 * Eliminar un gasto.
 */
@GetMapping("/gastos/eliminar/{id}")
public String eliminarGasto(
        @PathVariable Long id,
        RedirectAttributes redirectAttributes) {

    gastoService.eliminarPorId(id);

    redirectAttributes.addFlashAttribute(
            "mensajeExito",
            "Gasto eliminado correctamente"
    );

    return "redirect:/gastos";

}

/*
 * Mostrar formulario para editar un gasto.
 */
@GetMapping("/gastos/editar/{id}")
public String editarGasto(
        @PathVariable Long id,
        Model model) {

    model.addAttribute(
            "gasto",
            gastoService.buscarPorId(id)
    );

    model.addAttribute(
        "origenes",
        OrigenGasto.values()
);

    return "gastos/formulario";

}

}