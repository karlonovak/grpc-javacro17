package hr.kn.grpc.test;

import hr.kn.grpc.api.GreetingServiceGrpc;
import hr.kn.grpc.api.GreetingServiceGrpc.GreetingServiceStub;
import hr.kn.grpc.api.GreetingServiceProto;
import hr.kn.grpc.api.GreetingServiceProto.GreeterRequest;
import hr.kn.grpc.api.GreetingServiceProto.GreeterResponse;
import hr.kn.grpc.server.GrpcServer;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public final class GreetingServiceTestGrpc {

	private static final String HOST = "127.0.0.1";
	private static final int PORT = 20000;

	public static void main(String[] args) throws Exception {
		final GrpcServer server = new GrpcServer(HOST, PORT);
		server.start();

		GreetingServiceStub greetingClient = buildClient();
		greetingClient.greet(request(), new GreeterObserver());
		final CountDownLatch latch = new CountDownLatch(1);
		latch.await();
	}

	private static GreetingServiceStub buildClient() {
		return GreetingServiceGrpc.newStub(ManagedChannelBuilder.forAddress(HOST, PORT).usePlaintext(true).build());
	}

	private static GreeterRequest request() {
		return GreeterRequest.newBuilder()
				.setRequestId(123)
				.setGreeting("Hasta la vista baby")
				.setSender("Arnold S").build();
	}

	static class GreeterObserver implements StreamObserver<GreetingServiceProto.GreeterResponse> {
		@Override
		public void onNext(GreeterResponse response) {
			System.out.println("Response from server: " + response.getSuccess());
		}

		@Override
		public void onError(Throwable throwable) {
			System.out.println("Error " + throwable.getMessage());
		}

		@Override
		public void onCompleted() {}
	};
}
