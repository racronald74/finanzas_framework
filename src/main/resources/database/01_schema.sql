/*
=========================================================
Proyecto : Finanzas Framework
Autor    : Ronald Arenas
Motor    : MySQL 8
=========================================================

Script de creación de la base de datos.
*/

CREATE DATABASE IF NOT EXISTS finanzas_framework
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE finanzas_framework;

/*
---------------------------------------------------------
Tabla: usuario
---------------------------------------------------------
*/

CREATE TABLE IF NOT EXISTS usuario (

    id BIGINT AUTO_INCREMENT,

    nombre VARCHAR(100) NOT NULL,

    correo VARCHAR(100) NOT NULL,

    contrasena VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),

    UNIQUE (correo)

);