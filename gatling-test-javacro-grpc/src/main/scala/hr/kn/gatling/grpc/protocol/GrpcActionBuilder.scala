package hr.kn.gatling.grpc.protocol

import io.gatling.core.action.Action
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.protocol.Protocols
import io.gatling.core.structure.ScenarioContext
import hr.kn.gatling.grpc.protocol.grpc.GrpcCheck
import hr.kn.gatling.grpc.protocol.actions.GrpcAction
import hr.kn.gatling.grpc.protocol.actions.GrpcExecutableAction

case class GrpcActionBuilder(action: GrpcExecutableAction, checks: List[GrpcCheck]) extends ActionBuilder {

  def grpcProtocol(protocols: Protocols) = {
    protocols.protocol[GrpcProtocol].getOrElse(throw new UnsupportedOperationException("gRPC protocol wasn't registered"))
  }

  override def build(ctx: ScenarioContext, next: Action): Action = {
    import ctx._
    val statsEngine = coreComponents.statsEngine
    GrpcAction(action, checks, new GrpcProtocol, ctx.system, statsEngine, next)
  }
}
