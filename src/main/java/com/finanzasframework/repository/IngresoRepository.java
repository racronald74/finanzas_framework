package com.finanzasframework.repository;

import com.finanzasframework.entity.Ingreso;
import com.finanzasframework.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Repositorio encargado de administrar
 * las operaciones de la entidad Ingreso.
 */
@Repository
public interface IngresoRepository
        extends JpaRepository<Ingreso, Long> {

    /*
     * Obtener todos los ingresos
     * pertenecientes a un usuario.
     */
    List<Ingreso> findByUsuario(Usuario usuario);

}