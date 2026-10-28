# Finanzas Framework

Aplicación web desarrollada con Java 21 y Spring Boot para la administración de finanzas personales. Permite registrar ingresos y gastos, calcular indicadores financieros y visualizar un dashboard con información de la situación financiera.

## Estado del proyecto

Actualmente se encuentran implementados los siguientes módulos:

- Inicio de sesión.
- Dashboard con indicadores financieros.
- Gestión de ingresos:
  - Registro de ingresos fijos mensuales y adicionales.
  - Edición y eliminación de ingresos.
  - Cálculo automático del total de ingresos.
- Gestión de gastos:
  - Registro, edición y eliminación de gastos.
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

Antes de ejecutar el proyecto, se requiere:

- Java JDK 21 o superior.
- Apache Maven 3.9 o superior.
- MySQL 8 o superior.
- Un IDE compatible con Java y Spring Boot, como Visual Studio Code o IntelliJ IDEA.

## Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/racronald74/finanzas_framework.git
cd finanzas_framework
```

### 2. Preparar la base de datos

Consulta las instrucciones de instalación y los scripts disponibles en [`database/README.md`](database/README.md).

### 3. Configurar la conexión

Edita el archivo `src/main/resources/application.properties` y configura las credenciales de tu instalación local de MySQL.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finanzas_framework?useSSL=false&serverTimezone=America/Bogota
spring.datasource.username=root
spring.datasource.password=SU_CONTRASEÑA
```

Reemplaza `SU_CONTRASEÑA` por la contraseña correspondiente a tu entorno local. No publiques credenciales reales en el repositorio.

### 4. Ejecutar la aplicación

Desde la raíz del proyecto, ejecuta:

```bash
mvn spring-boot:run
```

También puedes iniciar la aplicación desde tu IDE.

### 5. Acceder a la aplicación

Abre en el navegador:

http://localhost:8080

## Demostración académica de JDBC

El proyecto incorpora una demostración independiente para evidenciar el uso directo de JDBC con una base de datos relacional.

La demostración implementa operaciones CRUD (crear, consultar, actualizar y eliminar) sobre la tabla `gasto_jdbc_demo`, utilizando JDBC explícito mediante clases como `Connection`, `PreparedStatement` y `ResultSet`.

Para acceder a la demostración:

http://localhost:8080/demostracion-jdbc/gastos

Antes de utilizarla, es necesario preparar la tabla mediante el script `database/gasto_jdbc_demo.sql`, siguiendo las instrucciones de [`database/README.md`](database/README.md).

La demostración utiliza una tabla separada de la tabla `gasto` del módulo principal. Los módulos existentes conservan su acceso a datos mediante Spring Data JPA e Hibernate.

## Usuario de prueba

En el entorno local de desarrollo se dispone de las siguientes credenciales de prueba:

- Correo: `admin@finanzas.com`
- Contraseña: `123456`

Estas credenciales son exclusivamente para pruebas locales y no deben utilizarse como credenciales de producción.

## Estructura general

```text
src/main/java/com/finanzasframework/
├── controller/
├── dto/
├── entity/
├── enums/
├── repository/
├── service/
└── ...

src/main/resources/
├── templates/
├── static/
└── application.properties

database/
├── finanzas_framework.sql
├── gasto_jdbc_demo.sql
└── README.md
```

## Arquitectura

La aplicación sigue una arquitectura Modelo–Vista–Controlador (MVC), organizada por capas para separar las responsabilidades:

- **Controller:** recibe y gestiona las solicitudes web.
- **Service:** centraliza la lógica de negocio.
- **Repository:** gestiona el acceso a datos mediante JPA o, en la demostración académica, JDBC explícito.
- **Entity:** representa las entidades persistentes gestionadas por JPA.
- **DTO:** transporta datos entre los componentes cuando corresponde.
- **View:** utiliza Thymeleaf para presentar la información al usuario.

## Versionamiento

El proyecto utiliza Git para el control de versiones y GitHub como repositorio remoto.

Repositorio: https://github.com/racronald74/finanzas_framework

## Autor

**Ronald Arenas Campuzano**

Proyecto desarrollado como evidencia del programa Análisis y Desarrollo de Software del Servicio Nacional de Aprendizaje (SENA).