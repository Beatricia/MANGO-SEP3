package mango.sep3.databaseaccess.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.39.0)",
    comments = "Source: contract.proto")
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final String SERVICE_NAME = "UserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.UserAuth,
      mango.sep3.databaseaccess.protobuf.User> getLoginUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LoginUser",
      requestType = mango.sep3.databaseaccess.protobuf.UserAuth.class,
      responseType = mango.sep3.databaseaccess.protobuf.User.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.UserAuth,
      mango.sep3.databaseaccess.protobuf.User> getLoginUserMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.UserAuth, mango.sep3.databaseaccess.protobuf.User> getLoginUserMethod;
    if ((getLoginUserMethod = UserServiceGrpc.getLoginUserMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getLoginUserMethod = UserServiceGrpc.getLoginUserMethod) == null) {
          UserServiceGrpc.getLoginUserMethod = getLoginUserMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.UserAuth, mango.sep3.databaseaccess.protobuf.User>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LoginUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.UserAuth.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.User.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("LoginUser"))
              .build();
        }
      }
    }
    return getLoginUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.UserAuth,
      mango.sep3.databaseaccess.protobuf.User> getRegisterUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterUser",
      requestType = mango.sep3.databaseaccess.protobuf.UserAuth.class,
      responseType = mango.sep3.databaseaccess.protobuf.User.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.UserAuth,
      mango.sep3.databaseaccess.protobuf.User> getRegisterUserMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.UserAuth, mango.sep3.databaseaccess.protobuf.User> getRegisterUserMethod;
    if ((getRegisterUserMethod = UserServiceGrpc.getRegisterUserMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getRegisterUserMethod = UserServiceGrpc.getRegisterUserMethod) == null) {
          UserServiceGrpc.getRegisterUserMethod = getRegisterUserMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.UserAuth, mango.sep3.databaseaccess.protobuf.User>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.UserAuth.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.User.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("RegisterUser"))
              .build();
        }
      }
    }
    return getRegisterUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text,
      mango.sep3.databaseaccess.protobuf.User> getGetUserByUsernameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUserByUsername",
      requestType = mango.sep3.databaseaccess.protobuf.Text.class,
      responseType = mango.sep3.databaseaccess.protobuf.User.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text,
      mango.sep3.databaseaccess.protobuf.User> getGetUserByUsernameMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text, mango.sep3.databaseaccess.protobuf.User> getGetUserByUsernameMethod;
    if ((getGetUserByUsernameMethod = UserServiceGrpc.getGetUserByUsernameMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getGetUserByUsernameMethod = UserServiceGrpc.getGetUserByUsernameMethod) == null) {
          UserServiceGrpc.getGetUserByUsernameMethod = getGetUserByUsernameMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.Text, mango.sep3.databaseaccess.protobuf.User>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUserByUsername"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Text.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.User.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("GetUserByUsername"))
              .build();
        }
      }
    }
    return getGetUserByUsernameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text,
      mango.sep3.databaseaccess.protobuf.UserAuth> getGetUserAuthByUsernameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUserAuthByUsername",
      requestType = mango.sep3.databaseaccess.protobuf.Text.class,
      responseType = mango.sep3.databaseaccess.protobuf.UserAuth.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text,
      mango.sep3.databaseaccess.protobuf.UserAuth> getGetUserAuthByUsernameMethod() {
    io.grpc.MethodDescriptor<mango.sep3.databaseaccess.protobuf.Text, mango.sep3.databaseaccess.protobuf.UserAuth> getGetUserAuthByUsernameMethod;
    if ((getGetUserAuthByUsernameMethod = UserServiceGrpc.getGetUserAuthByUsernameMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getGetUserAuthByUsernameMethod = UserServiceGrpc.getGetUserAuthByUsernameMethod) == null) {
          UserServiceGrpc.getGetUserAuthByUsernameMethod = getGetUserAuthByUsernameMethod =
              io.grpc.MethodDescriptor.<mango.sep3.databaseaccess.protobuf.Text, mango.sep3.databaseaccess.protobuf.UserAuth>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUserAuthByUsername"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.Text.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  mango.sep3.databaseaccess.protobuf.UserAuth.getDefaultInstance()))
              .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("GetUserAuthByUsername"))
              .build();
        }
      }
    }
    return getGetUserAuthByUsernameMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceStub>() {
        @java.lang.Override
        public UserServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceStub(channel, callOptions);
        }
      };
    return UserServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceBlockingStub>() {
        @java.lang.Override
        public UserServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceBlockingStub(channel, callOptions);
        }
      };
    return UserServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UserServiceFutureStub>() {
        @java.lang.Override
        public UserServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UserServiceFutureStub(channel, callOptions);
        }
      };
    return UserServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class UserServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void loginUser(mango.sep3.databaseaccess.protobuf.UserAuth request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.User> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLoginUserMethod(), responseObserver);
    }

    /**
     */
    public void registerUser(mango.sep3.databaseaccess.protobuf.UserAuth request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.User> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRegisterUserMethod(), responseObserver);
    }

    /**
     */
    public void getUserByUsername(mango.sep3.databaseaccess.protobuf.Text request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.User> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserByUsernameMethod(), responseObserver);
    }

    /**
     */
    public void getUserAuthByUsername(mango.sep3.databaseaccess.protobuf.Text request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.UserAuth> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserAuthByUsernameMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginUserMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.UserAuth,
                mango.sep3.databaseaccess.protobuf.User>(
                  this, METHODID_LOGIN_USER)))
          .addMethod(
            getRegisterUserMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.UserAuth,
                mango.sep3.databaseaccess.protobuf.User>(
                  this, METHODID_REGISTER_USER)))
          .addMethod(
            getGetUserByUsernameMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.Text,
                mango.sep3.databaseaccess.protobuf.User>(
                  this, METHODID_GET_USER_BY_USERNAME)))
          .addMethod(
            getGetUserAuthByUsernameMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                mango.sep3.databaseaccess.protobuf.Text,
                mango.sep3.databaseaccess.protobuf.UserAuth>(
                  this, METHODID_GET_USER_AUTH_BY_USERNAME)))
          .build();
    }
  }

  /**
   */
  public static final class UserServiceStub extends io.grpc.stub.AbstractAsyncStub<UserServiceStub> {
    private UserServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     */
    public void loginUser(mango.sep3.databaseaccess.protobuf.UserAuth request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.User> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLoginUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void registerUser(mango.sep3.databaseaccess.protobuf.UserAuth request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.User> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRegisterUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUserByUsername(mango.sep3.databaseaccess.protobuf.Text request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.User> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUserByUsernameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUserAuthByUsername(mango.sep3.databaseaccess.protobuf.Text request,
        io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.UserAuth> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUserAuthByUsernameMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class UserServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<UserServiceBlockingStub> {
    private UserServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.User loginUser(mango.sep3.databaseaccess.protobuf.UserAuth request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLoginUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.User registerUser(mango.sep3.databaseaccess.protobuf.UserAuth request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRegisterUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.User getUserByUsername(mango.sep3.databaseaccess.protobuf.Text request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUserByUsernameMethod(), getCallOptions(), request);
    }

    /**
     */
    public mango.sep3.databaseaccess.protobuf.UserAuth getUserAuthByUsername(mango.sep3.databaseaccess.protobuf.Text request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUserAuthByUsernameMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class UserServiceFutureStub extends io.grpc.stub.AbstractFutureStub<UserServiceFutureStub> {
    private UserServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UserServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.User> loginUser(
        mango.sep3.databaseaccess.protobuf.UserAuth request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLoginUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.User> registerUser(
        mango.sep3.databaseaccess.protobuf.UserAuth request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRegisterUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.User> getUserByUsername(
        mango.sep3.databaseaccess.protobuf.Text request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUserByUsernameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<mango.sep3.databaseaccess.protobuf.UserAuth> getUserAuthByUsername(
        mango.sep3.databaseaccess.protobuf.Text request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUserAuthByUsernameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN_USER = 0;
  private static final int METHODID_REGISTER_USER = 1;
  private static final int METHODID_GET_USER_BY_USERNAME = 2;
  private static final int METHODID_GET_USER_AUTH_BY_USERNAME = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN_USER:
          serviceImpl.loginUser((mango.sep3.databaseaccess.protobuf.UserAuth) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.User>) responseObserver);
          break;
        case METHODID_REGISTER_USER:
          serviceImpl.registerUser((mango.sep3.databaseaccess.protobuf.UserAuth) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.User>) responseObserver);
          break;
        case METHODID_GET_USER_BY_USERNAME:
          serviceImpl.getUserByUsername((mango.sep3.databaseaccess.protobuf.Text) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.User>) responseObserver);
          break;
        case METHODID_GET_USER_AUTH_BY_USERNAME:
          serviceImpl.getUserAuthByUsername((mango.sep3.databaseaccess.protobuf.Text) request,
              (io.grpc.stub.StreamObserver<mango.sep3.databaseaccess.protobuf.UserAuth>) responseObserver);
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

  private static abstract class UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return mango.sep3.databaseaccess.protobuf.Contract.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserService");
    }
  }

  private static final class UserServiceFileDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier {
    UserServiceFileDescriptorSupplier() {}
  }

  private static final class UserServiceMethodDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceFileDescriptorSupplier())
              .addMethod(getLoginUserMethod())
              .addMethod(getRegisterUserMethod())
              .addMethod(getGetUserByUsernameMethod())
              .addMethod(getGetUserAuthByUsernameMethod())
              .build();
        }
      }
    }
    return result;
  }
}
