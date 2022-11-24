// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: contract.proto

package mango.sep3.databaseaccess.protobuf;

public final class Contract {
  private Contract() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserAuth_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserAuth_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_User_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_User_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Farm_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Farm_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Offer_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Offer_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_OrderOffer_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_OrderOffer_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_OrderOffers_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_OrderOffers_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Order_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Order_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Orders_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Orders_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_OfferItems_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_OfferItems_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Customer_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Customer_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Farmer_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Farmer_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Address_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Address_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Void_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Void_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Text_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Text_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016contract.proto\"8\n\010UserAuth\022\020\n\010username" +
      "\030\001 \001(\t\022\014\n\004hash\030\002 \001(\t\022\014\n\004salt\030\003 \001(\t\"=\n\004Us" +
      "er\022\020\n\010username\030\001 \001(\t\022\021\n\tfirstname\030\002 \001(\t\022" +
      "\020\n\010lastname\030\003 \001(\t\"\253\001\n\004Farm\022\014\n\004name\030\001 \001(\t" +
      "\022\r\n\005phone\030\002 \001(\t\022\013\n\003zip\030\003 \001(\t\022\017\n\007address\030" +
      "\004 \001(\t\022\014\n\004city\030\005 \001(\t\022\027\n\nfarmStatus\030\006 \001(\tH" +
      "\000\210\001\001\022\035\n\020deliveryDistance\030\007 \001(\005H\001\210\001\001B\r\n\013_" +
      "farmStatusB\023\n\021_deliveryDistance\"\304\001\n\005Offe" +
      "r\022\n\n\002id\030\001 \001(\005\022\014\n\004name\030\002 \001(\t\022\020\n\010quantity\030" +
      "\003 \001(\005\022\014\n\004unit\030\004 \001(\t\022\r\n\005price\030\005 \001(\001\022\020\n\010de" +
      "livery\030\006 \001(\010\022\016\n\006pickUp\030\007 \001(\010\022\023\n\013pickYour" +
      "Own\030\010 \001(\010\022\030\n\013description\030\t \001(\tH\000\210\001\001\022\021\n\tI" +
      "magePath\030\n \001(\tB\016\n\014_description\"m\n\nOrderO" +
      "ffer\022\n\n\002id\030\001 \001(\005\022\025\n\005offer\030\002 \001(\0132\006.Offer\022" +
      "\020\n\010quantity\030\003 \001(\005\022\020\n\010username\030\004 \001(\t\022\030\n\020c" +
      "ollectionOption\030\005 \001(\t\"/\n\013OrderOffers\022 \n\013" +
      "orderOffers\030\001 \003(\0132\013.OrderOffer\"q\n\005Order\022" +
      "\n\n\002id\030\001 \001(\005\022 \n\013orderOffers\030\002 \003(\0132\013.Order" +
      "Offer\022\016\n\006isDone\030\003 \001(\010\022\020\n\010farmName\030\004 \001(\t\022" +
      "\030\n\020collectionOption\030\005 \001(\t\" \n\006Orders\022\026\n\006o" +
      "rders\030\001 \003(\0132\006.Order\"$\n\nOfferItems\022\026\n\006off" +
      "ers\030\001 \003(\0132\006.Offer\"k\n\010Customer\022\020\n\010usernam" +
      "e\030\001 \001(\t\022\021\n\tfirstname\030\002 \001(\t\022\020\n\010lastname\030\003" +
      " \001(\t\022\r\n\005phone\030\004 \001(\t\022\031\n\007address\030\005 \001(\0132\010.A" +
      "ddress\"?\n\006Farmer\022\020\n\010username\030\001 \001(\t\022\021\n\tfi" +
      "rstname\030\002 \001(\t\022\020\n\010lastname\030\003 \001(\t\"4\n\007Addre" +
      "ss\022\014\n\004city\030\001 \001(\t\022\016\n\006street\030\002 \001(\t\022\013\n\003zip\030" +
      "\003 \001(\t\"\006\n\004Void\"\024\n\004Text\022\014\n\004text\030\001 \001(\t2)\n\013F" +
      "armService\022\032\n\nCreateFarm\022\005.Farm\032\005.Farm2N" +
      "\n\014OfferService\022\035\n\013CreateOffer\022\006.Offer\032\006." +
      "Offer\022\037\n\tGetOffers\022\005.Void\032\013.OfferItems2s" +
      "\n\013UserService\022 \n\014RegisterUser\022\t.UserAuth" +
      "\032\005.User\022!\n\021GetUserByUsername\022\005.Text\032\005.Us" +
      "er\022\037\n\013GetCustomer\022\005.Text\032\t.Customer2\237\001\n\014" +
      "OrderService\022(\n\021CreateOrderOffers\022\014.Orde" +
      "rOffers\032\005.Void\022\036\n\014CreateOrders\022\007.Orders\032" +
      "\005.Void\022\036\n\014GetAllOrders\022\005.Text\032\007.Orders\022%" +
      "\n\016GetOrderOffers\022\005.Text\032\014.OrderOffersB&\n" +
      "\"mango.sep3.databaseaccess.protobufP\001b\006p" +
      "roto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_UserAuth_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_UserAuth_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserAuth_descriptor,
        new java.lang.String[] { "Username", "Hash", "Salt", });
    internal_static_User_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_User_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_User_descriptor,
        new java.lang.String[] { "Username", "Firstname", "Lastname", });
    internal_static_Farm_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_Farm_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Farm_descriptor,
        new java.lang.String[] { "Name", "Phone", "Zip", "Address", "City", "FarmStatus", "DeliveryDistance", "FarmStatus", "DeliveryDistance", });
    internal_static_Offer_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_Offer_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Offer_descriptor,
        new java.lang.String[] { "Id", "Name", "Quantity", "Unit", "Price", "Delivery", "PickUp", "PickYourOwn", "Description", "ImagePath", "Description", });
    internal_static_OrderOffer_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_OrderOffer_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_OrderOffer_descriptor,
        new java.lang.String[] { "Id", "Offer", "Quantity", "Username", "CollectionOption", });
    internal_static_OrderOffers_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_OrderOffers_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_OrderOffers_descriptor,
        new java.lang.String[] { "OrderOffers", });
    internal_static_Order_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_Order_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Order_descriptor,
        new java.lang.String[] { "Id", "OrderOffers", "IsDone", "FarmName", "CollectionOption", });
    internal_static_Orders_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_Orders_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Orders_descriptor,
        new java.lang.String[] { "Orders", });
    internal_static_OfferItems_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_OfferItems_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_OfferItems_descriptor,
        new java.lang.String[] { "Offers", });
    internal_static_Customer_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_Customer_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Customer_descriptor,
        new java.lang.String[] { "Username", "Firstname", "Lastname", "Phone", "Address", });
    internal_static_Farmer_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_Farmer_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Farmer_descriptor,
        new java.lang.String[] { "Username", "Firstname", "Lastname", });
    internal_static_Address_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_Address_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Address_descriptor,
        new java.lang.String[] { "City", "Street", "Zip", });
    internal_static_Void_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_Void_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Void_descriptor,
        new java.lang.String[] { });
    internal_static_Text_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_Text_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Text_descriptor,
        new java.lang.String[] { "Text", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
