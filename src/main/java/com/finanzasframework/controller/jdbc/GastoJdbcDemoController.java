package com.finanzasframework.controller.jdbc;

import com.finanzasframework.dto.GastoJdbcDemo;
import com.finanzasframework.enums.OrigenGasto;
import com.finanzasframework.service.GastoJdbcDemoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

/**
 * Expone una demostración MVC aislada para el CRUD implementado con JDBC explícito.
 */
@Controller
@RequestMapping("/demostracion-jdbc/gastos")
public class GastoJdbcDemoController {

    private final GastoJdbcDemoService gastoJdbcDemoService;

    public GastoJdbcDemoController(GastoJdbcDemoService gastoJdbcDemoService) {
        this.gastoJdbcDemoService = gastoJdbcDemoService;
    }

    /** Muestra únicamente los registros de la tabla gasto_jdbc_demo. */
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("gastos", gastoJdbcDemoService.listar());
        return "jdbc-gastos/listar";
    }

    /** Prepara el formulario para crear un gasto de demostración. */
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        GastoJdbcDemo gasto = new GastoJdbcDemo();
        gasto.setFecha(LocalDate.now());
        prepararFormulario(model, gasto);
        return "jdbc-gastos/formulario";
    }

    /** Valida y registra o actualiza un gasto mediante el servicio JDBC. */
    @PostMapping
    public String guardar(
            @Valid @ModelAttribute("gasto") GastoJdbcDemo gasto,
            BindingResult resultadoValidacion,
            Model model,
            RedirectAttributes atributosRedireccion) {

        if (resultadoValidacion.hasErrors()) {
            prepararFormulario(model, gasto);
            return "jdbc-gastos/formulario";
        }

        try {
            boolean esEdicion = gasto.getId() != null;
            gastoJdbcDemoService.guardar(gasto);

            atributosRedireccion.addFlashAttribute(
                    "mensajeExito",
                    esEdicion ? "Gasto JDBC actualizado correctamente." : "Gasto JDBC registrado correctamente."
            );

            return "redirect:/demostracion-jdbc/gastos";
        } catch (IllegalArgumentException excepcion) {
            resultadoValidacion.rejectValue("usuarioId", "usuario.invalido", excepcion.getMessage());
            prepararFormulario(model, gasto);
            return "jdbc-gastos/formulario";
        }
    }

    /** Muestra los datos de un gasto aislado para su edición. */
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        prepararFormulario(model, gastoJdbcDemoService.buscarPorId(id));
        return "jdbc-gastos/formulario";
    }

    /** Elimina un gasto de la tabla aislada mediante una solicitud POST. */
    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes atributosRedireccion) {
        gastoJdbcDemoService.eliminarPorId(id);
        atributosRedireccion.addFlashAttribute("mensajeExito", "Gasto JDBC eliminado correctamente.");
        return "redirect:/demostracion-jdbc/gastos";
    }

    private void prepararFormulario(Model model, GastoJdbcDemo gasto) {
        model.addAttribute("gasto", gasto);
        model.addAttribute("origenes", OrigenGasto.values());
        model.addAttribute("usuarios", gastoJdbcDemoService.listarUsuarios());
    }
}
