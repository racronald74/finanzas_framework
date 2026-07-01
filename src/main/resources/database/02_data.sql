/*
=========================================================
Datos iniciales
=========================================================
*/

USE finanzas_framework;

INSERT IGNORE INTO usuario
(
    nombre,
    correo,
    contrasena
)

VALUES
(
    'Administrador',
    'admin@finanzas.com',
    '123456'
);