package hr.kn.gatling.grpc.protocol

import io.gatling.commons.validation.Success
import io.gatling.core.check.{Check, Extender, Preparer}
import hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse

object Predef extends GrpcDsl

object grpc {
  type GrpcCheck = Check[GreeterResponse]
}
