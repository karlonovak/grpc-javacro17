package hr.kn.springboot.simulations

import io.gatling.core.Predef._
import scala.concurrent.duration._

import scala.io.Source
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class SpringBootSimulation extends Simulation {

  val http_headers = Map(
    "Accept-Encoding" -> "gzip,deflate",
    "Content-Type" -> "text/json;charset=UTF-8")
  
  val httpProtocol = http.baseURL("http://localhost:8080")
                         .header("Connection", "Keep-Alive")
                         .shareConnections

  val request = 
    http("Greet sync Spring")
      .post("/greet")
      .headers(http_headers)
      .body(StringBody
          ("""{"requestId": 123,
            	"sender": "Arnold S",
            	"greeting": "Hasta la vista baby!"}""")).asJSON
  
  val springBootScenario = scenario("Test Spring Boot server").repeat(20000) {
    exec(request.check(substring("true").exists))
  }

  setUp(springBootScenario.inject(atOnceUsers(50))).maxDuration(10 minutes).protocols(httpProtocol)
}