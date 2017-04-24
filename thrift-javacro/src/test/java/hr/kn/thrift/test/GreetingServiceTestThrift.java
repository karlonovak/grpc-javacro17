package hr.kn.thrift.test;

import java.io.IOException;

import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.transport.TNonblockingSocket;

import hr.kn.thrift.generated.GreeterRequest;
import hr.kn.thrift.generated.GreeterResponse;
import hr.kn.thrift.generated.GreetingServiceThrift;
import hr.kn.thrift.generated.GreetingServiceThrift.AsyncClient;
import hr.kn.thrift.server.ThriftServer;

public final class GreetingServiceTestThrift {

	private static final String HOST = "127.0.0.1";
	private static final int PORT = 20001;

	public static void main(String[] args) throws Exception {
		final ThriftServer server = new ThriftServer();
		new Thread(() -> server.start(PORT)).start();

		AsyncClient client = buildClient();
		client.greet(request(), new GreetCallback());
	}

	private static AsyncClient buildClient() throws IOException {
		return new GreetingServiceThrift.AsyncClient(new TBinaryProtocol.Factory(), new TAsyncClientManager(), new TNonblockingSocket(HOST, PORT));
	}

	private static GreeterRequest request() {
		return new GreeterRequest(123, "Arnold S", "Hasta la vista baby");
	}

	static class GreetCallback implements AsyncMethodCallback<GreeterResponse> {
		@Override
		public void onComplete(GreeterResponse response) {
			System.out.println("Response from server: " + response.success);
		}

		@Override
		public void onError(Exception exception) {
			System.out.println("Error " + exception.getMessage());
		}
	}
}
