package com.finanzasframework.service;

import com.finanzasframework.entity.Ingreso;
import com.finanzasframework.enums.TipoIngreso;
import com.finanzasframework.repository.IngresoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/*
 * Servicio encargado de la lógica
 * de negocio del módulo de ingresos.
 */
@Service
public class IngresoService {

    private final IngresoRepository ingresoRepository;

    public IngresoService(IngresoRepository ingresoRepository) {

        this.ingresoRepository = ingresoRepository;

    }

    /*
     * Registrar un nuevo ingreso.
     */
    public Ingreso registrarIngreso(Ingreso ingreso) {

        return ingresoRepository.save(ingreso);

    }

    /*
     * Obtener todos los ingresos.
     */
    public List<Ingreso> listarTodos() {

        return ingresoRepository.findAll();

    }

    /*
     * Buscar un ingreso por su id.
     */
    public Ingreso buscarPorId(Long id) {

        return ingresoRepository.findById(id).orElseThrow();

    }

    /*
     * Eliminar un ingreso.
     */
    public void eliminarPorId(Long id) {

        ingresoRepository.deleteById(id);

    }

    /*
 * Obtener el ingreso fijo.
 */
public Ingreso obtenerIngresoFijo() {

    return ingresoRepository.findAll()
            .stream()
            .filter(ingreso ->
                    ingreso.getTipo() == TipoIngreso.FIJO)
            .findFirst()
            .orElse(null);

}

/*
 * Obtener todos los ingresos adicionales.
 */
public List<Ingreso> listarIngresosAdicionales() {

    return ingresoRepository.findAll()
            .stream()
            .filter(ingreso ->
                    ingreso.getTipo() == TipoIngreso.ADICIONAL)
            .toList();

}

/*
 * Obtener el total de ingresos.
 */
public BigDecimal obtenerTotalIngresos() {

    return ingresoRepository.findAll()
            .stream()
            .map(Ingreso::getMonto)
            .reduce(
                    BigDecimal.ZERO,
                    BigDecimal::add
            );

}

/*
 * Eliminar un ingreso por id.
 */
public void eliminarIngreso(Long id) {

    ingresoRepository.deleteById(id);

}


}