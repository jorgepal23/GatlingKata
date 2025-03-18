package apiFakeGatling

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import java.nio.charset.StandardCharsets
import scala.io.Source

class PutGatling extends Simulation {

  val httpProtocol = http
    .baseUrl("https://fakestoreapi.com") // URL base de la API
    .header("Content-Type", "application/json")

  val jsonString = {
    val stream = getClass.getResourceAsStream("/bodies/update_product.json") // Ruta del archivo JSON en recursos
    Source.fromInputStream(stream, StandardCharsets.UTF_8.name()).getLines().mkString("\n")
  }

  // Escenario para PUT
  val putScenario = scenario("PUT Producto")
    .exec(http("Actualizar producto")
      .put("/products/1")
      .body(StringBody(jsonString)).asJson // Usar el JSON cargado manualmente
      .check(status.is(200))
    )
  setUp(
    putScenario.inject(atOnceUsers(15)).protocols(httpProtocol)
  )

}
