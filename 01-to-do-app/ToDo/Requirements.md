# 🧪 Prueba Técnica – Backend Java con Spring Boot

## 🎯 Objetivo

Desarrollar una API REST para la gestión de tareas personales (To-Do App), con usuarios autenticados mediante JWT. Cada usuario puede crear, consultar y eliminar **solo sus propias tareas**.

Queremos ver cómo diseñás, estructurás y resolvés un backend con Spring Boot, incluyendo autenticación, persistencia con SQL y buenas prácticas.

---

## 🚀 Lo que tenés que hacer

### Funcionalidades obligatorias

1. **Registro y login de usuarios**
   - `POST /auth/register`: crear un nuevo usuario (guardar username y password hasheado con BCrypt).
   - `POST /auth/login`: autenticar y devolver un JWT.

2. **Gestión de tareas personales**
   - `GET /tareas`: devuelve las tareas del usuario autenticado.
   - `POST /tareas`: crea una nueva tarea para ese usuario.
   - `GET /tareas/{id}`: devuelve una tarea solo si pertenece al usuario.
   - `DELETE /tareas/{id}`: elimina una tarea solo si pertenece al usuario.

---

### Requisitos técnicos

🔐 **Seguridad**
- Implementar autenticación y autorización con JWT (no OAuth).
- Usar `Spring Security` para proteger los endpoints.
- Solo `/auth/*` debe estar público.

🧱 **Persistencia**
- No usar JPA ni Hibernate.
- Usar `JdbcTemplate` para toda la capa de datos.
- Crear tablas `usuarios` y `tareas` en un script `schema.sql`.

🧪 **Testing**
- Escribir al menos 3 tests:
  - Login exitoso y fallido.
  - Acceso no autorizado a tareas.
  - Creación de tarea autenticada.
- Usar `@SpringBootTest` o `@WebMvcTest` donde corresponda.

📦 **Otros**
- Validaciones (`@Valid`) para inputs.
- Manejo de errores global con `@ControllerAdvice`.
- Uso de `BCryptPasswordEncoder` para guardar contraseñas.
- Base de datos: H2 en memoria (o PostgreSQL si preferís).
- Estructura organizada por capas (controller, service, repository).

---

## ✅ Entregables

1. Código fuente en GitHub o archivo ZIP.
2. Archivo `README.md` explicando:
   - Cómo correr el proyecto.
   - Cómo probar los endpoints.
   - Qué supuestos hiciste (si hubo ambigüedades).
3. Script `schema.sql` para crear las tablas.

---

## 💡 Tips (no obligatorios, pero suman)

- Usar DTOs para inputs y outputs.
- Usar `Record` si estás en Java 17+.
- Swagger en `/swagger-ui.html` si tenés tiempo.
- Logging claro (`Slf4j` o similar).

---

## 📌 Ejemplo de tablas

```sql
CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(20) DEFAULT 'USER'
);

CREATE TABLE tareas (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255),
    completada BOOLEAN DEFAULT FALSE,
    usuario_id BIGINT,
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);
