package hr.kn.gatling.grpc.protocol

import io.gatling.commons.validation.{Failure, Validation}
import io.gatling.core.check.CheckResult
import io.gatling.core.session.Session
import scala.collection.mutable
import hr.kn.gatling.grpc.protocol.grpc.GrpcCheck
import hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse

case class GrpcCustomCheck(func: GreeterResponse => Boolean) extends GrpcCheck {
  override def check(response: GreeterResponse, session: Session)(implicit cache: mutable.Map[Any, Any]): Validation[CheckResult] = {
    func(response) match {
      case true => CheckResult.NoopCheckResultSuccess
      case _ => Failure("Grpc check failed")
    }
  }
}
