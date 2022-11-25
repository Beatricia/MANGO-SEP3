// <auto-generated>
//     Generated by the protocol buffer compiler.  DO NOT EDIT!
//     source: contract.proto
// </auto-generated>
#pragma warning disable 0414, 1591, 8981
#region Designer generated code

using grpc = global::Grpc.Core;

public static partial class FarmService
{
  static readonly string __ServiceName = "FarmService";

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static void __Helper_SerializeMessage(global::Google.Protobuf.IMessage message, grpc::SerializationContext context)
  {
    #if !GRPC_DISABLE_PROTOBUF_BUFFER_SERIALIZATION
    if (message is global::Google.Protobuf.IBufferMessage)
    {
      context.SetPayloadLength(message.CalculateSize());
      global::Google.Protobuf.MessageExtensions.WriteTo(message, context.GetBufferWriter());
      context.Complete();
      return;
    }
    #endif
    context.Complete(global::Google.Protobuf.MessageExtensions.ToByteArray(message));
  }

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static class __Helper_MessageCache<T>
  {
    public static readonly bool IsBufferMessage = global::System.Reflection.IntrospectionExtensions.GetTypeInfo(typeof(global::Google.Protobuf.IBufferMessage)).IsAssignableFrom(typeof(T));
  }

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static T __Helper_DeserializeMessage<T>(grpc::DeserializationContext context, global::Google.Protobuf.MessageParser<T> parser) where T : global::Google.Protobuf.IMessage<T>
  {
    #if !GRPC_DISABLE_PROTOBUF_BUFFER_SERIALIZATION
    if (__Helper_MessageCache<T>.IsBufferMessage)
    {
      return parser.ParseFrom(context.PayloadAsReadOnlySequence());
    }
    #endif
    return parser.ParseFrom(context.PayloadAsNewBuffer());
  }

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Marshaller<global::Farm> __Marshaller_Farm = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::Farm.Parser));
  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Marshaller<global::Text> __Marshaller_Text = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::Text.Parser));

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Method<global::Farm, global::Farm> __Method_CreateFarm = new grpc::Method<global::Farm, global::Farm>(
      grpc::MethodType.Unary,
      __ServiceName,
      "CreateFarm",
      __Marshaller_Farm,
      __Marshaller_Farm);

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Method<global::Text, global::Farm> __Method_GetFarm = new grpc::Method<global::Text, global::Farm>(
      grpc::MethodType.Unary,
      __ServiceName,
      "GetFarm",
      __Marshaller_Text,
      __Marshaller_Farm);

  /// <summary>Service descriptor</summary>
  public static global::Google.Protobuf.Reflection.ServiceDescriptor Descriptor
  {
    get { return global::ContractReflection.Descriptor.Services[0]; }
  }

  /// <summary>Client for FarmService</summary>
  public partial class FarmServiceClient : grpc::ClientBase<FarmServiceClient>
  {
    /// <summary>Creates a new client for FarmService</summary>
    /// <param name="channel">The channel to use to make remote calls.</param>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public FarmServiceClient(grpc::ChannelBase channel) : base(channel)
    {
    }
    /// <summary>Creates a new client for FarmService that uses a custom <c>CallInvoker</c>.</summary>
    /// <param name="callInvoker">The callInvoker to use to make remote calls.</param>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public FarmServiceClient(grpc::CallInvoker callInvoker) : base(callInvoker)
    {
    }
    /// <summary>Protected parameterless constructor to allow creation of test doubles.</summary>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    protected FarmServiceClient() : base()
    {
    }
    /// <summary>Protected constructor to allow creation of configured clients.</summary>
    /// <param name="configuration">The client configuration.</param>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    protected FarmServiceClient(ClientBaseConfiguration configuration) : base(configuration)
    {
    }

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Farm CreateFarm(global::Farm request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return CreateFarm(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Farm CreateFarm(global::Farm request, grpc::CallOptions options)
    {
      return CallInvoker.BlockingUnaryCall(__Method_CreateFarm, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Farm> CreateFarmAsync(global::Farm request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return CreateFarmAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Farm> CreateFarmAsync(global::Farm request, grpc::CallOptions options)
    {
      return CallInvoker.AsyncUnaryCall(__Method_CreateFarm, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Farm GetFarm(global::Text request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return GetFarm(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Farm GetFarm(global::Text request, grpc::CallOptions options)
    {
      return CallInvoker.BlockingUnaryCall(__Method_GetFarm, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Farm> GetFarmAsync(global::Text request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return GetFarmAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Farm> GetFarmAsync(global::Text request, grpc::CallOptions options)
    {
      return CallInvoker.AsyncUnaryCall(__Method_GetFarm, null, options, request);
    }
    /// <summary>Creates a new instance of client from given <c>ClientBaseConfiguration</c>.</summary>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    protected override FarmServiceClient NewInstance(ClientBaseConfiguration configuration)
    {
      return new FarmServiceClient(configuration);
    }
  }

}
public static partial class OfferService
{
  static readonly string __ServiceName = "OfferService";

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static void __Helper_SerializeMessage(global::Google.Protobuf.IMessage message, grpc::SerializationContext context)
  {
    #if !GRPC_DISABLE_PROTOBUF_BUFFER_SERIALIZATION
    if (message is global::Google.Protobuf.IBufferMessage)
    {
      context.SetPayloadLength(message.CalculateSize());
      global::Google.Protobuf.MessageExtensions.WriteTo(message, context.GetBufferWriter());
      context.Complete();
      return;
    }
    #endif
    context.Complete(global::Google.Protobuf.MessageExtensions.ToByteArray(message));
  }

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static class __Helper_MessageCache<T>
  {
    public static readonly bool IsBufferMessage = global::System.Reflection.IntrospectionExtensions.GetTypeInfo(typeof(global::Google.Protobuf.IBufferMessage)).IsAssignableFrom(typeof(T));
  }

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static T __Helper_DeserializeMessage<T>(grpc::DeserializationContext context, global::Google.Protobuf.MessageParser<T> parser) where T : global::Google.Protobuf.IMessage<T>
  {
    #if !GRPC_DISABLE_PROTOBUF_BUFFER_SERIALIZATION
    if (__Helper_MessageCache<T>.IsBufferMessage)
    {
      return parser.ParseFrom(context.PayloadAsReadOnlySequence());
    }
    #endif
    return parser.ParseFrom(context.PayloadAsNewBuffer());
  }

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Marshaller<global::Offer> __Marshaller_Offer = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::Offer.Parser));
  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Marshaller<global::Void> __Marshaller_Void = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::Void.Parser));
  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Marshaller<global::OfferItems> __Marshaller_OfferItems = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::OfferItems.Parser));

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Method<global::Offer, global::Offer> __Method_CreateOffer = new grpc::Method<global::Offer, global::Offer>(
      grpc::MethodType.Unary,
      __ServiceName,
      "CreateOffer",
      __Marshaller_Offer,
      __Marshaller_Offer);

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Method<global::Void, global::OfferItems> __Method_GetOffers = new grpc::Method<global::Void, global::OfferItems>(
      grpc::MethodType.Unary,
      __ServiceName,
      "GetOffers",
      __Marshaller_Void,
      __Marshaller_OfferItems);

  /// <summary>Service descriptor</summary>
  public static global::Google.Protobuf.Reflection.ServiceDescriptor Descriptor
  {
    get { return global::ContractReflection.Descriptor.Services[1]; }
  }

  /// <summary>Client for OfferService</summary>
  public partial class OfferServiceClient : grpc::ClientBase<OfferServiceClient>
  {
    /// <summary>Creates a new client for OfferService</summary>
    /// <param name="channel">The channel to use to make remote calls.</param>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public OfferServiceClient(grpc::ChannelBase channel) : base(channel)
    {
    }
    /// <summary>Creates a new client for OfferService that uses a custom <c>CallInvoker</c>.</summary>
    /// <param name="callInvoker">The callInvoker to use to make remote calls.</param>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public OfferServiceClient(grpc::CallInvoker callInvoker) : base(callInvoker)
    {
    }
    /// <summary>Protected parameterless constructor to allow creation of test doubles.</summary>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    protected OfferServiceClient() : base()
    {
    }
    /// <summary>Protected constructor to allow creation of configured clients.</summary>
    /// <param name="configuration">The client configuration.</param>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    protected OfferServiceClient(ClientBaseConfiguration configuration) : base(configuration)
    {
    }

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Offer CreateOffer(global::Offer request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return CreateOffer(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Offer CreateOffer(global::Offer request, grpc::CallOptions options)
    {
      return CallInvoker.BlockingUnaryCall(__Method_CreateOffer, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Offer> CreateOfferAsync(global::Offer request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return CreateOfferAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Offer> CreateOfferAsync(global::Offer request, grpc::CallOptions options)
    {
      return CallInvoker.AsyncUnaryCall(__Method_CreateOffer, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::OfferItems GetOffers(global::Void request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return GetOffers(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::OfferItems GetOffers(global::Void request, grpc::CallOptions options)
    {
      return CallInvoker.BlockingUnaryCall(__Method_GetOffers, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::OfferItems> GetOffersAsync(global::Void request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return GetOffersAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::OfferItems> GetOffersAsync(global::Void request, grpc::CallOptions options)
    {
      return CallInvoker.AsyncUnaryCall(__Method_GetOffers, null, options, request);
    }
    /// <summary>Creates a new instance of client from given <c>ClientBaseConfiguration</c>.</summary>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    protected override OfferServiceClient NewInstance(ClientBaseConfiguration configuration)
    {
      return new OfferServiceClient(configuration);
    }
  }

}
public static partial class UserService
{
  static readonly string __ServiceName = "UserService";

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static void __Helper_SerializeMessage(global::Google.Protobuf.IMessage message, grpc::SerializationContext context)
  {
    #if !GRPC_DISABLE_PROTOBUF_BUFFER_SERIALIZATION
    if (message is global::Google.Protobuf.IBufferMessage)
    {
      context.SetPayloadLength(message.CalculateSize());
      global::Google.Protobuf.MessageExtensions.WriteTo(message, context.GetBufferWriter());
      context.Complete();
      return;
    }
    #endif
    context.Complete(global::Google.Protobuf.MessageExtensions.ToByteArray(message));
  }

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static class __Helper_MessageCache<T>
  {
    public static readonly bool IsBufferMessage = global::System.Reflection.IntrospectionExtensions.GetTypeInfo(typeof(global::Google.Protobuf.IBufferMessage)).IsAssignableFrom(typeof(T));
  }

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static T __Helper_DeserializeMessage<T>(grpc::DeserializationContext context, global::Google.Protobuf.MessageParser<T> parser) where T : global::Google.Protobuf.IMessage<T>
  {
    #if !GRPC_DISABLE_PROTOBUF_BUFFER_SERIALIZATION
    if (__Helper_MessageCache<T>.IsBufferMessage)
    {
      return parser.ParseFrom(context.PayloadAsReadOnlySequence());
    }
    #endif
    return parser.ParseFrom(context.PayloadAsNewBuffer());
  }

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Marshaller<global::UserAuth> __Marshaller_UserAuth = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::UserAuth.Parser));
  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Marshaller<global::User> __Marshaller_User = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::User.Parser));
  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Marshaller<global::Text> __Marshaller_Text = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::Text.Parser));
  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Marshaller<global::Customer> __Marshaller_Customer = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::Customer.Parser));

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Method<global::UserAuth, global::User> __Method_RegisterUser = new grpc::Method<global::UserAuth, global::User>(
      grpc::MethodType.Unary,
      __ServiceName,
      "RegisterUser",
      __Marshaller_UserAuth,
      __Marshaller_User);

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Method<global::Text, global::User> __Method_GetUserByUsername = new grpc::Method<global::Text, global::User>(
      grpc::MethodType.Unary,
      __ServiceName,
      "GetUserByUsername",
      __Marshaller_Text,
      __Marshaller_User);

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Method<global::Text, global::Customer> __Method_GetCustomer = new grpc::Method<global::Text, global::Customer>(
      grpc::MethodType.Unary,
      __ServiceName,
      "GetCustomer",
      __Marshaller_Text,
      __Marshaller_Customer);

  /// <summary>Service descriptor</summary>
  public static global::Google.Protobuf.Reflection.ServiceDescriptor Descriptor
  {
    get { return global::ContractReflection.Descriptor.Services[2]; }
  }

  /// <summary>Client for UserService</summary>
  public partial class UserServiceClient : grpc::ClientBase<UserServiceClient>
  {
    /// <summary>Creates a new client for UserService</summary>
    /// <param name="channel">The channel to use to make remote calls.</param>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public UserServiceClient(grpc::ChannelBase channel) : base(channel)
    {
    }
    /// <summary>Creates a new client for UserService that uses a custom <c>CallInvoker</c>.</summary>
    /// <param name="callInvoker">The callInvoker to use to make remote calls.</param>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public UserServiceClient(grpc::CallInvoker callInvoker) : base(callInvoker)
    {
    }
    /// <summary>Protected parameterless constructor to allow creation of test doubles.</summary>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    protected UserServiceClient() : base()
    {
    }
    /// <summary>Protected constructor to allow creation of configured clients.</summary>
    /// <param name="configuration">The client configuration.</param>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    protected UserServiceClient(ClientBaseConfiguration configuration) : base(configuration)
    {
    }

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::User RegisterUser(global::UserAuth request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return RegisterUser(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::User RegisterUser(global::UserAuth request, grpc::CallOptions options)
    {
      return CallInvoker.BlockingUnaryCall(__Method_RegisterUser, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::User> RegisterUserAsync(global::UserAuth request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return RegisterUserAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::User> RegisterUserAsync(global::UserAuth request, grpc::CallOptions options)
    {
      return CallInvoker.AsyncUnaryCall(__Method_RegisterUser, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::User GetUserByUsername(global::Text request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return GetUserByUsername(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::User GetUserByUsername(global::Text request, grpc::CallOptions options)
    {
      return CallInvoker.BlockingUnaryCall(__Method_GetUserByUsername, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::User> GetUserByUsernameAsync(global::Text request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return GetUserByUsernameAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::User> GetUserByUsernameAsync(global::Text request, grpc::CallOptions options)
    {
      return CallInvoker.AsyncUnaryCall(__Method_GetUserByUsername, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Customer GetCustomer(global::Text request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return GetCustomer(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Customer GetCustomer(global::Text request, grpc::CallOptions options)
    {
      return CallInvoker.BlockingUnaryCall(__Method_GetCustomer, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Customer> GetCustomerAsync(global::Text request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return GetCustomerAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Customer> GetCustomerAsync(global::Text request, grpc::CallOptions options)
    {
      return CallInvoker.AsyncUnaryCall(__Method_GetCustomer, null, options, request);
    }
    /// <summary>Creates a new instance of client from given <c>ClientBaseConfiguration</c>.</summary>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    protected override UserServiceClient NewInstance(ClientBaseConfiguration configuration)
    {
      return new UserServiceClient(configuration);
    }
  }

}
public static partial class OrderService
{
  static readonly string __ServiceName = "OrderService";

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static void __Helper_SerializeMessage(global::Google.Protobuf.IMessage message, grpc::SerializationContext context)
  {
    #if !GRPC_DISABLE_PROTOBUF_BUFFER_SERIALIZATION
    if (message is global::Google.Protobuf.IBufferMessage)
    {
      context.SetPayloadLength(message.CalculateSize());
      global::Google.Protobuf.MessageExtensions.WriteTo(message, context.GetBufferWriter());
      context.Complete();
      return;
    }
    #endif
    context.Complete(global::Google.Protobuf.MessageExtensions.ToByteArray(message));
  }

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static class __Helper_MessageCache<T>
  {
    public static readonly bool IsBufferMessage = global::System.Reflection.IntrospectionExtensions.GetTypeInfo(typeof(global::Google.Protobuf.IBufferMessage)).IsAssignableFrom(typeof(T));
  }

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static T __Helper_DeserializeMessage<T>(grpc::DeserializationContext context, global::Google.Protobuf.MessageParser<T> parser) where T : global::Google.Protobuf.IMessage<T>
  {
    #if !GRPC_DISABLE_PROTOBUF_BUFFER_SERIALIZATION
    if (__Helper_MessageCache<T>.IsBufferMessage)
    {
      return parser.ParseFrom(context.PayloadAsReadOnlySequence());
    }
    #endif
    return parser.ParseFrom(context.PayloadAsNewBuffer());
  }

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Marshaller<global::OrderOffers> __Marshaller_OrderOffers = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::OrderOffers.Parser));
  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Marshaller<global::Void> __Marshaller_Void = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::Void.Parser));
  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Marshaller<global::Orders> __Marshaller_Orders = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::Orders.Parser));
  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Marshaller<global::Text> __Marshaller_Text = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::Text.Parser));

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Method<global::OrderOffers, global::Void> __Method_CreateOrderOffers = new grpc::Method<global::OrderOffers, global::Void>(
      grpc::MethodType.Unary,
      __ServiceName,
      "CreateOrderOffers",
      __Marshaller_OrderOffers,
      __Marshaller_Void);

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Method<global::Orders, global::Void> __Method_CreateOrders = new grpc::Method<global::Orders, global::Void>(
      grpc::MethodType.Unary,
      __ServiceName,
      "CreateOrders",
      __Marshaller_Orders,
      __Marshaller_Void);

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Method<global::Text, global::Orders> __Method_GetAllOrders = new grpc::Method<global::Text, global::Orders>(
      grpc::MethodType.Unary,
      __ServiceName,
      "GetAllOrders",
      __Marshaller_Text,
      __Marshaller_Orders);

  [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
  static readonly grpc::Method<global::Text, global::OrderOffers> __Method_GetOrderOffers = new grpc::Method<global::Text, global::OrderOffers>(
      grpc::MethodType.Unary,
      __ServiceName,
      "GetOrderOffers",
      __Marshaller_Text,
      __Marshaller_OrderOffers);

  /// <summary>Service descriptor</summary>
  public static global::Google.Protobuf.Reflection.ServiceDescriptor Descriptor
  {
    get { return global::ContractReflection.Descriptor.Services[3]; }
  }

  /// <summary>Client for OrderService</summary>
  public partial class OrderServiceClient : grpc::ClientBase<OrderServiceClient>
  {
    /// <summary>Creates a new client for OrderService</summary>
    /// <param name="channel">The channel to use to make remote calls.</param>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public OrderServiceClient(grpc::ChannelBase channel) : base(channel)
    {
    }
    /// <summary>Creates a new client for OrderService that uses a custom <c>CallInvoker</c>.</summary>
    /// <param name="callInvoker">The callInvoker to use to make remote calls.</param>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public OrderServiceClient(grpc::CallInvoker callInvoker) : base(callInvoker)
    {
    }
    /// <summary>Protected parameterless constructor to allow creation of test doubles.</summary>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    protected OrderServiceClient() : base()
    {
    }
    /// <summary>Protected constructor to allow creation of configured clients.</summary>
    /// <param name="configuration">The client configuration.</param>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    protected OrderServiceClient(ClientBaseConfiguration configuration) : base(configuration)
    {
    }

    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Void CreateOrderOffers(global::OrderOffers request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return CreateOrderOffers(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Void CreateOrderOffers(global::OrderOffers request, grpc::CallOptions options)
    {
      return CallInvoker.BlockingUnaryCall(__Method_CreateOrderOffers, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Void> CreateOrderOffersAsync(global::OrderOffers request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return CreateOrderOffersAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Void> CreateOrderOffersAsync(global::OrderOffers request, grpc::CallOptions options)
    {
      return CallInvoker.AsyncUnaryCall(__Method_CreateOrderOffers, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Void CreateOrders(global::Orders request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return CreateOrders(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Void CreateOrders(global::Orders request, grpc::CallOptions options)
    {
      return CallInvoker.BlockingUnaryCall(__Method_CreateOrders, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Void> CreateOrdersAsync(global::Orders request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return CreateOrdersAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Void> CreateOrdersAsync(global::Orders request, grpc::CallOptions options)
    {
      return CallInvoker.AsyncUnaryCall(__Method_CreateOrders, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Orders GetAllOrders(global::Text request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return GetAllOrders(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::Orders GetAllOrders(global::Text request, grpc::CallOptions options)
    {
      return CallInvoker.BlockingUnaryCall(__Method_GetAllOrders, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Orders> GetAllOrdersAsync(global::Text request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return GetAllOrdersAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::Orders> GetAllOrdersAsync(global::Text request, grpc::CallOptions options)
    {
      return CallInvoker.AsyncUnaryCall(__Method_GetAllOrders, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::OrderOffers GetOrderOffers(global::Text request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return GetOrderOffers(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual global::OrderOffers GetOrderOffers(global::Text request, grpc::CallOptions options)
    {
      return CallInvoker.BlockingUnaryCall(__Method_GetOrderOffers, null, options, request);
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::OrderOffers> GetOrderOffersAsync(global::Text request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
    {
      return GetOrderOffersAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
    }
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    public virtual grpc::AsyncUnaryCall<global::OrderOffers> GetOrderOffersAsync(global::Text request, grpc::CallOptions options)
    {
      return CallInvoker.AsyncUnaryCall(__Method_GetOrderOffers, null, options, request);
    }
    /// <summary>Creates a new instance of client from given <c>ClientBaseConfiguration</c>.</summary>
    [global::System.CodeDom.Compiler.GeneratedCode("grpc_csharp_plugin", null)]
    protected override OrderServiceClient NewInstance(ClientBaseConfiguration configuration)
    {
      return new OrderServiceClient(configuration);
    }
  }

}
#endregion
