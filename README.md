# Proyecto: Sistema de Reservas con Microservicios

Este repositorio contiene un sistema distribuido basado en **microservicios** desarrollado con **Spring Boot**, que permite gestionar productos, realizar reservas y procesar pagos, utilizando una arquitectura escalable y desacoplada. El proyecto fue diseñado como parte de un entorno educativo, simulando un sistema completo de reservas de productos.

## ✨ Características Principales

- Arquitectura basada en **microservicios**.
- Registro y descubrimiento de servicios con **Eureka Server**.
- Persistencia de datos con **MongoDB** y **MySQL**.
- Integración con servicio de API externo de pagos simulado en Stripe: https://dashboard.stripe.com
- Comunicación entre servicios mediante **REST APIs**.
- Desplegado en entorno local usando **Java 17**, **Maven** y **Spring Boot**.

---

## 🚀 Microservicios Implementados

### 1. Discovery-Service

- Implementado con **Spring Cloud Netflix Eureka**.
- Provee registro y descubrimiento de servicios en la red.

### 2. Product-Microservice

- **CRUD** de productos.
- Persistencia en **MongoDB**.
- Endpoints REST para gestión de productos.

### 3. Booking-Microservice

- **CRUD** de bookings/reservas de compra.
- Validación de disponibilidad con **Product-Service:** Las órdenes son creadas, solamente si el id del producto existe.
- Persistencia en **MySQL**.

### 4. Payment-Microservice

- Creación, consulta y eliminaación de pagos para reservas o bookings.
- Persistencia en **MySQL**.
- Conexión con la **API de pagos externa** de Stripe.
- Validación del estado de transacciones: Una orden creada actualizará su status a "pagado" si el paymenta apunta a dicha orden.

---

## ⚙️ Tecnologías Utilizadas

| Componente             | Tecnología                        |
| ---------------------- | --------------------------------- |
| Lenguaje               | Java 17                           |
| Framework              | Spring Boot, Spring Cloud Netflix |
| Bases de Datos         | MongoDB, MySQL                    |
| Cliente REST           | Postman                           |
| Gestor de dependencias | Maven                             |
| Control de versiones   | Git                               |
| IDE                    | Visual Studio Code                |
  
---

## 📂 Estructura del Proyecto

```
project-root/
├── discovery-service/
├── doc
├── product-microservice/
├── booking-microservice/
├── payment-microservice/
├── README.md
└── .gitignore
```

---

🎥 Video de Demostración

Puedes ver el funcionamiento del sistema distribuido basado en microservicios en el siguiente video:  
[🔗 Ver Video en YouTube]([URL_DEL_VIDEO](https://youtu.be/n5GrQooqtYk))

Este video muestra la interacción entre los microservicios `Product`, `Booking` y `Payment`, la comunicación vía Eureka Server, y las pruebas funcionales realizadas mediante Postman.

---

## ✅ Requisitos Previos

- Java 17
- Maven 3.8+
- MongoDB (en localhost:27017)
- MySQL (con base de datos configurada y credenciales en application.properties)
- IDE de Visual Studio Code con la extensión de Spring Boot Extension Pack.

---

## 📚 Instalación y Ejecución

1. Clona el repositorio:

```bash
git clone [https://github.com/usuario/nombre-del-repositorio](https://github.com/chartorresgg/microservices-architecture).git
cd nombre-del-repositorio
```

2. Inicia la base de datos MongoDB y MySQL en tu equipo.

3. Configura los archivos `application.properties` de cada microservicio con tus credenciales y puertos personalizados si es necesario.

4. Desde cada carpeta de microservicio, ejecuta:

```bash
mvn spring-boot:run
```

5. Accede al panel de Eureka en:

```
http://localhost:8761
```

6. Usa Postman para probar los endpoints de cada microservicio (CRUD, reservas y pagos).

---

## 🔧 Endpoints REST (Resumen)

- **Product-Microservice**

  - `GET /products`
  - `GET /products/{id}`
  - `POST /products`
  - `PUT /products/{id}`
  - `DELETE /products/{id}`

- **Booking-Microservice**

  - `GET /bookings`
  - `GET /bookings/{id}`
  - `POST /bookings`
  - `PUT /bookings/{id}`
  - `DELETE /bookings/{id}`

- **Payment-Microservice**

  - `POST /payments`
  - `GET /payments/{bookingId}`
  - `DELETE /payments/{id}`

---

## 📊 Pruebas Realizadas

- Verificación de conectividad entre microservicios via Eureka.
- Persistencia de datos en MongoDB y MySQL con Postman.
- Pruebas funcionales completas de APIs REST.
- Tolerancia a fallos mediante reinicio de servicios y monitoreo en Eureka.

---

## 🌟 Contribuciones

Este proyecto es de propósito educativo. Si deseas contribuir o extender su funcionalidad, puedes hacer un fork y enviar un Pull Request.

---

## 🚀 Futuras Mejoras

- Integración con frontend en React o Angular.
- Autenticación con OAuth2 y JWT.
- Registro centralizado de logs.
- Orquestación con Kubernetes o Docker Compose.

---

## 📅 Autor

Desarrollado por **Carlos Guzmán Torres y Dana Yuredt Castro** para el curso de Ingeniería de Software II con del Politécnico Gracnolombiano - 2025.

---

## 🌐 Licencia

Este repositorio está licenciado bajo los términos de la MIT License.
