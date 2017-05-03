package hr.svgroup.ws.upload;

public class GreeterServiceImpl implements GreeterService {

	@Override
	public GreeterResponse greet(GreeterRequest request) {
		System.out.println("Got '" + request.getGreeting() + "' from " + request.getSender());
		return new GreeterResponse(request.getRequestId(), true);
	}
}
