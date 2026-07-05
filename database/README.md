# Base de datos

# Base de datos - Finanzas Framework

## Requisitos

- MySQL 8.0 o superior.

## Instalación

1. Crear una base de datos importando el archivo:

```
finanzas_framework.sql
```

2. Configurar la conexión a MySQL en el archivo:

```
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finanzas_framework
spring.datasource.username=root
spring.datasource.password=SU_CONTRASEÑA
```

3. Ejecutar la aplicación Spring Boot.

## Usuario de prueba

Correo:

```
admin@finanzas.com
```

Contraseña:

```
123456
```