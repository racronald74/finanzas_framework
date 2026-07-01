package com.finanzasframework.repository;

import com.finanzasframework.entity.Gasto;
import com.finanzasframework.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Repositorio encargado de administrar
 * las operaciones de la entidad Gasto.
 */
@Repository
public interface GastoRepository
        extends JpaRepository<Gasto, Long> {

    /*
     * Obtener todos los gastos
     * pertenecientes a un usuario.
     */
    List<Gasto> findByUsuario(Usuario usuario);

}