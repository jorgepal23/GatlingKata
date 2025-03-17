package apiFakeGatling

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.io.Source

class PutGatling extends Simulation {

  val httpProtocol = http
    .baseUrl("https://fakestoreapi.com") // URL base de la API
    .header("Content-Type", "application/json")

  // Leer el archivo JSON manualmente
  val jsonString = Source.fromFile("src/test/resources/bodies/update_product.json").getLines().mkString("\n")

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
