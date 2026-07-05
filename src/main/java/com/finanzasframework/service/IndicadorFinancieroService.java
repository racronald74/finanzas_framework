package com.finanzasframework.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

/*
 * Servicio encargado de calcular
 * los indicadores financieros.
 */
@Service
public class IndicadorFinancieroService {

    private final IngresoService ingresoService;

    private final GastoService gastoService;

    public IndicadorFinancieroService(
            IngresoService ingresoService,
            GastoService gastoService) {

        this.ingresoService = ingresoService;
        this.gastoService = gastoService;

    }

    /*
     * Obtener saldo disponible.
     */
    public BigDecimal obtenerSaldoDisponible() {

        return ingresoService.obtenerTotalIngresos()
                .subtract(
                        gastoService.obtenerTotalGastos()
                );

    }

    /*
 * Obtener el total de ingresos.
 */
public BigDecimal obtenerTotalIngresos() {

    return ingresoService.obtenerTotalIngresos();

}

/*
 * Obtener el total de gastos.
 */
public BigDecimal obtenerTotalGastos() {

    return gastoService.obtenerTotalGastos();

}

}