package mango.sep3.databaseaccess.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: contract.proto")
public final class OfferServiceGrpc {

  private OfferServiceGrpc() {}

  public static final String SERVICE_NAME = "OfferService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Offer,
      mango.sep3.databaseaccess.protobuf.Offer> getCreateOfferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateOffer",
      requestType = mango.sep3.databaseaccess.protobuf.Offer.class,
      responseType = mango.sep3.databaseaccess.protobuf.Offer.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Offer,
      mango.sep3.databaseaccess.protobuf.Offer> getCreateOfferMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Offer, mango.sep3.databaseaccess.protobuf.Offer> getCreateOfferMethod;
    if ((getCreateOfferMethod = OfferServiceGrpc.getCreateOfferMethod) == null) {
      synchronized (OfferServiceGrpc.class) {
        if ((getCreateOfferMethod = OfferServiceGrpc.getCreateOfferMethod) == null) {
          OfferServiceGrpc.getCreateOfferMethod = getCreateOfferMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.Offer, mango.sep3.databaseaccess.protobuf.Offer>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateOffer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Offer.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Offer.getDefaultInstance()))
              .setSchemaDescriptor(new OfferServiceMethodDescriptorSupplier("CreateOffer"))
              .build();
        }
      }
    }
    return getCreateOfferMethod;
  }

  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Void,
      mango.sep3.databaseaccess.protobuf.OfferItems> getGetOffersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetOffers",
      requestType = mango.sep3.databaseaccess.protobuf.Void.class,
      responseType = mango.sep3.databaseaccess.protobuf.OfferItems.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Void,
      mango.sep3.databaseaccess.protobuf.OfferItems> getGetOffersMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Void, mango.sep3.databaseaccess.protobuf.OfferItems> getGetOffersMethod;
    if ((getGetOffersMethod = OfferServiceGrpc.getGetOffersMethod) == null) {
      synchronized (OfferServiceGrpc.class) {
        if ((getGetOffersMethod = OfferServiceGrpc.getGetOffersMethod) == null) {
          OfferServiceGrpc.getGetOffersMethod = getGetOffersMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.Void, mango.sep3.databaseaccess.protobuf.OfferItems>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetOffers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Void.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.OfferItems.getDefaultInstance()))
              .setSchemaDescriptor(new OfferServiceMethodDescriptorSupplier("GetOffers"))
              .build();
        }
      }
    }
    return getGetOffersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OfferServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OfferServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OfferServiceStub>() {
        @java.lang.Override
        public OfferServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OfferServiceStub(channel, callOptions);
        }
      };
    return OfferServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OfferServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OfferServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OfferServiceBlockingStub>() {
        @java.lang.Override
        public OfferServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OfferServiceBlockingStub(channel, callOptions);
        }
      };
    return OfferServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OfferServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OfferServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OfferServiceFutureStub>() {
        @java.lang.Override
        public OfferServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OfferServiceFutureStub(channel, callOptions);
        }
      };
    return OfferServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class OfferServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void createOffer(mango.sep3.databaseaccess.protobuf.Offer request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Offer> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateOfferMethod(), responseObserver);
    }

    /**
     */
    public void getOffers(mango.sep3.databaseaccess.protobuf.Void request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.OfferItems> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOffersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateOfferMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.Offer,
                mango.sep3.databaseaccess.protobuf.Offer>(
                  this, METHODID_CREATE_OFFER)))
          .addMethod(
            getGetOffersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.Void,
                mango.sep3.databaseaccess.protobuf.OfferItems>(
                  this, METHODID_GET_OFFERS)))
          .build();
    }
  }

  /**
   */
  public static final class OfferServiceStub extends io.grpc.stub.AbstractAsyncStub<OfferServiceStub> {
    private OfferServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OfferServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OfferServiceStub(channel, callOptions);
    }

    /**
     */
    public void createOffer(mango.sep3.databaseaccess.protobuf.Offer request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Offer> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateOfferMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOffers(mango.sep3.databaseaccess.protobuf.Void request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.OfferItems> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOffersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class OfferServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<OfferServiceBlockingStub> {
    private OfferServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OfferServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OfferServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.Offer createOffer(mango.sep3.databaseaccess.protobuf.Offer request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateOfferMethod(), getCallOptions(), request);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.OfferItems getOffers(mango.sep3.databaseaccess.protobuf.Void request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOffersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class OfferServiceFutureStub extends io.grpc.stub.AbstractFutureStub<OfferServiceFutureStub> {
    private OfferServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OfferServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OfferServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.Offer> createOffer(
        mango.sep3.databaseaccess.protobuf.Offer request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateOfferMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.OfferItems> getOffers(
        mango.sep3.databaseaccess.protobuf.Void request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOffersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_OFFER = 0;
  private static final int METHODID_GET_OFFERS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final OfferServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(OfferServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_OFFER:
          serviceImpl.createOffer((mango.sep3.databaseaccess.protobuf.Offer) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Offer>) responseObserver);
          break;
        case METHODID_GET_OFFERS:
          serviceImpl.getOffers((mango.sep3.databaseaccess.protobuf.Void) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.OfferItems>) responseObserver);
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

  private static abstract class OfferServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OfferServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return mango.sep3.databaseaccess.protobuf.Contract.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OfferService");
    }
  }

  private static final class OfferServiceFileDescriptorSupplier
      extends OfferServiceBaseDescriptorSupplier {
    OfferServiceFileDescriptorSupplier() {}
  }

  private static final class OfferServiceMethodDescriptorSupplier
      extends OfferServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    OfferServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (OfferServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OfferServiceFileDescriptorSupplier())
              .addMethod(getCreateOfferMethod())
              .addMethod(getGetOffersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
