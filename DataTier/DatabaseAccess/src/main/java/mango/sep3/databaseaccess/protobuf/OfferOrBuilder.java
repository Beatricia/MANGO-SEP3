// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: contract.proto

package mango.sep3.databaseaccess.protobuf;

public interface OfferOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Offer)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string title = 1;</code>
   * @return The title.
   */
  java.lang.String getTitle();
  /**
   * <code>string title = 1;</code>
   * @return The bytes for title.
   */
  com.google.protobuf.ByteString
      getTitleBytes();

  /**
   * <code>optional int32 offerId = 2;</code>
   * @return Whether the offerId field is set.
   */
  boolean hasOfferId();
  /**
   * <code>optional int32 offerId = 2;</code>
   * @return The offerId.
   */
  int getOfferId();

  /**
   * <code>string price = 3;</code>
   * @return The price.
   */
  java.lang.String getPrice();
  /**
   * <code>string price = 3;</code>
   * @return The bytes for price.
   */
  com.google.protobuf.ByteString
      getPriceBytes();

  /**
   * <code>string description = 4;</code>
   * @return The description.
   */
  java.lang.String getDescription();
  /**
   * <code>string description = 4;</code>
   * @return The bytes for description.
   */
  com.google.protobuf.ByteString
      getDescriptionBytes();

  /**
   * <code>string photoPath = 5;</code>
   * @return The photoPath.
   */
  java.lang.String getPhotoPath();
  /**
   * <code>string photoPath = 5;</code>
   * @return The bytes for photoPath.
   */
  com.google.protobuf.ByteString
      getPhotoPathBytes();
}
