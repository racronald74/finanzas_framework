# Base de datos - Finanzas Framework

Este directorio contiene los scripts SQL necesarios para preparar la base de datos de Finanzas Framework y la tabla independiente utilizada en la demostración académica de JDBC.

## Requisitos

- MySQL 8.0 o superior.
- Un cliente para ejecutar scripts SQL, como MySQL Workbench o la consola de MySQL.
- La aplicación configurada con las credenciales de acceso a la base de datos.

## Archivos SQL

| Archivo | Descripción |
|---|---|
| `finanzas_framework.sql` | Script de la base de datos principal de Finanzas Framework. |
| `gasto_jdbc_demo.sql` | Script para crear la tabla utilizada en la demostración de JDBC explícito. |

## 1. Preparar la base de datos principal

Ejecuta el archivo `finanzas_framework.sql` utilizando MySQL Workbench o un cliente compatible.

Este script corresponde a la base de datos utilizada por los módulos principales de la aplicación, como gastos, ingresos y dashboard.

Si la base de datos ya está instalada y contiene información, revisa el contenido del script antes de ejecutarlo para evitar modificaciones no deseadas.

## 2. Configurar la conexión

Abre el archivo:

`src/main/resources/application.properties`

Configura los datos de conexión correspondientes a tu instalación local de MySQL.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/finanzas_framework?useSSL=false&serverTimezone=America/Bogota
spring.datasource.username=root
spring.datasource.password=SU_CONTRASEÑA
```

Reemplaza `SU_CONTRASEÑA` por la contraseña de tu entorno local. No publiques credenciales reales en GitHub.

## 3. Preparar la demostración JDBC

Ejecuta el archivo `gasto_jdbc_demo.sql` después de preparar la base de datos principal.

Este script crea la tabla `gasto_jdbc_demo`, utilizada exclusivamente para demostrar operaciones CRUD mediante JDBC explícito.

La tabla contiene información del gasto, como monto, fecha, descripción, origen e identificador del usuario. También mantiene una clave foránea hacia la tabla `usuario`.

La demostración utiliza esta tabla independiente para no modificar directamente los registros de la tabla `gasto` que utiliza el módulo principal.

Antes de ejecutar el script, revisa su contenido y verifica que la tabla `usuario` exista.

## 4. Ejecutar la demostración

Inicia Finanzas Framework desde la raíz del proyecto:

```bash
mvn spring-boot:run
```

Después, abre en el navegador:

http://localhost:8080/demostracion-jdbc/gastos

Desde esta pantalla puedes realizar las operaciones CRUD implementadas para la demostración:

- Crear un registro de prueba.
- Consultar los registros existentes.
- Editar un registro.
- Eliminar un registro.

La demostración utiliza JDBC explícito mediante conexiones, sentencias SQL parametrizadas y lectura de resultados. Los módulos principales continúan utilizando Spring Data JPA e Hibernate.

## 5. Usuario de prueba

Para las pruebas locales se dispone del siguiente usuario:

- Correo: `admin@finanzas.com`
- Contraseña: `123456`

El usuario debe existir en la tabla `usuario` para poder asociar los registros de la demostración.

Estas credenciales son exclusivamente para el entorno local de desarrollo.

## 6. Precauciones

- No confundas la tabla `gasto_jdbc_demo` con la tabla `gasto` de la aplicación principal.
- Utiliza registros de prueba para comprobar las operaciones CRUD.
- Evita eliminar o modificar registros reales durante las pruebas.
- Revisa los scripts SQL antes de ejecutarlos sobre una base de datos que contenga información importante.
- No guardes contraseñas reales en los archivos versionados.

## Documentación relacionada

Consulta el [README principal](../README.md) para conocer la descripción general de Finanzas Framework, sus módulos, tecnologías y arquitectura.