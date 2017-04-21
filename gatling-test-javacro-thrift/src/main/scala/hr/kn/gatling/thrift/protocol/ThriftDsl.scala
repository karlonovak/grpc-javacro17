package hr.kn.gatling.thrift.protocol

import io.gatling.core.action.builder.ActionBuilder
import hr.kn.gatling.thrift.protocol.thrift.ThriftCheck
import hr.kn.gatling.thrift.protocol.actions.ThriftExecutableAction
import hr.kn.gatling.thrift.protocol.actions.impl.ThriftAsyncGreet

trait ThriftDsl {
  val THRIFT = ThriftProtocolBuilder
  def thriftCall = ThriftProcessBuilder

  implicit def thirftProtocolBuilder2thriftProtocol(builder: ThriftProtocolBuilder): ThriftProtocol = builder.build()
  implicit def thriftProcessBuilder2ActionBuilder(builder: ThriftProcessBuilder): ActionBuilder = builder.build()
}

case class ThriftProtocolBuilder() {
  def build = ThriftProtocol
}

case class ThriftProcessBuilder(action: ThriftExecutableAction, checks: List[ThriftCheck] = Nil) {
  def check(thriftCheck: ThriftCheck*) = copy(checks = checks ::: thriftCheck.toList)
  def build(): ActionBuilder = new ThriftActionBuilder(action, checks)
}
