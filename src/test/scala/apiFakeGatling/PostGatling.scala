package apiFakeGatling

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

import scala.io.Source

class PostGatling extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("https://fakestoreapi.com") // URL base de la API
    .header("Content-Type", "application/json")

  // Leer el archivo JSON manualmente
  val jsonString = Source.fromFile("src/test/resources/bodies/create_product.json").getLines().mkString("\n")


  // Escenario para POST
  val postScenario = scenario("POST Producto")
    .exec(http("Crear producto")
      .post("/products")
      .body(StringBody(jsonString)).asJson // Usar el JSON cargado manualmente
      .check(status.is(200))
    )
  setUp(
    postScenario.inject(atOnceUsers(15)).protocols(httpProtocol)
  )


}
