package com.finanzasframework.controller.ingreso;

import com.finanzasframework.service.IngresoService;

import jakarta.validation.Valid;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.finanzasframework.entity.Ingreso;
import com.finanzasframework.enums.TipoIngreso;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.finanzasframework.entity.Usuario;
import com.finanzasframework.repository.UsuarioRepository;

/*
 * Controlador encargado de la gestión de ingresos.
 */
@Controller
public class IngresoController {

    private final IngresoService ingresoService;

    private final UsuarioRepository usuarioRepository;

    public IngresoController(
        IngresoService ingresoService,
        UsuarioRepository usuarioRepository) {

    this.ingresoService = ingresoService;
    this.usuarioRepository = usuarioRepository;
}

    /*
     * Mostrar pantalla principal de ingresos.
     */
    @GetMapping("/ingresos")
    public String listarIngresos(Model model) {

        model.addAttribute(
        "ingresos",
        ingresoService.listarIngresosAdicionales()
);

        model.addAttribute(
        "ingresoFijo",
        ingresoService.obtenerIngresoFijo()
        );

        model.addAttribute(
        "totalIngresos",
        ingresoService.obtenerTotalIngresos()
    );

        return "ingresos/listar";

    }

    /*
 * Metodo Mostrar formulario del ingreso fijo.
 */
@GetMapping("/ingresos/fijo")
public String ingresoFijo(Model model) {

    Ingreso ingreso = ingresoService.obtenerIngresoFijo();

    if (ingreso == null) {

        ingreso = new Ingreso();

        ingreso.setTipo(TipoIngreso.FIJO);

    }

    model.addAttribute("ingreso", ingreso);

    return "ingresos/fijo";

}

/*
 * POST Guardar ingreso fijo.
 */
@PostMapping("/ingresos/fijo")
public String guardarIngresoFijo(
        @ModelAttribute Ingreso ingreso,
        RedirectAttributes redirectAttributes) {

    ingreso.setTipo(TipoIngreso.FIJO);

    ingreso.setFecha(LocalDate.now());

    ingreso.setDescripcion("Ingreso fijo mensual");

Usuario usuario = usuarioRepository
        .findByCorreo("admin@finanzas.com")
        .orElseThrow();

    ingreso.setUsuario(usuario);

    ingresoService.registrarIngreso(ingreso);

    redirectAttributes.addFlashAttribute(
            "mensajeExito",
            "Ingreso fijo actualizado correctamente"
    );

    return "redirect:/ingresos";
}

/*
 * Mostrar formulario de ingreso adicional.
 */
@GetMapping("/ingresos/nuevo")
public String nuevoIngreso(Model model) {

    Ingreso ingreso = new Ingreso();

    ingreso.setFecha(LocalDate.now());

    ingreso.setTipo(TipoIngreso.ADICIONAL);

    model.addAttribute(
            "ingreso",
            ingreso
    );

    return "ingresos/formulario";

}

/*
 * Registrar un ingreso adicional.
 */
@PostMapping("/ingresos")
public String guardarIngreso(
        @Valid @ModelAttribute("ingreso") Ingreso ingreso,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes,
        Model model) {

    if (bindingResult.hasErrors()) {
    return "ingresos/formulario";
}



    Usuario usuario = usuarioRepository
        .findByCorreo("admin@finanzas.com")
        .orElseThrow();

ingreso.setUsuario(usuario);

ingreso.setTipo(TipoIngreso.ADICIONAL);

boolean esNuevo = ingreso.getId() == null;

ingresoService.registrarIngreso(ingreso);

redirectAttributes.addFlashAttribute(
        "mensajeExito",
        esNuevo
                ? "Ingreso registrado correctamente"
                : "Ingreso actualizado correctamente"
);

return "redirect:/ingresos";

}

/*
 * Mostrar formulario para editar un ingreso adicional.
 */
@GetMapping("/ingresos/editar/{id}")
public String editarIngreso(
        @org.springframework.web.bind.annotation.PathVariable Long id,
        Model model) {

    Ingreso ingreso = ingresoService.buscarPorId(id);

    model.addAttribute(
            "ingreso",
            ingreso
    );

    return "ingresos/formulario";
}

/*
 * Eliminar un ingreso adicional.
 */
@GetMapping("/ingresos/eliminar/{id}")
public String eliminarIngreso(
        @PathVariable Long id,
        RedirectAttributes redirectAttributes) {

    ingresoService.eliminarIngreso(id);

    redirectAttributes.addFlashAttribute(
            "mensajeExito",
            "Ingreso eliminado correctamente"
    );

    return "redirect:/ingresos";

}

}