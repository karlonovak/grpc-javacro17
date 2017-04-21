package hr.kn.gatling.grpc.protocol.actions

import com.google.common.util.concurrent.ListenableFuture
import hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse

trait GrpcExecutableAction {
  require(name.nonEmpty)
  def name: String
  def executeAsync: ListenableFuture[GreeterResponse]
}
