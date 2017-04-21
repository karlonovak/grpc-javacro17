package hr.kn.gatling.grpc.protocol.actions.impl

import io.grpc.ManagedChannelBuilder

import scala.concurrent.Future
import com.google.common.util.concurrent.ListenableFuture
import hr.kn.gatling.grpc.protocol.actions.GrpcExecutableAction
import hr.kn.grpc.generated.GreetingServiceProto.GreeterRequest
import hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse
import hr.kn.grpc.generated.GreetingServiceGrpc.GreetingServiceFutureStub
import hr.kn.grpc.generated.GreetingServiceGrpc

object GrpcAsyncGreet {
  def apply(name: String, host: String, port: Int, request: GreeterRequest) = new GrpcAsyncGreet(name, host, port, request)
}

class GrpcAsyncGreet(val name: String, host: String, port: Int, request: GreeterRequest) extends GrpcExecutableAction {
  var channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build
  val client: GreetingServiceFutureStub = GreetingServiceGrpc.newFutureStub(channel)

  override def executeAsync: ListenableFuture[GreeterResponse] = {
    client.greet(request)
  }
}
