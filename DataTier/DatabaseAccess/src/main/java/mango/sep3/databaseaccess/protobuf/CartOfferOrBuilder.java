// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: contract.proto

package mango.sep3.databaseaccess.protobuf;

public interface CartOfferOrBuilder extends
    // @@protoc_insertion_point(interface_extends:CartOffer)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 id = 1;</code>
   * @return The id.
   */
  int getId();

  /**
   * <code>int32 offerId = 2;</code>
   * @return The offerId.
   */
  int getOfferId();

  /**
   * <code>int32 quantity = 3;</code>
   * @return The quantity.
   */
  int getQuantity();

  /**
   * <code>string username = 4;</code>
   * @return The username.
   */
  java.lang.String getUsername();
  /**
   * <code>string username = 4;</code>
   * @return The bytes for username.
   */
  com.google.protobuf.ByteString
      getUsernameBytes();

  /**
   * <code>string collectionOption = 5;</code>
   * @return The collectionOption.
   */
  java.lang.String getCollectionOption();
  /**
   * <code>string collectionOption = 5;</code>
   * @return The bytes for collectionOption.
   */
  com.google.protobuf.ByteString
      getCollectionOptionBytes();
}