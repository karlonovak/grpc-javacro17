package hr.kn.grpc.simulations

import io.gatling.core.Predef._
import scala.concurrent.duration._

import scala.io.Source
import hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse
import hr.kn.gatling.grpc.protocol.GrpcCustomCheck
import hr.kn.grpc.generated.GreetingServiceProto.GreeterRequest
import hr.kn.gatling.grpc.protocol.actions.impl.GrpcAsyncGreet

class GrpcSimulation extends Simulation {
  import hr.kn.gatling.grpc.protocol.Predef._

  val host = "localhost"
  val port = 20000

  val grpcProtocol = GRPC()

  val request = GreeterRequest.newBuilder()
    .setRequestId(123)
    .setGreeting("Hasta la vista baby!")
		.setSender("Arnold S")
		.build();
  
  val grpcScenario = scenario("Test gRPC server").repeat(20000) {
      exec(grpcCall(GrpcAsyncGreet("Greet async gRPC", host, port, request)).check(new GrpcCustomCheck((s: GreeterResponse) => {
        s.asInstanceOf[GreeterResponse].getSuccess.equals(true)
      })))
  }

  setUp(grpcScenario.inject(atOnceUsers(50))).maxDuration(10 minutes).protocols(grpcProtocol)
}