package hr.kn.thrift.server;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.THsHaServer.Args;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import generated.GreetingServiceThrift;

public class ThriftServer {
	public void start(Integer port) {
		try {
			GreetingServiceImplThrift handler = new GreetingServiceImplThrift();
			GreetingServiceThrift.Processor<GreetingServiceThrift.Iface> processor =
					new GreetingServiceThrift.Processor<GreetingServiceThrift.Iface>(handler);

			TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(port);
			Args options = new THsHaServer.Args(serverSocket);
			options.processor(processor);
			options.minWorkerThreads(4);
			options.protocolFactory(new TBinaryProtocol.Factory());
			THsHaServer server = new THsHaServer(options);

			System.out.println("Server starting on port " + port);
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
}