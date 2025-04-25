# Sistema de Gestión de Pedidos Distribuido con Microservicios

Este proyecto implementa una arquitectura basada en **microservicios** usando **Spring Boot**, orientado a la gestión de pedidos en línea. Cada microservicio es independiente, tiene su propia base de datos y se comunica a través de servicios REST. Además, el sistema se conecta a **servicios externos** y utiliza **diferentes fuentes de datos**.

---

## 🌟 Objetivos del Proyecto

- Implementar una aplicación distribuida en microservicios.
- Utilizar múltiples bases de datos.
- Integrar servicios externos mediante REST APIs.
- Establecer comunicación interna entre microservicios.
- Desarrollar funcionalidades de un sistema de pedidos completo.

---

## 📊 Arquitectura General

```bash
microservices-project/
│
├── api-gateway/
├── discovery-server/ (opcional)
├── user-service/
├── product-service/
├── order-service/
├── payment-service/
└── docker-compose.yml (opcional)
```

---

## 📄 Microservicios Incluidos

### 1. User Service

- Registro de usuarios
- Autenticación (login)
- Perfil de usuario
- Validación desde Order Service
- **Base de datos:** MySQL

### 2. Product Service

- Listado de productos
- Búsqueda por ID o nombre
- Integración con API externa (ej: DummyJSON)
- **Base de datos:** MongoDB o API externa

### 3. Order Service

- Crear nuevo pedido
- Obtener pedidos por usuario
- Cálculo de total según productos seleccionados
- **Base de datos:** PostgreSQL

### 4. Payment Service

- Simulación de procesamiento de pagos
- Integración con API externa (ej: Stripe, ReqRes)
- Confirmación de pago
- **Base de datos:** Opcional / sin persistencia

### 5. API Gateway

- Enrutamiento entre microservicios
- Filtros de seguridad y logging

### 6. Discovery Server (opcional)

- Registro y descubrimiento automático de servicios (Eureka)

---

## 🚀 Tecnologías Utilizadas

- Java 17+
- Spring Boot
- Spring Cloud (Feign, Gateway, Eureka)
- Spring Data JPA / MongoDB
- REST APIs externas (DummyJSON, Stripe, ReqRes, etc.)
- Swagger / Springdoc OpenAPI
- MySQL, PostgreSQL, MongoDB
- Postman (para pruebas)
- Docker (opcional)
- Maven

---

## 📚 Casos de Uso

1. Registro y login de un usuario.
2. Consulta de productos disponibles.
3. Creación de un pedido desde productos seleccionados.
4. Procesamiento del pago usando un servicio externo.
5. Confirmación y almacenamiento del pedido en la base de datos.

---

## ✍️ Instalación y Ejecución (básico)

1. Clonar el repositorio.
2. Configurar los archivos `application.yml` de cada microservicio.
3. Levantar las bases de datos necesarias (MySQL, PostgreSQL, MongoDB).
4. Ejecutar cada microservicio de forma independiente.
5. Probar los endpoints con Postman o Swagger.

---

## 📃 Licencia

Este proyecto es desarrollado con fines educativos para la asignatura **Ingeniería de Software II**.

---

## 🚪 Autor

**Nombre del estudiante:** [Tu Nombre Aquí]  
**Universidad:** [Nombre de la universidad]  
**Asignatura:** Ingeniería de Software II  
**Profesor:** [Nombre del docente]

---

## ✉️ Contacto

Para consultas o sugerencias, puedes contactarme a través de [tu_email@ejemplo.com].
