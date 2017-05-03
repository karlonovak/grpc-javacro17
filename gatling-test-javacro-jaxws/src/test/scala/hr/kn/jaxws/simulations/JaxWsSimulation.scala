package hr.kn.jaxws.simulations

import io.gatling.core.Predef._
import scala.concurrent.duration._

import scala.io.Source
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class JaxWsSimulation extends Simulation {

  val http_headers = Map(
    "Accept-Encoding" -> "gzip,deflate",
    "Content-Type" -> "text/json;charset=UTF-8")
  
  val httpProtocol = http.baseURL("http://localhost:8080/services")
                         .header("Connection", "Keep-Alive")
                         .shareConnections

  val request = 
    http("Greet sync Jax WS")
      .post("/greet")
      .headers(http_headers)
      .body(StringBody
          ("""<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                xmlns:v1="http://kn.hr/greet/ws/test/v1.0">
               <soapenv:Header/>
               <soapenv:Body>
                  <v1:greet>
                     <greetRequest>
                        <requestId>123</requestId>
                        <sender>Arnold S</sender>
                        <greeting>Hasta la vista baby</greeting>
                     </greetRequest>
                  </v1:greet>
               </soapenv:Body>
            </soapenv:Envelope>""")).asXML
  
  val jaxWsScenario = scenario("Test JAX WS server").repeat(20000) {
    exec(request.check(substring("true").exists))
  }

  setUp(jaxWsScenario.inject(atOnceUsers(50))).maxDuration(10 minutes).protocols(httpProtocol)
}