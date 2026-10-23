# Sol de Shirán Backend

API REST CRUD en Java 21, Spring Boot, Spring Data JPA, Oracle y Flyway. Se implementó en un proyecto nuevo en la raíz; `.reference-pharmasoft` se mantiene intacto y sirvió para seguir el patrón de capas de `Categoria`.

## Ejecutar

Requisitos: JDK 21, Maven y Oracle accesible. Configura las variables de entorno antes de iniciar:

```powershell
$env:DB_URL = 'jdbc:oracle:thin:@//localhost:1521/FREEPDB1'
$env:DB_USERNAME = 'USUARIO_ESQUEMA'
$env:DB_PASSWORD = 'CONTRASENA'
mvn spring-boot:run
```

Flyway crea el esquema de este proyecto en `src/main/resources/db/migration`; Hibernate valida el esquema al arrancar. Swagger UI: `http://localhost:8080/swagger-ui.html`.

Todos los recursos ofrecen `POST` (201), `GET` colección (200), `GET /{id}` (200/404), `PUT /{id}` (200) y `DELETE /{id}` (204). Los IDs y timestamps de creación no se aceptan en las solicitudes. Las relaciones se envían mediante ID. Un ID relacionado inexistente devuelve 404 y restricciones de eliminación o integridad devuelven 409.

## Ejemplos JSON

`POST /api/v1/cabanas` y `PUT /api/v1/cabanas/{id}`:

```json
{"nombre":"Cabaña Sol","descripcion":"Cabaña familiar junto al valle","aforoMaximo":8,"numHabitaciones":3,"numBanos":2,"precioBaseNoche":250.00,"estado":"ACTIVA","imagenPrincipal":"https://ejemplo.pe/sol.jpg"}
```

`POST /api/v1/cabana-imagenes` y `PUT /api/v1/cabana-imagenes/{id}`:

```json
{"idCabana":1,"urlImagen":"https://ejemplo.pe/sol-interior.jpg"}
```

`POST /api/v1/cabana-comodidades` y `PUT /api/v1/cabana-comodidades/{id}`:

```json
{"idCabana":1,"nombreComodidad":"Piscina"}
```

`POST /api/v1/reservas` y `PUT /api/v1/reservas/{id}`:

```json
{"idCabana":1,"idUsuario":25,"fechaIngreso":"2026-11-10","fechaSalida":"2026-11-12","cantidadHuespedes":4,"precioNocheAplicado":250.00,"montoTotal":500.00,"montoAdelanto":100.00,"estadoReserva":"PENDIENTE_PAGO","expiraEn":"2026-10-22T18:30:00"}
```

`POST /api/v1/transacciones-pago` y `PUT /api/v1/transacciones-pago/{id}`:

```json
{"idReserva":1,"idExternoPasarela":"orden-abc-1","monto":100.00,"moneda":"PEN","metodoPago":"TRANSFERENCIA","estadoTransaccion":"PENDIENTE"}
```

`POST /api/v1/webhook-logs` y `PUT /api/v1/webhook-logs/{id}` (la relación con transacción puede ser nula):

```json
{"idTransaccion":1,"idTransaccionPasarela":"evento-abc-1","payloadJson":"{\"evento\":\"pago.actualizado\"}","estadoProcesamiento":"PROCESADO_EXITO"}
```

En `PUT`, usa el mismo cuerpo de creación en la URL del registro existente: se busca y modifica esa entidad y sus fechas originales de creación se conservan.
