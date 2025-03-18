package apiFakeGatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import java.nio.charset.StandardCharsets
import scala.concurrent.duration._
import scala.io.Source

class ClosedModelSimulation extends Simulation {

  // Configuración del protocolo HTTP
  val httpProtocol = http
    .baseUrl("https://fakestoreapi.com") // URL base
    .header("Content-Type", "application/json")

  val jsonString = {
    val stream = getClass.getResourceAsStream("/bodies/update_product.json") // Ruta del archivo JSON en recursos
    Source.fromInputStream(stream, StandardCharsets.UTF_8.name()).getLines().mkString("\n")
  }

  // Escenario con dos peticiones (GET, POST) en un modelo cerrado
  val scn = scenario("Closed Model Scenario")
    // Petición GET
    .exec(http("GET Request")
      .get("/products/2") // Primera petición GET
      .check(status.is(200))
    )
    .pause(1) // Pausa de 1 segundo entre las peticiones

    // Petición POST
    .exec(http("POST Request")
      .post("/products")
      .body(StringBody(jsonString)).asJson // Usar el JSON cargado manualmenteon
      .check(status.is(200))
    )
    .pause(1) // Pausa de 1 segundo entre las peticiones

  // Inyección de usuarios:
  // Usamos un modelo cerrado, donde los usuarios se inyectan con un `rampUsers` y un tiempo total de duración.
  setUp(
    scn.inject(rampUsers(10) during (30.seconds)) // 10 usuarios, distribuidos durante 30 segundos
  ).protocols(httpProtocol)
}
