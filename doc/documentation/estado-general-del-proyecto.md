# Estado General del Proyecto

**Repositorio:** microservices-architecture
**Fecha del análisis:** 2026-08-31
**Rama analizada:** `main` (commit `e110f9c`)
**Alcance:** los 4 microservicios (`discovery`, `product`, `booking`, `payment`), sus `pom.xml`, configuraciones, código fuente, documentación e historial de Git.

---

## 📖 Tabla de Contenidos

- [Resumen Ejecutivo](#resumen-ejecutivo)
- [Inventario de Servicios](#inventario-de-servicios)
- [🔴 Hallazgos Críticos](#-hallazgos-críticos)
- [🟡 Hallazgos Importantes](#-hallazgos-importantes)
- [🟢 Hallazgos Menores e Higiene de Código](#-hallazgos-menores-e-higiene-de-código)
- [Ausencias de Arquitectura](#ausencias-de-arquitectura)
- [Plan de Remediación Sugerido](#plan-de-remediación-sugerido)

---

## Resumen Ejecutivo

El proyecto es un **prototipo académico funcional pero frágil**. Cumple su objetivo pedagógico —cuatro servicios Spring Boot con registro en Eureka, dos motores de persistencia distintos e integración con una pasarela de pagos externa—, pero presenta dos problemas estructurales que impiden considerarlo listo para un entorno real:

1. **Credenciales expuestas** en el repositorio y en el historial de Git.
2. **El descubrimiento de servicios no se usa en la práctica**: aunque Eureka está levantado y los servicios se registran, la comunicación entre ellos se hace con URLs y puertos fijos escritos en el código y en la configuración.

La última actividad funcional data del **21 de mayo de 2025** (`f8a1dcb`); los commits posteriores son exclusivamente de documentación.

---

## Inventario de Servicios

| Servicio | Puerto | Persistencia | Spring Boot | Spring Cloud | Estado |
| --- | --- | --- | --- | --- | --- |
| `discovery-microservice` | 8761 | — | 3.4.5 | 2024.0.1 | Correcto |
| `product-microservice` | `0` (aleatorio) | MongoDB | 3.4.5 | 2021.0.8 (mal declarado) | Funcional; el puerto aleatorio rompe a sus consumidores |
| `booking-microservice` | 8082 | MySQL | 3.4.5 | 2021.0.8 (mal declarado) | Funcional; su integración con `product` está rota |
| `payment-microservice` | 8083 | MySQL + Stripe | 3.3.11 | 2023.0.1 | Funcional; se integra por URL fija |

---

## 🔴 Hallazgos Críticos

### 1. Secretos en el repositorio y en el historial de Git

- `booking-microservice/src/main/resources/application.properties:6` — contraseña del usuario `root` de MySQL en texto plano.
- `payment-microservice/src/main/resources/application.properties:9` — la misma contraseña de MySQL en texto plano.
- `payment-microservice/src/main/resources/application.properties:19` — clave secreta de Stripe (`sk_test_...`).

Agrava el hallazgo que el commit `74a5f45` (2025-04-29) se titula *"fix: remove Stripe secret key from application.properties"*: la clave **fue retirada una vez y volvió a añadirse**. Borrarla del archivo no la borra del historial, por lo que sigue siendo recuperable con `git log -p` desde cualquier clon del repositorio.

**Acción:** rotar la clave en el dashboard de Stripe y cambiar la contraseña de MySQL, y luego externalizar ambos valores a variables de entorno:

```properties
spring.datasource.password=${MYSQL_PASSWORD}
stripe.api.secret.key=${STRIPE_SECRET_KEY}
```

### 2. El descubrimiento de servicios no se usa; hay URLs y puertos fijos

- `booking-microservice/src/main/resources/application.properties:22` define `product.service.url=http://localhost:60648/api`. Ese es un **puerto aleatorio capturado en una ejecución concreta**, mientras que `product-microservice` arranca con `server.port=0`, es decir, con un puerto distinto en cada arranque. La integración se rompe en cada reinicio. El propio log lo confirma:

  ```
  HttpClientErrorException$NotFound: 404 on GET request for
  "http://localhost:60648/api/products/10": [no body]
  ```

- `payment-microservice/.../client/BookingClient.java:41` construye la URL con `http://localhost:8082` escrito directamente en el código.

**Acción:** anotar el `RestTemplate` con `@LoadBalanced` y usar el nombre lógico del servicio (`http://product-microservice/api/products/...`), o migrar a OpenFeign. Nótese que la dependencia `spring-cloud-starter-openfeign` **ya está declarada** en `payment-microservice/pom.xml` pero no existe ni un `@EnableFeignClients` ni una sola interfaz `@FeignClient`: es una dependencia muerta.

### 3. Identificadores incoherentes en el flujo de reserva y pago

- `booking-microservice/.../model/Booking.java:19` declara `@Id private String id` **sin `@GeneratedValue`**, y `BookingService.createBooking()` nunca lo asigna. El identificador debe llegar en el cuerpo de la petición; si llega nulo, la inserción falla.
- `PaymentRequest.bookingId` es de tipo `Long`, mientras que el identificador de `Booking` es `String`. El flujo completo solo funciona mientras los identificadores sean numéricos por convención tácita; cualquier id alfanumérico lo rompe.

**Acción:** generar el identificador en el servidor (UUID, o `Long` con `@GeneratedValue`) y unificar el tipo entre ambos servicios.

### 4. El flujo de pago carece de garantías transaccionales

En `PaymentService.processPayment()`, tras confirmar el cobro en Stripe se invoca `bookingClient.updateBookingStatus(...)`. Si esa llamada falla, `BookingClient` captura la excepción y solo la imprime con `System.err.println`. El resultado es un **cobro efectuado en Stripe con la reserva permanentemente en estado `Pending`**, sin reintento, sin compensación y sin registro estructurado del fallo.

Tampoco hay idempotencia: un doble `POST /api/payments` genera dos cobros reales.

**Acción:** registrar el pago antes de confirmarlo, propagar el fallo, añadir reintentos con `@Retryable` o un mecanismo de compensación, y aceptar una clave de idempotencia en la petición.

---

## 🟡 Hallazgos Importantes

### Versiones descoordinadas entre servicios

`payment-microservice` usa Spring Boot 3.3.11 con Spring Cloud 2023.0.1, mientras el resto usa Spring Boot 3.4.5 con Spring Cloud 2024.0.1. Conviene alinear todos los servicios en un mismo par de versiones.

### POMs mal formados en `product` y `booking`

En ambos, el BOM `spring-cloud-dependencies` (`<type>pom</type>`, `<scope>import</scope>`) está declarado dentro de `<dependencies>` en lugar de `<dependencyManagement>`, con la versión **2021.0.8**, incompatible con Spring Boot 3.4.5. Como compensación, el cliente de Eureka aparece con una versión fijada a mano (`4.2.0`). La combinación compila por casualidad, no por diseño.

### Ausencia de pruebas

Los cuatro servicios contienen únicamente el `contextLoads()` autogenerado. No hay ninguna prueba de la lógica de negocio, y esos mismos tests fallan si MySQL, MongoDB o Eureka no están levantados, porque cargan el contexto completo.

### Sin validación de entrada ni manejo global de errores

No se usa Bean Validation (`@Valid`, `@NotNull`, `@Positive`). `BookingService.createBooking()` lanza `RuntimeException("Producto no encontrado")`, que llega al cliente como **HTTP 500** cuando debería ser un 404. No existe ningún `@ControllerAdvice`.

### Entidades de persistencia expuestas como contrato de la API

`product` y `booking` devuelven directamente sus entidades JPA/Mongo en las respuestas REST. Solo `payment` define un DTO (`PaymentRequest`). Esto acopla el esquema de base de datos al contrato público de la API.

### Archivos que no deberían estar versionados

- `logs/booking-service.log` (4,6 MB) y `booking-microservice/logs/*` están registrados en Git pese a las reglas del `.gitignore`, porque se añadieron antes de que existieran esas reglas.
- El `.gitignore` de la raíz ignora `.mvn/`, cuando el Maven Wrapper **sí debe versionarse** para que el proyecto sea reproducible.

### El README no coincide con el código

| README | Ruta real |
| --- | --- |
| `GET /products` | `GET /api/products` |
| `GET /bookings` | `GET /api/bookings` |
| `GET /payments/{bookingId}` | `GET /api/payments/{id}` (por id de pago, no de reserva) |
| `discovery-service/` | `discovery-microservice/` |

Además, falta documentar el endpoint `PUT /api/bookings/{id}/status`, que es el que usa `payment` para cerrar el flujo.

---

## 🟢 Hallazgos Menores e Higiene de Código

- `PaymentService` define `deletePayment(Long)` y `deletePaymentById(Long)` con cuerpos idénticos; además los Javadoc están cruzados (el que dice "crea un pago" documenta a `getAllPayments`) y las llaves de cierre están mal indentadas.
- Paquete redundante: `com.edu.poli.payment_microservice.payment_microservice`.
- El paquete `Model` de `product-microservice` va en mayúscula, contra la convención de Java.
- `@EntityScan` está aplicada sobre la clase `Product`; es una anotación de configuración de la aplicación, no de la entidad.
- Estados de reserva en idiomas mezclados: `"Pending"` al crear, `"pagado"` al confirmar el pago. Conviene un `enum`.
- El JDK instalado en el entorno es **Java 25**, mientras los POMs declaran `java.version=17`. Además `mvn` no está en el `PATH`: usar `./mvnw` y verificar que la compilación sigue siendo limpia.

---

## Ausencias de Arquitectura

No existen API Gateway, Config Server, circuit breakers (Resilience4j), contenedores (Docker / Docker Compose) ni integración continua. El README ya los recoge en su sección de "Futuras Mejoras", por lo que se documentan aquí como omisiones conscientes y no como defectos.

---

## Plan de Remediación Sugerido

| # | Acción | Prioridad | Motivo |
| --- | --- | --- | --- |
| 1 | Rotar la clave de Stripe y la contraseña de MySQL; externalizar ambas a variables de entorno | Crítica | Están expuestas en el repositorio y en el historial |
| 2 | Sustituir las URLs fijas por descubrimiento (`@LoadBalanced` u OpenFeign) | Crítica | Sin esto el sistema no arranca dos veces seguidas |
| 3 | Generar el id de `Booking` en el servidor y unificar su tipo con `PaymentRequest` | Crítica | Rompe el flujo de creación y de pago |
| 4 | Propagar y reintentar el fallo de actualización de estado tras el cobro | Alta | Evita cobros huérfanos |
| 5 | Unificar versiones de Spring Boot / Spring Cloud y corregir el `<dependencyManagement>` | Alta | Los POMs compilan por casualidad |
| 6 | Purgar los logs versionados y corregir el `.gitignore` (dejar de ignorar `.mvn/`) | Media | Higiene del repositorio |
| 7 | Añadir Bean Validation y un `@ControllerAdvice` global | Media | Códigos de estado HTTP correctos |
| 8 | Escribir pruebas unitarias de servicios con Mockito | Media | Hoy la cobertura de lógica es nula |
| 9 | Alinear el README con las rutas reales (`/api/...`) | Baja | La documentación induce a error |
