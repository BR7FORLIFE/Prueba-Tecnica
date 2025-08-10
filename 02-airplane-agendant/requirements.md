# ✈️ API de Reservas de Vuelos – Prueba Técnica

## 📌 Descripción
API REST para la gestión de vuelos y reservas, implementada con **Spring Boot**.  
Incluye autenticación **JWT**, persistencia con **Spring Data JPA**, validación de datos, pruebas unitarias e integración.

---

## 🎯 Objetivos de la prueba
- Implementar un backend con **Spring Web**, **Spring Security JWT**, **Spring Data JPA** y **Spring Test**.
- Aplicar **buenas prácticas** de arquitectura y código limpio.
- Asegurar la **seguridad** y **correcto manejo de datos**.

---

## 📂 Requerimientos funcionales

### 1. Usuarios
- **Registro:** `POST /auth/register`
- **Login:** `POST /auth/login`
- Roles:
  - `PASSENGER` (pasajero)
  - `ADMIN` (administrador)

### 2. Vuelos
- Crear vuelo → `POST /flights` (solo `ADMIN`)
- Listar vuelos → `GET /flights` (cualquier usuario autenticado)
- Buscar vuelos por origen/destino/fecha → `GET /flights/search`
- Eliminar vuelo → `DELETE /flights/{id}` (solo `ADMIN`)

### 3. Reservas
- Reservar asiento → `POST /bookings` (solo `PASSENGER`)
- Listar reservas propias → `GET /bookings/mine` (solo `PASSENGER`)
- Cancelar reserva → `DELETE /bookings/{id}` (solo `PASSENGER`)
- Listar todas las reservas → `GET /bookings` (solo `ADMIN`)

---

## 🛠️ Requerimientos técnicos
- **Java 17+**
- **Spring Boot**
- **Spring Web**
- **Spring Security + JWT**
- **Spring Data JPA + Hibernate**
- **Base de datos:** PostgreSQL (H2 para tests)
- **Validación:** `@Valid` + Bean Validation
- **Documentación:** Swagger / OpenAPI
- **Pruebas:** Spring Test + JUnit + Mockito

---

## 🏛️ Arquitectura
