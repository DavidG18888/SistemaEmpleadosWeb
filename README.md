# SistemaEmpleadosWeb

Aplicación web Java Spring Boot para registrar, consultar, editar y eliminar empleados, con base de datos Turso (LibSQL).

## Variables de entorno
- `TURSO_DATABASE_URL`: URL de Turso (por ejemplo `libsql://nombre-db-nombreusuario.turso.io` o `https://...turso.io`)
- `TURSO_AUTH_TOKEN`: token de acceso

## Ejecutar local
1. Configurar las dos variables de entorno.
2. Ejecutar `mvn spring-boot:run`.
3. Abrir http://localhost:8080

## Render
Este repositorio incluye `render.yaml`. En Render, conectar el repositorio y configurar las variables de entorno mencionadas.

## Funciones
- Listado de empleados
- Búsqueda por nombre, apellido, cargo y departamento
- Crear, editar y eliminar
- Salario y estado
- Diseño adaptable a móvil
