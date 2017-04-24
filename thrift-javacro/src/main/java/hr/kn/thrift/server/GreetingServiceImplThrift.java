package hr.kn.thrift.server;

import org.apache.thrift.TException;

import hr.kn.thrift.generated.GreeterRequest;
import hr.kn.thrift.generated.GreeterResponse;
import hr.kn.thrift.generated.GreetingServiceThrift;

public class GreetingServiceImplThrift implements GreetingServiceThrift.Iface {

	public GreeterResponse greet(GreeterRequest request) throws TException {
		System.out.println("Got '" + request.getGreeting() + "' from " + request.getSender());
		return new GreeterResponse(request.getRequestId(), true);
	}
}