package hr.kn.gatling.thrift.protocol

import io.gatling.core.protocol.{Protocol, ProtocolKey}


object ThriftProtocol {

  val ThriftProtocolKey = new ProtocolKey {

    override type Protocol = ThriftProtocol
    override type Components = ThriftComponents

    override def protocolClass: _root_.scala.Predef.Class[_root_.io.gatling.core.protocol.Protocol] = classOf[ThriftProtocol].asInstanceOf[Class[io.gatling.core.protocol.Protocol]]

    override def defaultValue(configuration: _root_.io.gatling.core.config.GatlingConfiguration): ThriftProtocol = throw new IllegalStateException("Can't provide a default value for ThriftProtocol")

    override def newComponents(system: _root_.akka.actor.ActorSystem, coreComponents: _root_.io.gatling.core.CoreComponents): ThriftProtocol => ThriftComponents = {
      ThriftProtocol => ThriftComponents(ThriftProtocol)
    }
  }
}

case class ThriftProtocol() extends Protocol{
  type Components = ThriftComponents
}
