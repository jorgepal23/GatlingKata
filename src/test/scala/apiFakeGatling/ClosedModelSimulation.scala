package apiFakeGatling

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ClosedModelSimulation extends Simulation {

  // Configuración del protocolo HTTP
  val httpProtocol = http
    .baseUrl("https://jsonplaceholder.typicode.com")  // URL base
    .header("Content-Type", "application/json")

  // Escenario con tres peticiones secuenciales en un modelo cerrado
  val scn = scenario("Closed Model Scenario")
    .exec(http("GET Request 1")
      .get("/posts/1")  // Primera petición GET
      .check(status.is(200))
    )
    .pause(1)  // Pausa de 1 segundo entre las peticiones
    .exec(http("GET Request 2")
      .get("/posts/2")  // Segunda petición GET
      .check(status.is(200))
    )
    .pause(1)  // Pausa de 1 segundo entre las peticiones
    .exec(http("GET Request 3")
      .get("/posts/3")  // Tercera petición GET
      .check(status.is(200))
    )

  // Inyección de usuarios:
  // Usamos un modelo cerrado, donde los usuarios se inyectan con un `rampUsers` y un tiempo total de duración.
  setUp(
    scn.inject(rampUsers(10) during (30.seconds))  // 10 usuarios, distribuidos durante 30 segundos
  ).protocols(httpProtocol)
}
