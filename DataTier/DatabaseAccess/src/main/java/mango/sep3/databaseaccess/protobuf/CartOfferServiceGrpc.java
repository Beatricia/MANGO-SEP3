package mango.sep3.databaseaccess.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: contract.proto")
public final class CartOfferServiceGrpc {

  private CartOfferServiceGrpc() {}

  public static final String SERVICE_NAME = "CartOfferService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.CartOffer,
      mango.sep3.databaseaccess.protobuf.Void> getAddToCartMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddToCart",
      requestType = mango.sep3.databaseaccess.protobuf.CartOffer.class,
      responseType = mango.sep3.databaseaccess.protobuf.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.CartOffer,
      mango.sep3.databaseaccess.protobuf.Void> getAddToCartMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.CartOffer, mango.sep3.databaseaccess.protobuf.Void> getAddToCartMethod;
    if ((getAddToCartMethod = CartOfferServiceGrpc.getAddToCartMethod) == null) {
      synchronized (CartOfferServiceGrpc.class) {
        if ((getAddToCartMethod = CartOfferServiceGrpc.getAddToCartMethod) == null) {
          CartOfferServiceGrpc.getAddToCartMethod = getAddToCartMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.CartOffer, mango.sep3.databaseaccess.protobuf.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddToCart"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.CartOffer.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Void.getDefaultInstance()))
              .setSchemaDescriptor(new CartOfferServiceMethodDescriptorSupplier("AddToCart"))
              .build();
        }
      }
    }
    return getAddToCartMethod;
  }

  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Username,
      mango.sep3.databaseaccess.protobuf.CartOffers> getGetAllCartOffersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllCartOffers",
      requestType = mango.sep3.databaseaccess.protobuf.Username.class,
      responseType = mango.sep3.databaseaccess.protobuf.CartOffers.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Username,
      mango.sep3.databaseaccess.protobuf.CartOffers> getGetAllCartOffersMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Username, mango.sep3.databaseaccess.protobuf.CartOffers> getGetAllCartOffersMethod;
    if ((getGetAllCartOffersMethod = CartOfferServiceGrpc.getGetAllCartOffersMethod) == null) {
      synchronized (CartOfferServiceGrpc.class) {
        if ((getGetAllCartOffersMethod = CartOfferServiceGrpc.getGetAllCartOffersMethod) == null) {
          CartOfferServiceGrpc.getGetAllCartOffersMethod = getGetAllCartOffersMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.Username, mango.sep3.databaseaccess.protobuf.CartOffers>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllCartOffers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Username.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.CartOffers.getDefaultInstance()))
              .setSchemaDescriptor(new CartOfferServiceMethodDescriptorSupplier("GetAllCartOffers"))
              .build();
        }
      }
    }
    return getGetAllCartOffersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Username,
      mango.sep3.databaseaccess.protobuf.Void> getDeleteAllCartOffersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteAllCartOffers",
      requestType = mango.sep3.databaseaccess.protobuf.Username.class,
      responseType = mango.sep3.databaseaccess.protobuf.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Username,
      mango.sep3.databaseaccess.protobuf.Void> getDeleteAllCartOffersMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Username, mango.sep3.databaseaccess.protobuf.Void> getDeleteAllCartOffersMethod;
    if ((getDeleteAllCartOffersMethod = CartOfferServiceGrpc.getDeleteAllCartOffersMethod) == null) {
      synchronized (CartOfferServiceGrpc.class) {
        if ((getDeleteAllCartOffersMethod = CartOfferServiceGrpc.getDeleteAllCartOffersMethod) == null) {
          CartOfferServiceGrpc.getDeleteAllCartOffersMethod = getDeleteAllCartOffersMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.Username, mango.sep3.databaseaccess.protobuf.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteAllCartOffers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Username.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Void.getDefaultInstance()))
              .setSchemaDescriptor(new CartOfferServiceMethodDescriptorSupplier("DeleteAllCartOffers"))
              .build();
        }
      }
    }
    return getDeleteAllCartOffersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CartOfferServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CartOfferServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CartOfferServiceStub>() {
        @java.lang.Override
        public CartOfferServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CartOfferServiceStub(channel, callOptions);
        }
      };
    return CartOfferServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CartOfferServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CartOfferServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CartOfferServiceBlockingStub>() {
        @java.lang.Override
        public CartOfferServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CartOfferServiceBlockingStub(channel, callOptions);
        }
      };
    return CartOfferServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CartOfferServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CartOfferServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CartOfferServiceFutureStub>() {
        @java.lang.Override
        public CartOfferServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CartOfferServiceFutureStub(channel, callOptions);
        }
      };
    return CartOfferServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class CartOfferServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void addToCart(mango.sep3.databaseaccess.protobuf.CartOffer request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddToCartMethod(), responseObserver);
    }

    /**
     */
    public void getAllCartOffers(mango.sep3.databaseaccess.protobuf.Username request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.CartOffers> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllCartOffersMethod(), responseObserver);
    }

    /**
     */
    public void deleteAllCartOffers(mango.sep3.databaseaccess.protobuf.Username request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteAllCartOffersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddToCartMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.CartOffer,
                mango.sep3.databaseaccess.protobuf.Void>(
                  this, METHODID_ADD_TO_CART)))
          .addMethod(
            getGetAllCartOffersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.Username,
                mango.sep3.databaseaccess.protobuf.CartOffers>(
                  this, METHODID_GET_ALL_CART_OFFERS)))
          .addMethod(
            getDeleteAllCartOffersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.Username,
                mango.sep3.databaseaccess.protobuf.Void>(
                  this, METHODID_DELETE_ALL_CART_OFFERS)))
          .build();
    }
  }

  /**
   */
  public static final class CartOfferServiceStub extends io.grpc.stub.AbstractAsyncStub<CartOfferServiceStub> {
    private CartOfferServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CartOfferServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CartOfferServiceStub(channel, callOptions);
    }

    /**
     */
    public void addToCart(mango.sep3.databaseaccess.protobuf.CartOffer request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddToCartMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllCartOffers(mango.sep3.databaseaccess.protobuf.Username request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.CartOffers> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllCartOffersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteAllCartOffers(mango.sep3.databaseaccess.protobuf.Username request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteAllCartOffersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class CartOfferServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<CartOfferServiceBlockingStub> {
    private CartOfferServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CartOfferServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CartOfferServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.Void addToCart(mango.sep3.databaseaccess.protobuf.CartOffer request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddToCartMethod(), getCallOptions(), request);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.CartOffers getAllCartOffers(mango.sep3.databaseaccess.protobuf.Username request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllCartOffersMethod(), getCallOptions(), request);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.Void deleteAllCartOffers(mango.sep3.databaseaccess.protobuf.Username request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteAllCartOffersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class CartOfferServiceFutureStub extends io.grpc.stub.AbstractFutureStub<CartOfferServiceFutureStub> {
    private CartOfferServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CartOfferServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CartOfferServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.Void> addToCart(
        mango.sep3.databaseaccess.protobuf.CartOffer request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddToCartMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.CartOffers> getAllCartOffers(
        mango.sep3.databaseaccess.protobuf.Username request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllCartOffersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.Void> deleteAllCartOffers(
        mango.sep3.databaseaccess.protobuf.Username request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteAllCartOffersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_TO_CART = 0;
  private static final int METHODID_GET_ALL_CART_OFFERS = 1;
  private static final int METHODID_DELETE_ALL_CART_OFFERS = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final CartOfferServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(CartOfferServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_TO_CART:
          serviceImpl.addToCart((mango.sep3.databaseaccess.protobuf.CartOffer) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void>) responseObserver);
          break;
        case METHODID_GET_ALL_CART_OFFERS:
          serviceImpl.getAllCartOffers((mango.sep3.databaseaccess.protobuf.Username) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.CartOffers>) responseObserver);
          break;
        case METHODID_DELETE_ALL_CART_OFFERS:
          serviceImpl.deleteAllCartOffers((mango.sep3.databaseaccess.protobuf.Username) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void>) responseObserver);
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

  private static abstract class CartOfferServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CartOfferServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return mango.sep3.databaseaccess.protobuf.Contract.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CartOfferService");
    }
  }

  private static final class CartOfferServiceFileDescriptorSupplier
      extends CartOfferServiceBaseDescriptorSupplier {
    CartOfferServiceFileDescriptorSupplier() {}
  }

  private static final class CartOfferServiceMethodDescriptorSupplier
      extends CartOfferServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    CartOfferServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (CartOfferServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CartOfferServiceFileDescriptorSupplier())
              .addMethod(getAddToCartMethod())
              .addMethod(getGetAllCartOffersMethod())
              .addMethod(getDeleteAllCartOffersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
