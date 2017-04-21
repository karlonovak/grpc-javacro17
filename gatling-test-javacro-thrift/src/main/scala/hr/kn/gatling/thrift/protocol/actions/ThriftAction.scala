package hr.kn.gatling.thrift.protocol.actions

import akka.actor.ActorSystem
import io.gatling.core.action.{Action, ExitableActorDelegatingAction}
import io.gatling.core.stats.StatsEngine
import io.gatling.core.util.NameGen
import hr.kn.gatling.thrift.protocol.thrift.ThriftCheck
import hr.kn.gatling.thrift.protocol.ThriftProtocol

object ThriftAction extends NameGen {
  def apply(action: ThriftExecutableAction, checks: List[ThriftCheck], protocol: ThriftProtocol, system: ActorSystem, statsEngine: StatsEngine, next: Action) = {
    val actor = system.actorOf(ThriftActionActor.props(action, checks, protocol, statsEngine, next))
    new ExitableActorDelegatingAction(genName("Grpc"), statsEngine, next, actor)
  }
}


