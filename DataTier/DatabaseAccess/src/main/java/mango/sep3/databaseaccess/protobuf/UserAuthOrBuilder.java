// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: contract.proto

package mango.sep3.databaseaccess.protobuf;

public interface UserAuthOrBuilder extends
    // @@protoc_insertion_point(interface_extends:UserAuth)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string username = 1;</code>
   * @return The username.
   */
  java.lang.String getUsername();
  /**
   * <code>string username = 1;</code>
   * @return The bytes for username.
   */
  com.google.protobuf.ByteString
      getUsernameBytes();

  /**
   * <code>string hash = 2;</code>
   * @return The hash.
   */
  java.lang.String getHash();
  /**
   * <code>string hash = 2;</code>
   * @return The bytes for hash.
   */
  com.google.protobuf.ByteString
      getHashBytes();

  /**
   * <code>string salt = 3;</code>
   * @return The salt.
   */
  java.lang.String getSalt();
  /**
   * <code>string salt = 3;</code>
   * @return The bytes for salt.
   */
  com.google.protobuf.ByteString
      getSaltBytes();
}