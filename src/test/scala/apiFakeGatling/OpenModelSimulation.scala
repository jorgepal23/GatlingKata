package apiFakeGatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import java.nio.charset.StandardCharsets
import scala.concurrent.duration._
import scala.io.Source

class OpenModelSimulation extends Simulation {

  // Configuración del protocolo HTTP
  val httpProtocol = http
    .baseUrl("https://fakestoreapi.com") // URL base
    .header("Content-Type", "application/json")

  val jsonString = {
    val stream = getClass.getResourceAsStream("/bodies/update_product.json") // Ruta del archivo JSON en recursos
    Source.fromInputStream(stream, StandardCharsets.UTF_8.name()).getLines().mkString("\n")
  }

  // Escenario con tres peticiones (GET, PUT, DELETE) en un modelo abierto
  val scn = scenario("Open Model Scenario")
    // Petición GET
    .exec(http("GET Request")
      .get("/products/1") // Primera petición GET
      .check(status.is(200))
    )
    .pause(1) // Pausa de 1 segundo entre las peticiones

    // Petición PUT
    .exec(http("PUT Request")
      .put("/products/1")
      .body(StringBody(jsonString)).asJson
      .check(status.is(200))
    )
    .pause(1) // Pausa de 1 segundo entre las peticiones

    // Petición DELETE
    .exec(http("DELETE Request")
      .delete("/products/2")
      .check(status.is(200))
    )

  // Inyección de usuarios:
  // En un modelo abierto, los usuarios se inyectan de forma continua.
  setUp(
    scn.inject(
      constantUsersPerSec(10) during (30.seconds) // Inyectar 3 usuarios por segundo durante 30 segundos
    )
  ).protocols(httpProtocol)
}