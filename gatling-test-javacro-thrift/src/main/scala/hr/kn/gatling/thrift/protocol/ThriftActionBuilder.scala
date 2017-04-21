package hr.kn.gatling.thrift.protocol

import io.gatling.core.action.Action
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.protocol.Protocols
import io.gatling.core.structure.ScenarioContext
import hr.kn.gatling.thrift.protocol.actions.ThriftExecutableAction
import hr.kn.gatling.thrift.protocol.thrift.ThriftCheck
import hr.kn.gatling.thrift.protocol.actions.ThriftAction

case class ThriftActionBuilder(action: ThriftExecutableAction, checks: List[ThriftCheck]) extends ActionBuilder{

  def thriftProtocol(protocols: Protocols) = {
    protocols.protocol[ThriftProtocol].getOrElse(throw new UnsupportedOperationException("Thrift protocol wasn't registered"))
  }

  override def build(ctx: ScenarioContext, next: Action): Action = {
    import ctx._
    val statsEngine = coreComponents.statsEngine
    ThriftAction(action, checks, new ThriftProtocol, ctx.system, statsEngine, next)
  }
}
