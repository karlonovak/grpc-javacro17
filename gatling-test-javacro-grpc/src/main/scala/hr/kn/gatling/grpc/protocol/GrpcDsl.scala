package hr.kn.gatling.grpc.protocol

import io.gatling.core.action.builder.ActionBuilder
import hr.kn.gatling.grpc.protocol.grpc.GrpcCheck
import hr.kn.gatling.grpc.protocol.actions.GrpcExecutableAction

trait GrpcDsl {
  val GRPC = GrpcProtocolBuilder
  def grpcCall = GrpcProcessBuilder

  implicit def grpcProtocolBuilder2grpcProtocol(builder: GrpcProtocolBuilder): GrpcProtocol = builder.build()
  implicit def grpcProcessBuilder2ActionBuilder(builder: GrpcProcessBuilder): ActionBuilder = builder.build()
}

case class GrpcProtocolBuilder() {
  def build = GrpcProtocol
}

case class GrpcProcessBuilder(action: GrpcExecutableAction, checks: List[GrpcCheck] = Nil) {
  def check(grpcCheck: GrpcCheck*) = copy(checks = checks ::: grpcCheck.toList)
  def build(): ActionBuilder = new GrpcActionBuilder(action, checks)
}