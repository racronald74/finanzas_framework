-- Tabla aislada para evidenciar operaciones CRUD con JDBC explícito.
-- No modifica la tabla `gasto` utilizada por los módulos JPA/Hibernate.
CREATE TABLE IF NOT EXISTS gasto_jdbc_demo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    monto DECIMAL(10, 2) NOT NULL,
    fecha DATE NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    origen VARCHAR(20) NOT NULL,
    id_usuario BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_gasto_jdbc_demo_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);
