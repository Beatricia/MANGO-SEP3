// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: contract.proto

package mango.sep3.databaseaccess.protobuf;

public interface CustomerOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Customer)
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
   * <code>string firstname = 2;</code>
   * @return The firstname.
   */
  java.lang.String getFirstname();
  /**
   * <code>string firstname = 2;</code>
   * @return The bytes for firstname.
   */
  com.google.protobuf.ByteString
      getFirstnameBytes();

  /**
   * <code>string lastname = 3;</code>
   * @return The lastname.
   */
  java.lang.String getLastname();
  /**
   * <code>string lastname = 3;</code>
   * @return The bytes for lastname.
   */
  com.google.protobuf.ByteString
      getLastnameBytes();

  /**
   * <code>string phone = 4;</code>
   * @return The phone.
   */
  java.lang.String getPhone();
  /**
   * <code>string phone = 4;</code>
   * @return The bytes for phone.
   */
  com.google.protobuf.ByteString
      getPhoneBytes();

  /**
   * <code>.Address address = 5;</code>
   * @return Whether the address field is set.
   */
  boolean hasAddress();
  /**
   * <code>.Address address = 5;</code>
   * @return The address.
   */
  mango.sep3.databaseaccess.protobuf.Address getAddress();
  /**
   * <code>.Address address = 5;</code>
   */
  mango.sep3.databaseaccess.protobuf.AddressOrBuilder getAddressOrBuilder();
}
