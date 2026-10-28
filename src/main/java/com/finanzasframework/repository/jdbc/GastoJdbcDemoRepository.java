package com.finanzasframework.repository.jdbc;

import com.finanzasframework.dto.GastoJdbcDemo;
import com.finanzasframework.dto.UsuarioJdbcOpcion;
import com.finanzasframework.enums.OrigenGasto;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio aislado que demuestra JDBC explícito.
 * Cada operación administra Connection, PreparedStatement y ResultSet directamente.
 */
@Repository
public class GastoJdbcDemoRepository {

    private static final String SELECT_BASE = """
            SELECT g.id, g.monto, g.fecha, g.descripcion, g.origen, g.id_usuario,
                   u.nombre AS usuario_nombre
            FROM gasto_jdbc_demo g
            INNER JOIN usuario u ON u.id = g.id_usuario
            """;

    private final DataSource dataSource;

    public GastoJdbcDemoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /** Obtiene los gastos de la demostración junto con su usuario asociado. */
    public List<GastoJdbcDemo> listar() {
        String sql = SELECT_BASE + " ORDER BY g.fecha DESC, g.id DESC";
        List<GastoJdbcDemo> gastos = new ArrayList<>();

        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultados = sentencia.executeQuery()) {

            while (resultados.next()) {
                gastos.add(mapearGasto(resultados));
            }

            return gastos;
        } catch (SQLException excepcion) {
            throw new IllegalStateException("No fue posible consultar los gastos JDBC.", excepcion);
        }
    }

    /** Busca un gasto de la demostración por su identificador. */
    public Optional<GastoJdbcDemo> buscarPorId(Long id) {
        String sql = SELECT_BASE + " WHERE g.id = ?";

        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setLong(1, id);

            try (ResultSet resultados = sentencia.executeQuery()) {
                if (resultados.next()) {
                    return Optional.of(mapearGasto(resultados));
                }

                return Optional.empty();
            }
        } catch (SQLException excepcion) {
            throw new IllegalStateException("No fue posible consultar el gasto JDBC.", excepcion);
        }
    }

    /** Inserta un gasto y asigna al objeto el identificador generado por MySQL. */
    public GastoJdbcDemo insertar(GastoJdbcDemo gasto) {
        String sql = """
                INSERT INTO gasto_jdbc_demo (monto, fecha, descripcion, origen, id_usuario)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            asignarParametros(gasto, sentencia);
            sentencia.executeUpdate();

            try (ResultSet llavesGeneradas = sentencia.getGeneratedKeys()) {
                if (!llavesGeneradas.next()) {
                    throw new IllegalStateException("MySQL no devolvió el identificador del gasto JDBC.");
                }

                gasto.setId(llavesGeneradas.getLong(1));
                return gasto;
            }
        } catch (SQLException excepcion) {
            throw new IllegalStateException("No fue posible registrar el gasto JDBC.", excepcion);
        }
    }

    /** Actualiza un gasto existente de la tabla aislada. */
    public boolean actualizar(GastoJdbcDemo gasto) {
        String sql = """
                UPDATE gasto_jdbc_demo
                SET monto = ?, fecha = ?, descripcion = ?, origen = ?, id_usuario = ?
                WHERE id = ?
                """;

        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            asignarParametros(gasto, sentencia);
            sentencia.setLong(6, gasto.getId());
            return sentencia.executeUpdate() == 1;
        } catch (SQLException excepcion) {
            throw new IllegalStateException("No fue posible actualizar el gasto JDBC.", excepcion);
        }
    }

    /** Elimina únicamente un registro de la tabla de demostración. */
    public boolean eliminarPorId(Long id) {
        String sql = "DELETE FROM gasto_jdbc_demo WHERE id = ?";

        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setLong(1, id);
            return sentencia.executeUpdate() == 1;
        } catch (SQLException excepcion) {
            throw new IllegalStateException("No fue posible eliminar el gasto JDBC.", excepcion);
        }
    }

    /** Lista usuarios existentes para respetar la clave foránea del gasto. */
    public List<UsuarioJdbcOpcion> listarUsuarios() {
        String sql = "SELECT id, nombre, correo FROM usuario ORDER BY nombre, correo";
        List<UsuarioJdbcOpcion> usuarios = new ArrayList<>();

        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultados = sentencia.executeQuery()) {

            while (resultados.next()) {
                usuarios.add(new UsuarioJdbcOpcion(
                        resultados.getLong("id"),
                        resultados.getString("nombre"),
                        resultados.getString("correo")
                ));
            }

            return usuarios;
        } catch (SQLException excepcion) {
            throw new IllegalStateException("No fue posible consultar los usuarios JDBC.", excepcion);
        }
    }

    /** Verifica que el usuario seleccionado exista antes de guardar el gasto. */
    public boolean existeUsuario(Long idUsuario) {
        String sql = "SELECT 1 FROM usuario WHERE id = ?";

        try (Connection conexion = dataSource.getConnection();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setLong(1, idUsuario);

            try (ResultSet resultados = sentencia.executeQuery()) {
                return resultados.next();
            }
        } catch (SQLException excepcion) {
            throw new IllegalStateException("No fue posible validar el usuario JDBC.", excepcion);
        }
    }

    private void asignarParametros(GastoJdbcDemo gasto, PreparedStatement sentencia) throws SQLException {
        sentencia.setBigDecimal(1, gasto.getMonto());
        sentencia.setDate(2, Date.valueOf(gasto.getFecha()));
        sentencia.setString(3, gasto.getDescripcion());
        sentencia.setString(4, gasto.getOrigen().name());
        sentencia.setLong(5, gasto.getUsuarioId());
    }

    private GastoJdbcDemo mapearGasto(ResultSet resultados) throws SQLException {
        GastoJdbcDemo gasto = new GastoJdbcDemo();
        gasto.setId(resultados.getLong("id"));
        gasto.setMonto(resultados.getBigDecimal("monto"));
        gasto.setFecha(resultados.getDate("fecha").toLocalDate());
        gasto.setDescripcion(resultados.getString("descripcion"));
        gasto.setOrigen(OrigenGasto.valueOf(resultados.getString("origen")));
        gasto.setUsuarioId(resultados.getLong("id_usuario"));
        gasto.setUsuarioNombre(resultados.getString("usuario_nombre"));
        return gasto;
    }
}
