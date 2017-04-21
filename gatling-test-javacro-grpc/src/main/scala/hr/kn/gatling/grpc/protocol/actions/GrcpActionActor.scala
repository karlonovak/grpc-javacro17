package hr.kn.gatling.grpc.protocol.actions

import akka.actor.Props
import io.gatling.commons.stats.{KO, OK}
import io.gatling.commons.util.TimeHelper
import io.gatling.commons.validation.Failure
import io.gatling.core.action.{Action, ActionActor}
import io.gatling.core.check.Check
import io.gatling.core.session.Session
import io.gatling.core.stats.StatsEngine
import io.gatling.core.stats.message.ResponseTimings
import com.google.common.util.concurrent.ListenableFuture
import com.google.common.util.concurrent.FutureCallback
import com.google.common.util.concurrent.Futures
import hr.kn.gatling.grpc.protocol.grpc.GrpcCheck
import hr.kn.gatling.grpc.protocol.GrpcProtocol
import hr.kn.gatling.grpc.protocol.actions.impl.GrpcAsyncGreet
import hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse

object GrpcActionActor {
  def props(action: GrpcExecutableAction, checks: List[GrpcCheck], protocol: GrpcProtocol, statsEngine: StatsEngine, next: Action): Props = {
    Props(new GrpcActionActor(action, checks, protocol, statsEngine, next))
  }
}

class GrpcActionActor(action: GrpcExecutableAction,
                      checks: List[GrpcCheck],
                      protocol: GrpcProtocol,
                      val statsEngine: StatsEngine,
                      val next: Action) extends ActionActor {

  override def execute(session: Session): Unit = {
    val startTime = TimeHelper.nowMillis
    var optionalResult: Option[GreeterResponse] = None
    var optionalThrowable : Option[Throwable] = None

    try {
      action match {
        case act: GrpcAsyncGreet => {
          val asyncResponse: ListenableFuture[GreeterResponse] = act.executeAsync

          Futures.addCallback(asyncResponse, new FutureCallback[GreeterResponse] {
            def onFailure(t: Throwable): Unit = {
              logResult(None, Some(t))
            }
          
            def onSuccess(result: GreeterResponse): Unit = {
              optionalResult = Option(result)
              logResult(optionalResult, None)
            }
          })
        }
        case _ => throw new UnsupportedOperationException("Action is not supported")
      }
    }
    catch {
      case t: Throwable => {
        optionalThrowable = Some(t)
        logResult(None, optionalThrowable)
      }
    }

    def logResult(maybeResult: Option[GreeterResponse], error: Option[Throwable] = None) = {
      val endTime = TimeHelper.nowMillis
      val timings = ResponseTimings(startTime, endTime)

      if (error.isEmpty) {
        val result = maybeResult.get
        if (Option(result).nonEmpty) {
          val (newSession, error) = Check.check(result, session, checks)
          error match {
            case None => {
              statsEngine.logResponse(session, action.name, timings, OK, None, None)
              next ! newSession(session)
            }
            case Some(Failure(errorMessage)) => {
              statsEngine.logResponse(session, action.name, timings, KO, None, Some(errorMessage))
              next ! newSession(session).markAsFailed
            }
          }
        }
        else {
          statsEngine.logResponse(session, action.name, timings, KO, None, Some(s"Error during the call!"))
          next ! session.markAsFailed
        }
      }
      else {
        val throwable = error.get
        statsEngine.logResponse(session, action.name, timings, KO, None, Some(throwable.getMessage))
        next ! session.markAsFailed
      }
    }
  }
}