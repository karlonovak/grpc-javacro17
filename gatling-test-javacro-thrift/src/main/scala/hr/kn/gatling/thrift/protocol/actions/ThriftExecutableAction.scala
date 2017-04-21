package hr.kn.gatling.thrift.protocol.actions

import org.apache.thrift.async.AsyncMethodCallback
import hr.kn.thrift.generated.GreeterResponse

trait ThriftExecutableAction {
  require(name.nonEmpty)
  def name: String
  def executeAsync(callback: AsyncMethodCallback[GreeterResponse], userId: Long): Unit
}
