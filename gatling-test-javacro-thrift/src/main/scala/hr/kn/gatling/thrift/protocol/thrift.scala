package hr.kn.gatling.thrift.protocol

import io.gatling.commons.validation.Success
import io.gatling.core.check.{Check, Extender, Preparer}
import hr.kn.thrift.generated.GreeterResponse

object Predef extends ThriftDsl

object thrift {
  type ThriftCheck = Check[GreeterResponse]
}
