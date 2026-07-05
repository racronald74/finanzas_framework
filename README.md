# Finanzas Framework

Aplicación web desarrollada con Spring Boot para la administración de finanzas personales. El sistema permite registrar ingresos y gastos, calcular indicadores financieros y visualizar un Dashboard con información actualizada de la situación financiera del usuario.

## Estado del proyecto

Actualmente se encuentran implementados los siguientes módulos:

- Inicio de sesión.
- Dashboard con indicadores financieros.
- Gestión de ingresos.
  - Registro de ingreso fijo mensual.
  - Registro de ingresos adicionales.
  - Edición de ingresos.
  - Eliminación de ingresos.
  - Cálculo automático del total de ingresos.
- Gestión de gastos.
  - Registro de gastos.
  - Edición de gastos.
  - Eliminación de gastos.
  - Cálculo automático del total de gastos.
- Cálculo automático del saldo disponible.

## Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- Bootstrap 5
- MySQL 8
- Maven
- Git y GitHub

## Requisitos

Antes de ejecutar el proyecto es necesario tener instalado:

- Java JDK 21 o superior.
- Apache Maven 3.9 o superior.
- MySQL 8 o superior.
- Visual Studio Code, IntelliJ IDEA o cualquier IDE compatible con Spring Boot.

## Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/racronald74/finanzas_framework.git
```

### 2. Configurar la base de datos

Importar el archivo:

```
src/main/resources/database/finanzas_framework.sql
```

### 3. Configurar la conexión

Editar el archivo:

```
src/main/resources/application.properties
```

Configurar los datos de conexión de MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finanzas_framework
spring.datasource.username=root
spring.datasource.password=SU_CONTRASEÑA
```

### 4. Ejecutar la aplicación

Desde la raíz del proyecto ejecutar:

```bash
mvn spring-boot:run
```

O utilizar el botón **Run** del IDE.

### 5. Acceder desde el navegador

```
http://localhost:8080
```

## Usuario de prueba

Correo:

```
admin@finanzas.com
```

Contraseña:

```
123456
```

## Estructura general

```
src
 ├── controller
 ├── entity
 ├── repository
 ├── service
 ├── resources
 │    ├── templates
 │    ├── static
 │    └── database
 └── ...
```

## Arquitectura

El proyecto sigue una arquitectura MVC (Modelo – Vista – Controlador), organizada en capas para separar la presentación, la lógica de negocio y el acceso a los datos.

## Autor

**Ronald Arenas Campuzano**

Proyecto desarrollado como evidencia del programa **Análisis y Desarrollo de Software** del Servicio Nacional de Aprendizaje (SENA).