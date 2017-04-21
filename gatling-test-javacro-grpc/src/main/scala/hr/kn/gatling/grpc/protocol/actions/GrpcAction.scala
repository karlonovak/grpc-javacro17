package hr.kn.gatling.grpc.protocol.actions

import akka.actor.ActorSystem
import io.gatling.core.action.{Action, ExitableActorDelegatingAction}
import io.gatling.core.stats.StatsEngine
import io.gatling.core.util.NameGen
import hr.kn.gatling.grpc.protocol.GrpcProtocol
import hr.kn.gatling.grpc.protocol.grpc.GrpcCheck

object GrpcAction extends NameGen {
  def apply(action: GrpcExecutableAction, checks: List[GrpcCheck], protocol: GrpcProtocol, system: ActorSystem, statsEngine: StatsEngine, next: Action) = {
    val actor = system.actorOf(GrpcActionActor.props(action, checks, protocol, statsEngine, next))
    new ExitableActorDelegatingAction(genName("Grpc"), statsEngine, next, actor)
  }
}


