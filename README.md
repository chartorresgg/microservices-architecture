# Microservicios Spring Boot

Este proyecto implementa una arquitectura de microservicios usando Spring Boot. Fue desarrollado como parte de la asignatura **Ingeniería de Software II**.

## 🎯 Objetivo

Demostrar el uso de:
- Arquitectura de microservicios
- Diferentes fuentes y bases de datos (MySQL, PostgreSQL, MongoDB)
- Consumo de APIs externas
- Comunicación entre microservicios (REST, Feign)
- Spring Cloud Gateway y Eureka

## 🧩 Servicios

- `user-service`: Gestión de usuarios (MySQL)
- `product-service`: Gestión de productos (MongoDB o API externa)
- `order-service`: Gestión de pedidos (PostgreSQL)
- `payment-service`: Procesamiento de pagos (servicio simulado o API externa)
- `gateway-service`: API Gateway
- `discovery-service`: Eureka Server (opcional)

## 🚀 Tecnologías

- Java 24
- Spring Boot 3.x
- Spring Cloud
- Maven
- Docker (opcional)
- Git & GitHub
- Postman

## 🧪 Desarrollo

Cada microservicio tiene su propio repositorio y base de datos, simulando una arquitectura desacoplada.

## 📚 Autor

Desarrollado por: *Carlos Eduardo Guzmán Torres y Dana Yuredt Castro Cuevas*  
Asignatura: Ingeniería de Software II  
Docente: *Wilson Eduardo Soto Forero*