// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: contract.proto

package mango.sep3.databaseaccess.protobuf;

/**
 * Protobuf type {@code CartOffers}
 */
public final class CartOffers extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:CartOffers)
    CartOffersOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CartOffers.newBuilder() to construct.
  private CartOffers(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CartOffers() {
    cartOffers_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new CartOffers();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private CartOffers(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              cartOffers_ = new java.util.ArrayList<mango.sep3.databaseaccess.protobuf.CartOffer>();
              mutable_bitField0_ |= 0x00000001;
            }
            cartOffers_.add(
                input.readMessage(mango.sep3.databaseaccess.protobuf.CartOffer.parser(), extensionRegistry));
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (com.google.protobuf.UninitializedMessageException e) {
      throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        cartOffers_ = java.util.Collections.unmodifiableList(cartOffers_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return mango.sep3.databaseaccess.protobuf.Contract.internal_static_CartOffers_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return mango.sep3.databaseaccess.protobuf.Contract.internal_static_CartOffers_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            mango.sep3.databaseaccess.protobuf.CartOffers.class, mango.sep3.databaseaccess.protobuf.CartOffers.Builder.class);
  }

  public static final int CARTOFFERS_FIELD_NUMBER = 1;
  private java.util.List<mango.sep3.databaseaccess.protobuf.CartOffer> cartOffers_;
  /**
   * <code>repeated .CartOffer cartOffers = 1;</code>
   */
  @java.lang.Override
  public java.util.List<mango.sep3.databaseaccess.protobuf.CartOffer> getCartOffersList() {
    return cartOffers_;
  }
  /**
   * <code>repeated .CartOffer cartOffers = 1;</code>
   */
  @java.lang.Override
  public java.util.List<? extends mango.sep3.databaseaccess.protobuf.CartOfferOrBuilder> 
      getCartOffersOrBuilderList() {
    return cartOffers_;
  }
  /**
   * <code>repeated .CartOffer cartOffers = 1;</code>
   */
  @java.lang.Override
  public int getCartOffersCount() {
    return cartOffers_.size();
  }
  /**
   * <code>repeated .CartOffer cartOffers = 1;</code>
   */
  @java.lang.Override
  public mango.sep3.databaseaccess.protobuf.CartOffer getCartOffers(int index) {
    return cartOffers_.get(index);
  }
  /**
   * <code>repeated .CartOffer cartOffers = 1;</code>
   */
  @java.lang.Override
  public mango.sep3.databaseaccess.protobuf.CartOfferOrBuilder getCartOffersOrBuilder(
      int index) {
    return cartOffers_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    for (int i = 0; i < cartOffers_.size(); i++) {
      output.writeMessage(1, cartOffers_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < cartOffers_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, cartOffers_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof mango.sep3.databaseaccess.protobuf.CartOffers)) {
      return super.equals(obj);
    }
    mango.sep3.databaseaccess.protobuf.CartOffers other = (mango.sep3.databaseaccess.protobuf.CartOffers) obj;

    if (!getCartOffersList()
        .equals(other.getCartOffersList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getCartOffersCount() > 0) {
      hash = (37 * hash) + CARTOFFERS_FIELD_NUMBER;
      hash = (53 * hash) + getCartOffersList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static mango.sep3.databaseaccess.protobuf.CartOffers parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static mango.sep3.databaseaccess.protobuf.CartOffers parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static mango.sep3.databaseaccess.protobuf.CartOffers parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static mango.sep3.databaseaccess.protobuf.CartOffers parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static mango.sep3.databaseaccess.protobuf.CartOffers parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static mango.sep3.databaseaccess.protobuf.CartOffers parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static mango.sep3.databaseaccess.protobuf.CartOffers parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static mango.sep3.databaseaccess.protobuf.CartOffers parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static mango.sep3.databaseaccess.protobuf.CartOffers parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static mango.sep3.databaseaccess.protobuf.CartOffers parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static mango.sep3.databaseaccess.protobuf.CartOffers parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static mango.sep3.databaseaccess.protobuf.CartOffers parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(mango.sep3.databaseaccess.protobuf.CartOffers prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code CartOffers}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:CartOffers)
      mango.sep3.databaseaccess.protobuf.CartOffersOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return mango.sep3.databaseaccess.protobuf.Contract.internal_static_CartOffers_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return mango.sep3.databaseaccess.protobuf.Contract.internal_static_CartOffers_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              mango.sep3.databaseaccess.protobuf.CartOffers.class, mango.sep3.databaseaccess.protobuf.CartOffers.Builder.class);
    }

    // Construct using mango.sep3.databaseaccess.protobuf.CartOffers.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getCartOffersFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      if (cartOffersBuilder_ == null) {
        cartOffers_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        cartOffersBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return mango.sep3.databaseaccess.protobuf.Contract.internal_static_CartOffers_descriptor;
    }

    @java.lang.Override
    public mango.sep3.databaseaccess.protobuf.CartOffers getDefaultInstanceForType() {
      return mango.sep3.databaseaccess.protobuf.CartOffers.getDefaultInstance();
    }

    @java.lang.Override
    public mango.sep3.databaseaccess.protobuf.CartOffers build() {
      mango.sep3.databaseaccess.protobuf.CartOffers result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public mango.sep3.databaseaccess.protobuf.CartOffers buildPartial() {
      mango.sep3.databaseaccess.protobuf.CartOffers result = new mango.sep3.databaseaccess.protobuf.CartOffers(this);
      int from_bitField0_ = bitField0_;
      if (cartOffersBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          cartOffers_ = java.util.Collections.unmodifiableList(cartOffers_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.cartOffers_ = cartOffers_;
      } else {
        result.cartOffers_ = cartOffersBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof mango.sep3.databaseaccess.protobuf.CartOffers) {
        return mergeFrom((mango.sep3.databaseaccess.protobuf.CartOffers)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(mango.sep3.databaseaccess.protobuf.CartOffers other) {
      if (other == mango.sep3.databaseaccess.protobuf.CartOffers.getDefaultInstance()) return this;
      if (cartOffersBuilder_ == null) {
        if (!other.cartOffers_.isEmpty()) {
          if (cartOffers_.isEmpty()) {
            cartOffers_ = other.cartOffers_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureCartOffersIsMutable();
            cartOffers_.addAll(other.cartOffers_);
          }
          onChanged();
        }
      } else {
        if (!other.cartOffers_.isEmpty()) {
          if (cartOffersBuilder_.isEmpty()) {
            cartOffersBuilder_.dispose();
            cartOffersBuilder_ = null;
            cartOffers_ = other.cartOffers_;
            bitField0_ = (bitField0_ & ~0x00000001);
            cartOffersBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getCartOffersFieldBuilder() : null;
          } else {
            cartOffersBuilder_.addAllMessages(other.cartOffers_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      mango.sep3.databaseaccess.protobuf.CartOffers parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (mango.sep3.databaseaccess.protobuf.CartOffers) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.util.List<mango.sep3.databaseaccess.protobuf.CartOffer> cartOffers_ =
      java.util.Collections.emptyList();
    private void ensureCartOffersIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        cartOffers_ = new java.util.ArrayList<mango.sep3.databaseaccess.protobuf.CartOffer>(cartOffers_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        mango.sep3.databaseaccess.protobuf.CartOffer, mango.sep3.databaseaccess.protobuf.CartOffer.Builder, mango.sep3.databaseaccess.protobuf.CartOfferOrBuilder> cartOffersBuilder_;

    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public java.util.List<mango.sep3.databaseaccess.protobuf.CartOffer> getCartOffersList() {
      if (cartOffersBuilder_ == null) {
        return java.util.Collections.unmodifiableList(cartOffers_);
      } else {
        return cartOffersBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public int getCartOffersCount() {
      if (cartOffersBuilder_ == null) {
        return cartOffers_.size();
      } else {
        return cartOffersBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public mango.sep3.databaseaccess.protobuf.CartOffer getCartOffers(int index) {
      if (cartOffersBuilder_ == null) {
        return cartOffers_.get(index);
      } else {
        return cartOffersBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public Builder setCartOffers(
        int index, mango.sep3.databaseaccess.protobuf.CartOffer value) {
      if (cartOffersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCartOffersIsMutable();
        cartOffers_.set(index, value);
        onChanged();
      } else {
        cartOffersBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public Builder setCartOffers(
        int index, mango.sep3.databaseaccess.protobuf.CartOffer.Builder builderForValue) {
      if (cartOffersBuilder_ == null) {
        ensureCartOffersIsMutable();
        cartOffers_.set(index, builderForValue.build());
        onChanged();
      } else {
        cartOffersBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public Builder addCartOffers(mango.sep3.databaseaccess.protobuf.CartOffer value) {
      if (cartOffersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCartOffersIsMutable();
        cartOffers_.add(value);
        onChanged();
      } else {
        cartOffersBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public Builder addCartOffers(
        int index, mango.sep3.databaseaccess.protobuf.CartOffer value) {
      if (cartOffersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCartOffersIsMutable();
        cartOffers_.add(index, value);
        onChanged();
      } else {
        cartOffersBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public Builder addCartOffers(
        mango.sep3.databaseaccess.protobuf.CartOffer.Builder builderForValue) {
      if (cartOffersBuilder_ == null) {
        ensureCartOffersIsMutable();
        cartOffers_.add(builderForValue.build());
        onChanged();
      } else {
        cartOffersBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public Builder addCartOffers(
        int index, mango.sep3.databaseaccess.protobuf.CartOffer.Builder builderForValue) {
      if (cartOffersBuilder_ == null) {
        ensureCartOffersIsMutable();
        cartOffers_.add(index, builderForValue.build());
        onChanged();
      } else {
        cartOffersBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public Builder addAllCartOffers(
        java.lang.Iterable<? extends mango.sep3.databaseaccess.protobuf.CartOffer> values) {
      if (cartOffersBuilder_ == null) {
        ensureCartOffersIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, cartOffers_);
        onChanged();
      } else {
        cartOffersBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public Builder clearCartOffers() {
      if (cartOffersBuilder_ == null) {
        cartOffers_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        cartOffersBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public Builder removeCartOffers(int index) {
      if (cartOffersBuilder_ == null) {
        ensureCartOffersIsMutable();
        cartOffers_.remove(index);
        onChanged();
      } else {
        cartOffersBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public mango.sep3.databaseaccess.protobuf.CartOffer.Builder getCartOffersBuilder(
        int index) {
      return getCartOffersFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public mango.sep3.databaseaccess.protobuf.CartOfferOrBuilder getCartOffersOrBuilder(
        int index) {
      if (cartOffersBuilder_ == null) {
        return cartOffers_.get(index);  } else {
        return cartOffersBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public java.util.List<? extends mango.sep3.databaseaccess.protobuf.CartOfferOrBuilder> 
         getCartOffersOrBuilderList() {
      if (cartOffersBuilder_ != null) {
        return cartOffersBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(cartOffers_);
      }
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public mango.sep3.databaseaccess.protobuf.CartOffer.Builder addCartOffersBuilder() {
      return getCartOffersFieldBuilder().addBuilder(
          mango.sep3.databaseaccess.protobuf.CartOffer.getDefaultInstance());
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public mango.sep3.databaseaccess.protobuf.CartOffer.Builder addCartOffersBuilder(
        int index) {
      return getCartOffersFieldBuilder().addBuilder(
          index, mango.sep3.databaseaccess.protobuf.CartOffer.getDefaultInstance());
    }
    /**
     * <code>repeated .CartOffer cartOffers = 1;</code>
     */
    public java.util.List<mango.sep3.databaseaccess.protobuf.CartOffer.Builder> 
         getCartOffersBuilderList() {
      return getCartOffersFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        mango.sep3.databaseaccess.protobuf.CartOffer, mango.sep3.databaseaccess.protobuf.CartOffer.Builder, mango.sep3.databaseaccess.protobuf.CartOfferOrBuilder> 
        getCartOffersFieldBuilder() {
      if (cartOffersBuilder_ == null) {
        cartOffersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            mango.sep3.databaseaccess.protobuf.CartOffer, mango.sep3.databaseaccess.protobuf.CartOffer.Builder, mango.sep3.databaseaccess.protobuf.CartOfferOrBuilder>(
                cartOffers_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        cartOffers_ = null;
      }
      return cartOffersBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:CartOffers)
  }

  // @@protoc_insertion_point(class_scope:CartOffers)
  private static final mango.sep3.databaseaccess.protobuf.CartOffers DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new mango.sep3.databaseaccess.protobuf.CartOffers();
  }

  public static mango.sep3.databaseaccess.protobuf.CartOffers getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CartOffers>
      PARSER = new com.google.protobuf.AbstractParser<CartOffers>() {
    @java.lang.Override
    public CartOffers parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new CartOffers(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<CartOffers> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CartOffers> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public mango.sep3.databaseaccess.protobuf.CartOffers getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
