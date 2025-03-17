package apiFakeGatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class OpenModelSimulation extends Simulation {

  // Configuración de protocolo HTTP
  val httpProtocol = http
    .baseUrl("https://jsonplaceholder.typicode.com")  // Usa una URL de ejemplo
    .header("Content-Type", "application/json")

  // Escenario para enviar tres peticiones HTTP
  val scn = scenario("Multiple Requests Scenario")
    .exec(http("GET Request 1")
      .get("/posts/1")  // Primera petición GET
      .check(status.is(200))
    )
    .exec(http("GET Request 2")
      .get("/posts/2")  // Segunda petición GET
      .check(status.is(200))
    )
    .exec(http("GET Request 3")
      .get("/posts/3")  // Tercera petición GET
      .check(status.is(200))
    )

  // Configuración del escenario y número de usuarios
  setUp(
    scn.inject(atOnceUsers(1))  // Ejecutar con un único usuario
  ).protocols(httpProtocol)
}