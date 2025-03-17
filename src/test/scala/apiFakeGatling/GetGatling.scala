package apiFakeGatling

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class GetGatling extends Simulation {
  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("https://fakestoreapi.com") // URL base de la API
    .header("Content-Type", "application/json")

  // Escenario para GET
  val getScenario: ScenarioBuilder = scenario("GET Productos")
    .exec(http("Obtener productos")
      .get("/products/1")
      .check(status.is(200))
    )

  setUp(
    getScenario.inject(atOnceUsers(15)).protocols(httpProtocol)
  )

}
