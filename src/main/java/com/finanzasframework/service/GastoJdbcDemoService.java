package com.finanzasframework.service;

import com.finanzasframework.dto.GastoJdbcDemo;
import com.finanzasframework.dto.UsuarioJdbcOpcion;
import com.finanzasframework.repository.jdbc.GastoJdbcDemoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Centraliza la lógica de la demostración JDBC sin afectar al servicio JPA de gastos.
 */
@Service
public class GastoJdbcDemoService {

    private final GastoJdbcDemoRepository gastoJdbcDemoRepository;

    public GastoJdbcDemoService(GastoJdbcDemoRepository gastoJdbcDemoRepository) {
        this.gastoJdbcDemoRepository = gastoJdbcDemoRepository;
    }

    public List<GastoJdbcDemo> listar() {
        return gastoJdbcDemoRepository.listar();
    }

    public GastoJdbcDemo buscarPorId(Long id) {
        return gastoJdbcDemoRepository.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("El gasto JDBC no existe."));
    }

    public List<UsuarioJdbcOpcion> listarUsuarios() {
        return gastoJdbcDemoRepository.listarUsuarios();
    }

    /** Guarda un registro nuevo o actualiza uno existente tras validar su usuario. */
    public GastoJdbcDemo guardar(GastoJdbcDemo gasto) {
        validarUsuario(gasto.getUsuarioId());

        if (gasto.getId() == null) {
            return gastoJdbcDemoRepository.insertar(gasto);
        }

        if (!gastoJdbcDemoRepository.actualizar(gasto)) {
            throw new NoSuchElementException("El gasto JDBC no existe.");
        }

        return gasto;
    }

    /** Elimina un registro y señala si ya no existe. */
    public void eliminarPorId(Long id) {
        if (!gastoJdbcDemoRepository.eliminarPorId(id)) {
            throw new NoSuchElementException("El gasto JDBC no existe.");
        }
    }

    private void validarUsuario(Long idUsuario) {
        if (idUsuario == null || !gastoJdbcDemoRepository.existeUsuario(idUsuario)) {
            throw new IllegalArgumentException("El usuario seleccionado no existe.");
        }
    }
}
