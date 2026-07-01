package com.finanzasframework.service;

import com.finanzasframework.entity.Gasto;
import com.finanzasframework.repository.GastoRepository;
import org.springframework.stereotype.Service;

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

}