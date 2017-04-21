package hr.kn.gatling.thrift.protocol.actions.impl

import scala.concurrent.Future
import org.apache.thrift.transport.TNonblockingTransport
import org.apache.thrift.transport.TNonblockingServerSocket
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.transport.TFramedTransport
import org.apache.thrift.transport.TSocket
import org.apache.thrift.transport.TNonblockingSocket
import org.apache.thrift.async.TAsyncClientManager
import org.apache.thrift.async.AsyncMethodCallback
import org.apache.thrift.TException
import hr.kn.gatling.thrift.protocol.actions.ThriftExecutableAction
import hr.kn.thrift.generated.GreetingServiceThrift
import hr.kn.thrift.generated.GreeterRequest
import hr.kn.thrift.generated.GreeterResponse

object ThriftAsyncGreet {

  val asyncClientManager = new TAsyncClientManager()
  val clients = collection.mutable.Map.empty[Long, GreetingServiceThrift.AsyncClient]

  def initThriftClientsForUsers(clientsNum: Long, host: String, port: Int) = {
    for (i <- 1L to clientsNum) {
      clients += (i -> generateClient(host, port))
    }
  }

  def generateClient(host: String, port: Int) = new GreetingServiceThrift.AsyncClient(new TBinaryProtocol.Factory(), asyncClientManager, new TNonblockingSocket(host, port))
  def apply(name: String, host: String, port: Int, request: GreeterRequest) = new ThriftAsyncGreet(name, host, port, request)
}

class ThriftAsyncGreet(val name: String, host: String, port: Int, request: GreeterRequest) extends ThriftExecutableAction {
  override def executeAsync(callback: AsyncMethodCallback[GreeterResponse], userId: Long): Unit = {
    ThriftAsyncGreet.clients(userId).greet(request, callback)
  }
}
