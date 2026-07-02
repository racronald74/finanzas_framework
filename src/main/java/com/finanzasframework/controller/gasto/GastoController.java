package com.finanzasframework.controller.gasto;

import com.finanzasframework.service.GastoService;
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

/*
 * Controlador encargado de la gestión de gastos.
 */
@Controller
public class GastoController {

    private final GastoService gastoService;
    private final UsuarioRepository usuarioRepository;
//Constructor para inyectar las dependencias necesarias en el controlador.
    public GastoController(
        GastoService gastoService,
        UsuarioRepository usuarioRepository) {

    this.gastoService = gastoService;
    this.usuarioRepository = usuarioRepository;

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

        return "gastos/listar";

    }

    /*
 * Mostrar formulario de registro de gastos.
 */
@GetMapping("/gastos/nuevo")
public String nuevoGasto(Model model) {

    model.addAttribute(
            "gasto",
            new Gasto()
    );

    return "gastos/formulario";

}

/*
 * Registrar un nuevo gasto.
 */
@PostMapping("/gastos")
public String guardarGasto(
        @ModelAttribute Gasto gasto,
        RedirectAttributes redirectAttributes) {

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
                "actualizado"
        );

    } else {

        redirectAttributes.addFlashAttribute(
                "mensajeExito",
                "registrado"
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
            "eliminado"
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

    return "gastos/formulario";

}

}