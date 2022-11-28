package mango.sep3.databaseaccess.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: contract.proto")
public final class OrderServiceGrpc {

  private OrderServiceGrpc() {}

  public static final String SERVICE_NAME = "OrderService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.OrderOffersToCreate,
      mango.sep3.databaseaccess.protobuf.Void> getCreateOrderOffersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateOrderOffers",
      requestType = mango.sep3.databaseaccess.protobuf.OrderOffersToCreate.class,
      responseType = mango.sep3.databaseaccess.protobuf.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.OrderOffersToCreate,
      mango.sep3.databaseaccess.protobuf.Void> getCreateOrderOffersMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.OrderOffersToCreate, mango.sep3.databaseaccess.protobuf.Void> getCreateOrderOffersMethod;
    if ((getCreateOrderOffersMethod = OrderServiceGrpc.getCreateOrderOffersMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getCreateOrderOffersMethod = OrderServiceGrpc.getCreateOrderOffersMethod) == null) {
          OrderServiceGrpc.getCreateOrderOffersMethod = getCreateOrderOffersMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.OrderOffersToCreate, mango.sep3.databaseaccess.protobuf.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateOrderOffers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.OrderOffersToCreate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Void.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("CreateOrderOffers"))
              .build();
        }
      }
    }
    return getCreateOrderOffersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.OrdersToCreate,
      mango.sep3.databaseaccess.protobuf.Void> getCreateOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateOrders",
      requestType = mango.sep3.databaseaccess.protobuf.OrdersToCreate.class,
      responseType = mango.sep3.databaseaccess.protobuf.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.OrdersToCreate,
      mango.sep3.databaseaccess.protobuf.Void> getCreateOrdersMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.OrdersToCreate, mango.sep3.databaseaccess.protobuf.Void> getCreateOrdersMethod;
    if ((getCreateOrdersMethod = OrderServiceGrpc.getCreateOrdersMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getCreateOrdersMethod = OrderServiceGrpc.getCreateOrdersMethod) == null) {
          OrderServiceGrpc.getCreateOrdersMethod = getCreateOrdersMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.OrdersToCreate, mango.sep3.databaseaccess.protobuf.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.OrdersToCreate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Void.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("CreateOrders"))
              .build();
        }
      }
    }
    return getCreateOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text,
      mango.sep3.databaseaccess.protobuf.Orders> getGetAllOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllOrders",
      requestType = mango.sep3.databaseaccess.protobuf.Text.class,
      responseType = mango.sep3.databaseaccess.protobuf.Orders.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text,
      mango.sep3.databaseaccess.protobuf.Orders> getGetAllOrdersMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text, mango.sep3.databaseaccess.protobuf.Orders> getGetAllOrdersMethod;
    if ((getGetAllOrdersMethod = OrderServiceGrpc.getGetAllOrdersMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getGetAllOrdersMethod = OrderServiceGrpc.getGetAllOrdersMethod) == null) {
          OrderServiceGrpc.getGetAllOrdersMethod = getGetAllOrdersMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.Text, mango.sep3.databaseaccess.protobuf.Orders>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Text.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Orders.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("GetAllOrders"))
              .build();
        }
      }
    }
    return getGetAllOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text,
      mango.sep3.databaseaccess.protobuf.OrderOffers> getGetOrderOffersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetOrderOffers",
      requestType = mango.sep3.databaseaccess.protobuf.Text.class,
      responseType = mango.sep3.databaseaccess.protobuf.OrderOffers.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text,
      mango.sep3.databaseaccess.protobuf.OrderOffers> getGetOrderOffersMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text, mango.sep3.databaseaccess.protobuf.OrderOffers> getGetOrderOffersMethod;
    if ((getGetOrderOffersMethod = OrderServiceGrpc.getGetOrderOffersMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getGetOrderOffersMethod = OrderServiceGrpc.getGetOrderOffersMethod) == null) {
          OrderServiceGrpc.getGetOrderOffersMethod = getGetOrderOffersMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.Text, mango.sep3.databaseaccess.protobuf.OrderOffers>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetOrderOffers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Text.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.OrderOffers.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("GetOrderOffers"))
              .build();
        }
      }
    }
    return getGetOrderOffersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OrderServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderServiceStub>() {
        @java.lang.Override
        public OrderServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderServiceStub(channel, callOptions);
        }
      };
    return OrderServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OrderServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderServiceBlockingStub>() {
        @java.lang.Override
        public OrderServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderServiceBlockingStub(channel, callOptions);
        }
      };
    return OrderServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OrderServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderServiceFutureStub>() {
        @java.lang.Override
        public OrderServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderServiceFutureStub(channel, callOptions);
        }
      };
    return OrderServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class OrderServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void createOrderOffers(mango.sep3.databaseaccess.protobuf.OrderOffersToCreate request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateOrderOffersMethod(), responseObserver);
    }

    /**
     */
    public void createOrders(mango.sep3.databaseaccess.protobuf.OrdersToCreate request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateOrdersMethod(), responseObserver);
    }

    /**
     */
    public void getAllOrders(mango.sep3.databaseaccess.protobuf.Text request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Orders> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllOrdersMethod(), responseObserver);
    }

    /**
     */
    public void getOrderOffers(mango.sep3.databaseaccess.protobuf.Text request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.OrderOffers> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOrderOffersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateOrderOffersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.OrderOffersToCreate,
                mango.sep3.databaseaccess.protobuf.Void>(
                  this, METHODID_CREATE_ORDER_OFFERS)))
          .addMethod(
            getCreateOrdersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.OrdersToCreate,
                mango.sep3.databaseaccess.protobuf.Void>(
                  this, METHODID_CREATE_ORDERS)))
          .addMethod(
            getGetAllOrdersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.Text,
                mango.sep3.databaseaccess.protobuf.Orders>(
                  this, METHODID_GET_ALL_ORDERS)))
          .addMethod(
            getGetOrderOffersMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.Text,
                mango.sep3.databaseaccess.protobuf.OrderOffers>(
                  this, METHODID_GET_ORDER_OFFERS)))
          .build();
    }
  }

  /**
   */
  public static final class OrderServiceStub extends io.grpc.stub.AbstractAsyncStub<OrderServiceStub> {
    private OrderServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderServiceStub(channel, callOptions);
    }

    /**
     */
    public void createOrderOffers(mango.sep3.databaseaccess.protobuf.OrderOffersToCreate request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateOrderOffersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createOrders(mango.sep3.databaseaccess.protobuf.OrdersToCreate request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllOrders(mango.sep3.databaseaccess.protobuf.Text request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Orders> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOrderOffers(mango.sep3.databaseaccess.protobuf.Text request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.OrderOffers> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOrderOffersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class OrderServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<OrderServiceBlockingStub> {
    private OrderServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.Void createOrderOffers(mango.sep3.databaseaccess.protobuf.OrderOffersToCreate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateOrderOffersMethod(), getCallOptions(), request);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.Void createOrders(mango.sep3.databaseaccess.protobuf.OrdersToCreate request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateOrdersMethod(), getCallOptions(), request);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.Orders getAllOrders(mango.sep3.databaseaccess.protobuf.Text request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllOrdersMethod(), getCallOptions(), request);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.OrderOffers getOrderOffers(mango.sep3.databaseaccess.protobuf.Text request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOrderOffersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class OrderServiceFutureStub extends io.grpc.stub.AbstractFutureStub<OrderServiceFutureStub> {
    private OrderServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.Void> createOrderOffers(
        mango.sep3.databaseaccess.protobuf.OrderOffersToCreate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateOrderOffersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.Void> createOrders(
        mango.sep3.databaseaccess.protobuf.OrdersToCreate request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateOrdersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.Orders> getAllOrders(
        mango.sep3.databaseaccess.protobuf.Text request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllOrdersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.OrderOffers> getOrderOffers(
        mango.sep3.databaseaccess.protobuf.Text request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOrderOffersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_ORDER_OFFERS = 0;
  private static final int METHODID_CREATE_ORDERS = 1;
  private static final int METHODID_GET_ALL_ORDERS = 2;
  private static final int METHODID_GET_ORDER_OFFERS = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final OrderServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(OrderServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_ORDER_OFFERS:
          serviceImpl.createOrderOffers((mango.sep3.databaseaccess.protobuf.OrderOffersToCreate) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void>) responseObserver);
          break;
        case METHODID_CREATE_ORDERS:
          serviceImpl.createOrders((mango.sep3.databaseaccess.protobuf.OrdersToCreate) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Void>) responseObserver);
          break;
        case METHODID_GET_ALL_ORDERS:
          serviceImpl.getAllOrders((mango.sep3.databaseaccess.protobuf.Text) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.Orders>) responseObserver);
          break;
        case METHODID_GET_ORDER_OFFERS:
          serviceImpl.getOrderOffers((mango.sep3.databaseaccess.protobuf.Text) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.OrderOffers>) responseObserver);
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

  private static abstract class OrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OrderServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return mango.sep3.databaseaccess.protobuf.Contract.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OrderService");
    }
  }

  private static final class OrderServiceFileDescriptorSupplier
      extends OrderServiceBaseDescriptorSupplier {
    OrderServiceFileDescriptorSupplier() {}
  }

  private static final class OrderServiceMethodDescriptorSupplier
      extends OrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    OrderServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (OrderServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OrderServiceFileDescriptorSupplier())
              .addMethod(getCreateOrderOffersMethod())
              .addMethod(getCreateOrdersMethod())
              .addMethod(getGetAllOrdersMethod())
              .addMethod(getGetOrderOffersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
