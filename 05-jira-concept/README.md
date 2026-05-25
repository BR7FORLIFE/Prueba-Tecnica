# TaskFlow — Prueba Técnica Full Stack

## Resumen del proyecto
TaskFlow es una plataforma interna para la gestión de proyectos y tareas de equipos de desarrollo, con un diseño inspirado en Jira/Trello/Asana.

## Stack elegido
- Backend: `NestJS` + `TypeScript`
- Base de datos: `PostgreSQL`
- Frontend: `React` + `TypeScript`
- Estilos: `Tailwind CSS` o `CSS Modules`

## Funcionalidades implementadas
### Autenticación
- Registro de usuario
- Login con JWT
- Roles: `ADMIN`, `MEMBER`

### Proyectos
- Crear proyecto
- Listar proyectos
- Actualizar proyecto
- Eliminar proyecto

### Tareas
- Crear tarea
- Listar tareas
- Actualizar tarea
- Eliminar tarea

### Reglas de negocio
- No se puede eliminar un proyecto con tareas activas
- Solo `ADMIN` puede eliminar proyectos
- Un usuario solo puede editar tareas asignadas a él
- Validaciones de entrada con DTOs
- Manejo global de errores

## API REST esperada
```http
POST /auth/register
POST /auth/login
GET /projects
POST /projects
PATCH /projects/:id
DELETE /projects/:id
GET /tasks
POST /tasks
PATCH /tasks/:id
DELETE /tasks/:id
```

## Arquitectura propuesta
### Backend
- Modularización por dominios: `AuthModule`, `UsersModule`, `ProjectsModule`, `TasksModule`
- Patrones: Repository, Service, Controller
- Validaciones con DTOs y `class-validator`
- Guards para autenticación y autorización
- Logger estructurado
- Documentación con Swagger/OpenAPI
- Docker + `docker-compose`
- Tests unitarios e integración

### Frontend
- Componentes reutilizables y escalables
- Gestión de datos remotos con React Query
- Rutas protegidas
- Manejo de errores claro
- Skeleton loaders para UX de carga
- Formularios reutilizables
- Diseño responsive

## Entidades principales
### Usuario
```json
{
  "id": 1,
  "name": "Juan",
  "email": "juan@test.com",
  "password": "hashed",
  "role": "ADMIN"
}
```

### Proyecto
```json
{
  "id": 1,
  "name": "Sistema ERP",
  "description": "Proyecto interno"
}
```

### Tarea
```json
{
  "id": 1,
  "title": "Implement login",
  "description": "JWT auth",
  "status": "IN_PROGRESS",
  "priority": "HIGH",
  "assignedTo": 2,
  "projectId": 1
}
```

### Estados y prioridades
- Estados: `TODO`, `IN_PROGRESS`, `DONE`
- Prioridades: `LOW`, `MEDIUM`, `HIGH`

## Requerimientos mínimos de frontend
1. Login
   - Formulario con validación
   - Manejo de errores
   - Guardar JWT
2. Dashboard
   - Total de tareas
   - Tareas por estado
   - Tareas por prioridad
3. Lista de proyectos
   - Crear, editar, eliminar proyectos
4. Board de tareas
   - Columnas Kanban: `TODO`, `IN_PROGRESS`, `DONE`
   - Mostrar tareas por proyecto

## Extras Semi Senior incluidos
### Backend
- Swagger/OpenAPI
- Docker
- Unit testing
- Integration testing

### Frontend
- React Query / TanStack Query
- Rutas protegidas
- Skeleton loaders
- Formularios reutilizables

## Decisiones técnicas
- `NestJS` facilita una arquitectura modular, con guards y decoradores listos para auth y validaciones.
- DTOs permiten validación robusta y control de datos de entrada.
- Roles server-side evitan fugas de autorización desde el frontend.
- React Query reduce la complejidad de manejo de datos remotos y mejora cacheo y refetch.

## Mejores prácticas clave
- Separación de responsabilidades entre controllers, services y repositorios.
- Manejo global de errores para respuestas consistentes.
- Commit de infraestructura para Docker y documentación OpenAPI.
- Componentes frontend responsables y hooks de datos reutilizables.

## Preguntas de arquitectura y razonamiento
### ¿Por qué elegiste esta arquitectura?
Porque separa responsabilidades, facilita pruebas y permite escalar. `NestJS` ofrece módulos y guards que encajan bien con sistemas de auth y reglas de negocio.

### ¿Cómo escalarías esto?
- Separar servicios por bounded contexts (`auth`, `projects`, `tasks`).
- Usar cache (Redis) para consultas frecuentes.
- Añadir paginación y filtros dinámicos antes de escalar la base de datos.
- Escalar horizontalmente con contenedores.

### ¿Cómo manejarías concurrencia?
- Usar transacciones en PostgreSQL.
- Aplicar bloqueos pesimistas/optimistas en cambios críticos.
- Validar versión o fila antes de actualizar recursos compartidos.

### ¿Cómo evitarías N+1 queries?
- Usar joins y relaciones explícitas en el ORM.
- Prefetch o `leftJoinAndSelect` en consultas de proyecto/tarea.
- Agrupar cargas de datos en consultas más grandes.

### ¿Qué harías si el sistema crece a microservicios?
- Separar `auth`, `projects` y `tasks` en servicios independientes.
- Usar mensajería/eventos para sincronización.
- Implementar API Gateway y contratos claros.
- Mantener una base de datos por servicio o módulos bien delimitados.

### ¿Cuándo usarías global state?
- Para auth, tema global y settings de usuario.
- No usaría global state para listas de datos que React Query ya maneja mejor.
- Usaría global state para permisos y estado de autenticación.

### ¿Cómo optimizas renders?
- Memorizar componentes con `React.memo`.
- Usar `useMemo`/`useCallback` cuando hay props derivadas.
- Evitar re-render de listas con keys estables.
- Delegar datos remotos a React Query para evitar renders extra.

### ¿Qué problemas resuelve React Query?
- Cache automática de datos.
- Refetch y revalidación eficiente.
- Estado global de carga/errores centralizado.
- Sincronización de datos con el servidor.

### ¿Cómo manejarías permisos por rol?
- Backend: guards/middleware validando `role` en JWT.
- Frontend: rutas y componentes condicionados a permisos.
- UI: ocultar y deshabilitar acciones no permitidas.

## Notas finales
TaskFlow debe ser un MVP sólido que demuestre:
- Arquitectura bien estructurada
- Autenticación y autorización sólida
- Validaciones y manejo de errores
- Buen diseño de frontend
- Documentación clara y reproducible

> Esta propuesta es ideal para una prueba semi senior: cumple requisitos esenciales, incluye extras valiosos y deja espacio para mejoras futuras.
