package apiFakeGatling

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class DeleteGatling extends Simulation{

  val httpProtocol: HttpProtocolBuilder = (http baseUrl "https://fakestoreapi.com") // URL base de la API
    .header("Content-Type", "application/json")

  // Escenario para DELETE
  val deleteScenario: ScenarioBuilder = scenario("DELETE Producto")
    .exec(http("Eliminar producto")
      .delete("/products/2")
      .check(status.is(200))
    )
  setUp(
    deleteScenario.inject(atOnceUsers(15)).protocols(httpProtocol)
  )
}
