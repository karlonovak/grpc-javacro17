package hr.kn.grpc.server;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.NettyServerBuilder;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class GrpcServer {

	private final ExecutorService executor;
	private final Server server;
	private static final Integer THREADS = 4;

	private final List<BindableService> SERVICES = Lists.newArrayList(new GreetingServiceImplGrpc());

	public GrpcServer(String hostname, Integer port) {
		this.executor = serverExecutor();
		final ServerBuilder<NettyServerBuilder> builder = NettyServerBuilder.forAddress(new InetSocketAddress(hostname, port));
		SERVICES.forEach(builder::addService);
		this.server = builder.executor(executor).build();
	}

	private static ExecutorService serverExecutor() {
		return Executors.newFixedThreadPool(THREADS, new ThreadFactoryBuilder().setNameFormat("rpc-server-%d").build());
	}

	public void start() {
		try {
			server.start();
			System.out.println("Started server");
		} catch (Exception e) {
			throw new RuntimeException("Could not start server", e);
		}
	}

	public void stop() {
		try {
			System.out.println("Stopping " + this);
			executor.shutdown();
			server.shutdown();
			System.out.println("Stopped " + this);
		} catch (Exception e) {
			System.out.println("Interrupted while shutting down " + this);
		}
	}
}