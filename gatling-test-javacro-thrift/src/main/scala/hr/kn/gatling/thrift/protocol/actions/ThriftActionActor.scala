package hr.kn.gatling.thrift.protocol.actions

import akka.actor.Props
import io.gatling.commons.stats.{KO, OK}
import io.gatling.commons.util.TimeHelper
import io.gatling.commons.validation.Failure
import io.gatling.core.action.{Action, ActionActor}
import io.gatling.core.check.Check
import io.gatling.core.session.Session
import io.gatling.core.stats.StatsEngine
import io.gatling.core.stats.message.ResponseTimings
import org.apache.thrift.async.AsyncMethodCallback
import hr.kn.gatling.thrift.protocol.thrift.ThriftCheck
import hr.kn.gatling.thrift.protocol.ThriftProtocol
import hr.kn.gatling.thrift.protocol.actions.impl.ThriftAsyncGreet
import hr.kn.thrift.generated.GreeterResponse

object ThriftActionActor {
  def props(action: ThriftExecutableAction, checks: List[ThriftCheck], protocol: ThriftProtocol, statsEngine: StatsEngine, next: Action): Props = {
    Props(new ThriftActionActor(action, checks, protocol, statsEngine, next))
  }
}

class ThriftActionActor(action: ThriftExecutableAction,
                      checks: List[ThriftCheck],
                      protocol: ThriftProtocol,
                      val statsEngine: StatsEngine,
                      val next: Action) extends ActionActor {

  override def execute(session: Session): Unit = {
    val startTime = TimeHelper.nowMillis
    var optionalResult: Option[GreeterResponse] = None
    var optionalThrowable : Option[Throwable] = None

    try {
      action match {
        case act: ThriftAsyncGreet => {
          val callback = new GreetCallback
          act.executeAsync(callback, session.userId)
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
    
    
    class GreetCallback extends AsyncMethodCallback[GreeterResponse] {
      def onComplete(response: GreeterResponse): Unit = {
        try {
          logResult(Option(response))
        } catch {
          case e: Exception => logResult(None, Some(e))
        }
      }
    
      def onError(e: Exception): Unit = {
        System.out.println("Error : ");
        e.printStackTrace();
      }
    }
    
  }
}