package hr.kn.thrift.simulations

import io.gatling.core.Predef._
import scala.concurrent.duration._

import scala.io.Source
import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import hr.kn.gatling.thrift.protocol.ThriftCustomCheck
import hr.kn.gatling.thrift.protocol.actions.impl.ThriftAsyncGreet
import hr.kn.thrift.generated.GreeterRequest
import hr.kn.thrift.generated.GreeterResponse

class ThriftSimulation extends Simulation {
  import hr.kn.gatling.thrift.protocol.Predef._

  val host = "localhost"
  val port = 20001
  val clientsNum = 50
  
  val thriftProtocol = THRIFT()
 ThriftAsyncGreet.initThriftClientsForUsers(clientsNum, host, port)
 
  val request = new GreeterRequest
  request.setRequestId(123)
  request.setGreeting("Hasta la vista baby!")
  request.setSender("Arnold S")
  
  val thriftScenario = scenario("Test Thrift server").repeat(20000) {
      exec(
        thriftCall(
            ThriftAsyncGreet("Greet async Thrift", host, port, request)
          ).check(new ThriftCustomCheck((s: GreeterResponse) => {
            s.asInstanceOf[GreeterResponse].success.equals(true)
        })))
  }
  
  setUp(thriftScenario.inject(atOnceUsers(clientsNum))).maxDuration(10 minutes).protocols(thriftProtocol)
}