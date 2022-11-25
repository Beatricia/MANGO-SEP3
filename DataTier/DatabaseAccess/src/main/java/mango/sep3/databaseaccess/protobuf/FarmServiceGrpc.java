package mango.sep3.databaseaccess.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: contract.proto")
public final class FarmServiceGrpc {

  private FarmServiceGrpc() {}

  public static final String SERVICE_NAME = "FarmService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Farm,
      mango.sep3.databaseaccess.protobuf.Void> getCreateFarmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateFarm",
      requestType = mango.sep3.databaseaccess.protobuf.Farm.class,
      responseType = mango.sep3.databaseaccess.protobuf.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Farm,
      mango.sep3.databaseaccess.protobuf.Void> getCreateFarmMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Farm, mango.sep3.databaseaccess.protobuf.Void> getCreateFarmMethod;
    if ((getCreateFarmMethod = FarmServiceGrpc.getCreateFarmMethod) == null) {
      synchronized (FarmServiceGrpc.class) {
        if ((getCreateFarmMethod = FarmServiceGrpc.getCreateFarmMethod) == null) {
          FarmServiceGrpc.getCreateFarmMethod = getCreateFarmMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.Farm, mango.sep3.databaseaccess.protobuf.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateFarm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Farm.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Void.getDefaultInstance()))
              .setSchemaDescriptor(new FarmServiceMethodDescriptorSupplier("CreateFarm"))
              .build();
        }
      }
    }
    return getCreateFarmMethod;
  }

  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text,
      mango.sep3.databaseaccess.protobuf.Farm> getGetFarmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetFarm",
      requestType = mango.sep3.databaseaccess.protobuf.Text.class,
      responseType = mango.sep3.databaseaccess.protobuf.Farm.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text,
      mango.sep3.databaseaccess.protobuf.Farm> getGetFarmMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text, mango.sep3.databaseaccess.protobuf.Farm> getGetFarmMethod;
    if ((getGetFarmMethod = FarmServiceGrpc.getGetFarmMethod) == null) {
      synchronized (FarmServiceGrpc.class) {
        if ((getGetFarmMethod = FarmServiceGrpc.getGetFarmMethod) == null) {
          FarmServiceGrpc.getGetFarmMethod = getGetFarmMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.Text, mango.sep3.databaseaccess.protobuf.Farm>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetFarm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Text.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Farm.getDefaultInstance()))
              .setSchemaDescriptor(new FarmServiceMethodDescriptorSupplier("GetFarm"))
              .build();
        }
      }
    }
    return getGetFarmMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FarmServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FarmServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FarmServiceStub>() {
        @java.lang.Override
        public FarmServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FarmServiceStub(channel, callOptions);
        }
      };
    return FarmServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FarmServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FarmServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FarmServiceBlockingStub>() {
        @java.lang.Override
        public FarmServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FarmServiceBlockingStub(channel, callOptions);
        }
      };
    return FarmServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FarmServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FarmServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FarmServiceFutureStub>() {
        @java.lang.Override
        public FarmServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FarmServiceFutureStub(channel, callOptions);
        }
      };
    return FarmServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class FarmServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void createFarm(mango.sep3.databaseaccess.protobuf.Farm request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateFarmMethod(), responseObserver);
    }

    /**
     */
    public void getFarm(mango.sep3.databaseaccess.protobuf.Text request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Farm> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetFarmMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateFarmMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.Farm,
                mango.sep3.databaseaccess.protobuf.Void>(
                  this, METHODID_CREATE_FARM)))
          .addMethod(
            getGetFarmMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.Text,
                mango.sep3.databaseaccess.protobuf.Farm>(
                  this, METHODID_GET_FARM)))
          .build();
    }
  }

  /**
   */
  public static final class FarmServiceStub extends io.grpc.stub.AbstractAsyncStub<FarmServiceStub> {
    private FarmServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FarmServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FarmServiceStub(channel, callOptions);
    }

    /**
     */
    public void createFarm(mango.sep3.databaseaccess.protobuf.Farm request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateFarmMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getFarm(mango.sep3.databaseaccess.protobuf.Text request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Farm> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetFarmMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FarmServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<FarmServiceBlockingStub> {
    private FarmServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FarmServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FarmServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.Void createFarm(mango.sep3.databaseaccess.protobuf.Farm request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateFarmMethod(), getCallOptions(), request);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.Farm getFarm(mango.sep3.databaseaccess.protobuf.Text request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetFarmMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FarmServiceFutureStub extends io.grpc.stub.AbstractFutureStub<FarmServiceFutureStub> {
    private FarmServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FarmServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FarmServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.Void> createFarm(
        mango.sep3.databaseaccess.protobuf.Farm request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateFarmMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.Farm> getFarm(
        mango.sep3.databaseaccess.protobuf.Text request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetFarmMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_FARM = 0;
  private static final int METHODID_GET_FARM = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FarmServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FarmServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_FARM:
          serviceImpl.createFarm((mango.sep3.databaseaccess.protobuf.Farm) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void>) responseObserver);
          break;
        case METHODID_GET_FARM:
          serviceImpl.getFarm((mango.sep3.databaseaccess.protobuf.Text) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Farm>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FarmServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FarmServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return mango.sep3.databaseaccess.protobuf.Contract.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FarmService");
    }
  }

  private static final class FarmServiceFileDescriptorSupplier
      extends FarmServiceBaseDescriptorSupplier {
    FarmServiceFileDescriptorSupplier() {}
  }

  private static final class FarmServiceMethodDescriptorSupplier
      extends FarmServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FarmServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FarmServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FarmServiceFileDescriptorSupplier())
              .addMethod(getCreateFarmMethod())
              .addMethod(getGetFarmMethod())
              .build();
        }
      }
    }
    return result;
  }
}
