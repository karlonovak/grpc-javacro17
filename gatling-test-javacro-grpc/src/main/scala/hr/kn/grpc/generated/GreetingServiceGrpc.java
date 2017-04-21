package hr.kn.grpc.generated;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.1.2)",
    comments = "Source: greetingService.proto")
public class GreetingServiceGrpc {

  private GreetingServiceGrpc() {}

  public static final String SERVICE_NAME = "javacro.GreetingService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<hr.kn.grpc.generated.GreetingServiceProto.GreeterRequest,
      hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse> METHOD_GREET =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "javacro.GreetingService", "Greet"),
          io.grpc.protobuf.ProtoUtils.marshaller(hr.kn.grpc.generated.GreetingServiceProto.GreeterRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreetingServiceStub newStub(io.grpc.Channel channel) {
    return new GreetingServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreetingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreetingServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static GreetingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreetingServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GreetingServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void greet(hr.kn.grpc.generated.GreetingServiceProto.GreeterRequest request,
        io.grpc.stub.StreamObserver<hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GREET, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GREET,
            asyncUnaryCall(
              new MethodHandlers<
                hr.kn.grpc.generated.GreetingServiceProto.GreeterRequest,
                hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse>(
                  this, METHODID_GREET)))
          .build();
    }
  }

  /**
   */
  public static final class GreetingServiceStub extends io.grpc.stub.AbstractStub<GreetingServiceStub> {
    private GreetingServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingServiceStub(channel, callOptions);
    }

    /**
     */
    public void greet(hr.kn.grpc.generated.GreetingServiceProto.GreeterRequest request,
        io.grpc.stub.StreamObserver<hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GREET, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GreetingServiceBlockingStub extends io.grpc.stub.AbstractStub<GreetingServiceBlockingStub> {
    private GreetingServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse greet(hr.kn.grpc.generated.GreetingServiceProto.GreeterRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GREET, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GreetingServiceFutureStub extends io.grpc.stub.AbstractStub<GreetingServiceFutureStub> {
    private GreetingServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse> greet(
        hr.kn.grpc.generated.GreetingServiceProto.GreeterRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GREET, getCallOptions()), request);
    }
  }

  private static final int METHODID_GREET = 0;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreetingServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(GreetingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GREET:
          serviceImpl.greet((hr.kn.grpc.generated.GreetingServiceProto.GreeterRequest) request,
              (io.grpc.stub.StreamObserver<hr.kn.grpc.generated.GreetingServiceProto.GreeterResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class GreetingServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return hr.kn.grpc.generated.GreetingServiceProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreetingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreetingServiceDescriptorSupplier())
              .addMethod(METHOD_GREET)
              .build();
        }
      }
    }
    return result;
  }
}
