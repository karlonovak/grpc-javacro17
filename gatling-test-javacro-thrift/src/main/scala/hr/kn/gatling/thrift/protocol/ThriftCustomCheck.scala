package hr.kn.gatling.thrift.protocol

import io.gatling.commons.validation.{Failure, Validation}
import io.gatling.core.check.CheckResult
import io.gatling.core.session.Session
import scala.collection.mutable
import hr.kn.gatling.thrift.protocol.thrift.ThriftCheck
import hr.kn.thrift.generated.GreeterResponse

case class ThriftCustomCheck(func: GreeterResponse => Boolean) extends ThriftCheck {
  override def check(response: GreeterResponse, session: Session)(implicit cache: mutable.Map[Any, Any]): Validation[CheckResult] = {
    func(response) match {
      case true => CheckResult.NoopCheckResultSuccess
      case _ => Failure("Thrift check failed")
    }
  }
}
