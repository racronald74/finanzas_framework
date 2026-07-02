package com.finanzasframework.service;

import com.finanzasframework.entity.Gasto;
import com.finanzasframework.repository.GastoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/*
 * Servicio encargado de la lógica
 * de negocio del módulo de gastos.
 */
@Service
public class GastoService {

    private final GastoRepository gastoRepository;

    public GastoService(GastoRepository gastoRepository) {

        this.gastoRepository = gastoRepository;

    }

    /*
     * Registrar un nuevo gasto.
     */
    public Gasto registrarGasto(Gasto gasto) {

        return gastoRepository.save(gasto);

    }

    /*
 * Obtener todos los gastos registrados.
 */
public List<Gasto> listarTodos() {

    return gastoRepository.findAll();

}

/*
 * Buscar un gasto por su identificador.
 */
public Gasto buscarPorId(Long id) {

    return gastoRepository
            .findById(id)
            .orElseThrow();

}

/*
 * Eliminar un gasto por su identificador.
 */
public void eliminarPorId(Long id) {

    gastoRepository.deleteById(id);

}

}