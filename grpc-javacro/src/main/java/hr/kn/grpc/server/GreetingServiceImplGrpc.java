package hr.kn.grpc.server;

import hr.kn.grpc.api.GreetingServiceGrpc.GreetingServiceImplBase;
import hr.kn.grpc.api.GreetingServiceProto.GreeterRequest;
import hr.kn.grpc.api.GreetingServiceProto.GreeterResponse;
import io.grpc.stub.StreamObserver;

public final class GreetingServiceImplGrpc extends GreetingServiceImplBase {

	@Override
	public void greet(GreeterRequest request, StreamObserver<GreeterResponse> observer) {
		System.out.println("Got '" + request.getGreeting() + "' from " + request.getSender());
		observer.onNext(response(request.getRequestId()));
		observer.onCompleted();
	}

	private static GreeterResponse response(long requestId) {
		return GreeterResponse.newBuilder().setRequestId(requestId).setSuccess(true).build();
	}
}
