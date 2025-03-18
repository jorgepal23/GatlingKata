# Pruebas de Gatling - Métodos Abierto y Cerrado

Este repositorio contiene ejemplos de simulaciones de carga utilizando **Gatling** con dos enfoques principales de inyección de usuarios: **método abierto** y **método cerrado**.

## Requisitos Previos

Para ejecutar las simulaciones de carga utilizando **Gatling**, debes cumplir con los siguientes requisitos:

1. **Instalar Gatling**:
  - Descarga Gatling desde su sitio web oficial: [https://gatling.io/download](https://gatling.io/download).
  - Descomprime el archivo descargado y agrega el directorio `bin` de Gatling a tu `PATH` para poder ejecutarlo desde la terminal.

2. **Instalar Java**:
  - Asegúrate de tener **Java 8 o superior** instalado en tu sistema.

3. **IntelliJ IDEA (Opcional)**:
  - Si prefieres usar un IDE, **IntelliJ IDEA** es una excelente opción para trabajar con Gatling, especialmente si ya estás familiarizado con el entorno.

4. **Ejecutar las Simulaciones**:
  - Abre la terminal y navega al directorio donde tienes el archivo de simulación.
  - Ejecuta el comando `gatling:test` para ejecutar la simulación.
  - Revisa el directorio `target/gatling/` para encontrar los resultados de la simulación.
  - Abre el reporte HTML generado por Gatling para visualizar los resultados de la simulación.
  - Puedes ejecutar multiples simulaciones al mismo tiempo utilizando el flag `-Dgatling.runMultipleSimulations=true`

## Métodos de Inyección de Usuarios

### 1. Método Abierto (Open Model)

En el **modelo abierto**, los usuarios se inyectan a lo largo del tiempo según una tasa constante. Esto significa que los usuarios pueden llegar de forma continua a lo largo de la duración de la simulación.

#### Cómo ejecutar una simulación con el modelo abierto:

1. **Abrir o crear el archivo de simulación**:
   En el archivo `OpenModelSimulation.scala`, tienes un ejemplo de simulación utilizando el modelo abierto. La configuración inyecta un número fijo de usuarios durante un período de tiempo específico.

2. **Ejemplo de código** (`OpenModelSimulation.scala`):

   ```scala
   package apiFakeGatling

   import io.gatling.core.Predef._
   import io.gatling.http.Predef._
   import scala.concurrent.duration._

   class OpenModelSimulation extends Simulation {

     val httpProtocol = http
       .baseUrl("https://jsonplaceholder.typicode.com")  // URL base
       .header("Content-Type", "application/json")

     val scn = scenario("Open Model Scenario")
       .exec(http("GET Request 1")
         .get("/posts/1")
         .check(status.is(200))
       )
       .pause(1)
       .exec(http("GET Request 2")
         .get("/posts/2")
         .check(status.is(200))
       )

     setUp(
       scn.inject(constantUsersPerSec(10) during (30.seconds))  // 10 usuarios por segundo durante 30 segundos
     ).protocols(httpProtocol)
   }

## Métodos de Inyección de Usuarios

### 1. Método Abierto (Open Model)

En el **modelo abierto**, los usuarios se inyectan a lo largo del tiempo según una tasa constante. Esto significa que los usuarios pueden llegar de forma continua a lo largo de la duración de la simulación.

#### Cómo ejecutar una simulación con el modelo abierto:

1. **Abrir o crear el archivo de simulación**:
   En el archivo `OpenModelSimulation.scala`, tienes un ejemplo de simulación utilizando el modelo abierto. La configuración inyecta un número fijo de usuarios durante un período de tiempo específico.

2. **Ejemplo de código** (`OpenModelSimulation.scala`):

   ```scala
   package apiFakeGatling

   import io.gatling.core.Predef._
   import io.gatling.http.Predef._
   import scala.concurrent.duration._

   class OpenModelSimulation extends Simulation {

     val httpProtocol = http
       .baseUrl("https://jsonplaceholder.typicode.com")  // URL base
       .header("Content-Type", "application/json")

     val scn = scenario("Open Model Scenario")
       .exec(http("GET Request 1")
         .get("/posts/1")
         .check(status.is(200))
       )
       .pause(1)
       .exec(http("GET Request 2")
         .get("/posts/2")
         .check(status.is(200))
       )

     setUp(
       scn.inject(constantUsersPerSec(10) during (30.seconds))  // 10 usuarios por segundo durante 30 segundos
     ).protocols(httpProtocol)
   }

## Generación de Reportes

Gatling genera automáticamente reportes detallados de las simulaciones que se almacenan en la carpeta target/gatling/. Los reportes incluyen métricas sobre el rendimiento de las pruebas, como el tiempo de respuesta, el número de peticiones, el número de usuarios, etc.

### Pasos para ver los reportes:

Después de ejecutar la simulación, ve a la carpeta target/gatling/ en tu proyecto.
Abre el archivo index.html generado en la carpeta correspondiente a la simulación.
El reporte te proporcionará una visión general del desempeño de la API bajo la carga de usuarios simulados.

¡Gracias por leer este guía de uso de Gatling para API Fake! Esperamos que te sirva de ayuda para simular tus pruebas de API utilizando Gatling. ¡Que tengas un excelente dia!
