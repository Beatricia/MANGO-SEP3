package mango.sep3.databaseaccess.utils;

import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.DAOInterfaces.UserDaoInterface;
import mango.sep3.databaseaccess.Shared.Address;
import mango.sep3.databaseaccess.Shared.NotificationCustomer;
import mango.sep3.databaseaccess.Shared.NotificationFarmer;
import mango.sep3.databaseaccess.protobuf.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GrpcConverter {


    @Autowired
    private UserDaoInterface userDaoInterface;

    @Autowired
    private FarmDaoInterface farmDaoInterface;

    public GrpcConverter() {
    }


    // region Text
    public Text convertToGrpc(String text) {
        return Text.newBuilder().setText(text).build();
    }

    // endregion

    // region RepeatedUsername
    public RepeatedUsername convertToGrpcRU(Collection<String> usernames) {
        RepeatedUsername.Builder repeatedUsernameBuilder = RepeatedUsername.newBuilder();
        for (String username : usernames) {
            repeatedUsernameBuilder.addUsername(convertToGrpc(username));
        }
        return repeatedUsernameBuilder.build();
    }

    // endregion

    // region Farm

    public Farm convertToGrpc(mango.sep3.databaseaccess.Shared.Farm farm)
    {
        return Farm.newBuilder()
                .setName(farm.getName())
                .setFarmStatus(farm.getDescription())
                .setIconPath(farm.getIconPath())
                .setDeliveryDistance(farm.getDeliveryDistance())
                .setPhone(farm.getPhone())
                .setIconPath(farm.getIconPath())
                .setFarmer(convertToGrpc(farm.getFarmer()))
                .setAddress(convertToGrpc(farm.getAddress()))


                .build();
    }

    public mango.sep3.databaseaccess.Shared.Farm convertToShared(Farm farm)
    {
        var farm1 = new mango.sep3.databaseaccess.Shared.Farm();
        farm1.setName(farm.getName());
        farm1.setDescription(farm.getFarmStatus());
        farm1.setIconPath(farm.getIconPath());
        farm1.setDeliveryDistance(farm.getDeliveryDistance());
        farm1.setPhone(farm.getPhone());
        farm1.setIconPath(farm.getIconPath());
        farm1.setFarmer(convertToShared(farm.getFarmer()));
        farm1.setAddress(convertToShared(farm.getAddress()));

        return farm1;
    }

    // endregion

    // region Farmer
    public Farmer convertToGrpc(mango.sep3.databaseaccess.Shared.Farmer farmer)
    {
        return Farmer.newBuilder()
                .setFirstname(farmer.getFirstName())
                .setLastname(farmer.getLastName())
                .setUsername(farmer.getUsername())
                .build();
    }

    public mango.sep3.databaseaccess.Shared.Farmer convertToShared(Farmer farmer)
    {
        var farmer1 = new mango.sep3.databaseaccess.Shared.Farmer();
        farmer1.setFirstName(farmer.getFirstname());
        farmer1.setLastName(farmer.getLastname());
        farmer1.setUsername(farmer.getUsername());

        return farmer1;
    }

    // endregion

    // region Customer

    public Customer convertToGrpc(mango.sep3.databaseaccess.Shared.Customer customer)
    {
        return Customer.newBuilder()
                .setFirstname(customer.getFirstName())
                .setLastname(customer.getLastName())
                .setUsername(customer.getUsername())
                .setPhone(customer.getPhone())
                .setAddress(convertToGrpc(customer.getAddress()))
                .build();
    }

    public mango.sep3.databaseaccess.Shared.Customer convertToShared(Customer customer)
    {
        var customer1 = new mango.sep3.databaseaccess.Shared.Customer();
        customer1.setFirstName(customer.getFirstname());
        customer1.setLastName(customer.getLastname());
        customer1.setUsername(customer.getUsername());
        customer1.setPhone(customer.getPhone());
        customer1.setAddress(convertToShared(customer.getAddress()));

        return customer1;
    }

    // endregion

    // region UserAuth

    public UserAuth convertToGrpc(mango.sep3.databaseaccess.Shared.UserAuth userAuth)
    {
        return UserAuth.newBuilder()
                .setUsername(userAuth.getUsername())
                .setHash(userAuth.getHash())
                .setSalt(userAuth.getSalt())
                .build();
    }

    public mango.sep3.databaseaccess.Shared.UserAuth convertToShared(UserAuth userAuth)
    {
        var userAuth1 = new mango.sep3.databaseaccess.Shared.UserAuth();
        userAuth1.setUsername(userAuth.getUsername());
        userAuth1.setHash(userAuth.getHash());
        userAuth1.setSalt(userAuth.getSalt());

        return userAuth1;
    }

    // endregion

    // region Address
    public mango.sep3.databaseaccess.protobuf.Address convertToGrpc(
            mango.sep3.databaseaccess.Shared.Address address)
    {
        return mango.sep3.databaseaccess.protobuf.Address.newBuilder()
                .setCity(address.getCity())
                .setZip(address.getZip())
                .setStreet(address.getStreet())
                .setLatitude(address.getLatitude())
                .setLongitude(address.getLongitude())
                .build();
    }

    public mango.sep3.databaseaccess.Shared.Address convertToShared(
            mango.sep3.databaseaccess.protobuf.Address address)
    {
        var address1 = new Address();
        address1.setCity(address.getCity());
        address1.setZip(address.getZip());
        address1.setStreet(address.getStreet());
        address1.setLatitude(address.getLatitude());
        address1.setLongitude(address.getLongitude());

        return address1;
    }

    // endregion

    // region CartOffer
    public mango.sep3.databaseaccess.protobuf.CartOffer convertToGrpc(
            mango.sep3.databaseaccess.Shared.CartItem cartOffer)
    {
        return CartOffer.newBuilder()
                .setId(cartOffer.getCartItemId())
                .setOffer(convertToGrpc(cartOffer.getOfferId()))
                .setQuantity(cartOffer.getQuantity())
                .setCollectionOption(cartOffer.getCollectionOption())
                .setUsername(cartOffer.getCustomer().getUsername())
                .build();
    }

    public mango.sep3.databaseaccess.Shared.CartItem convertToShared(
            mango.sep3.databaseaccess.protobuf.CartOffer cartOffer)
    {
        var cartOffer1 = new mango.sep3.databaseaccess.Shared.CartItem();
        cartOffer1.setCartItemId(cartOffer.getId());
        cartOffer1.setOfferId(convertToShared(cartOffer.getOffer()));
        cartOffer1.setQuantity(cartOffer.getQuantity());
        cartOffer1.setCollectionOption(cartOffer.getCollectionOption());

        var customer = userDaoInterface.getCustomer(cartOffer.getUsername());
        cartOffer1.setCustomer(customer);

        return cartOffer1;
    }

    // endregion

    // region Offer

    public mango.sep3.databaseaccess.protobuf.Offer convertToGrpc(
            mango.sep3.databaseaccess.Shared.Offer offer)
    {
        return Offer.newBuilder()
                .setId(offer.getId())
                .setName(offer.getName())
                .setQuantity(offer.getQuantity())
                .setUnit(offer.getUnit())
                .setPrice(offer.getPrice())
                .setDelivery(offer.isDelivery())
                .setPickUp(offer.isPickUp())
                .setPickYourOwn(offer.isPickyourOwn())
                .setDescription(offer.getDescription())
                .setFarmName(offer.getFarm().getName())
                .build();
    }

    public mango.sep3.databaseaccess.Shared.Offer convertToShared(
            mango.sep3.databaseaccess.protobuf.Offer offer)
    {
        mango.sep3.databaseaccess.Shared.Offer offer1 = new mango.sep3.databaseaccess.Shared.Offer();
        offer1.setId(offer.getId());
        offer1.setName(offer.getName());
        offer1.setQuantity(offer.getQuantity());
        offer1.setDescription(offer.getDescription());
        offer1.setDelivery(offer.getDelivery());
        offer1.setPickUp(offer.getPickUp());
        offer1.setPickyourOwn(offer.getPickYourOwn());
        offer1.setPrice(offer.getPrice());
        offer1.setUnit(offer.getUnit());

        var farm = farmDaoInterface.getFarmByName(offer.getFarmName());
        offer1.setFarm(farm);

        return offer1;
    }

    // endregion

    // region OrderOffer

    public mango.sep3.databaseaccess.protobuf.OrderOffer convertToGrpc(
            mango.sep3.databaseaccess.Shared.OrderOffer orderOffer)
    {
        return OrderOffer.newBuilder()
                .setId(orderOffer.getId())
                .setOffer(convertToGrpc(orderOffer.getOffer()))
                .setQuantity(orderOffer.getQuantity())
                .setCollectionOption(orderOffer.getCollectionOption())
                .setUsername(orderOffer.getUsername())
                .build();
    }

    public mango.sep3.databaseaccess.Shared.OrderOffer convertToShared(
            mango.sep3.databaseaccess.protobuf.OrderOffer orderOffer)
    {
        var orderOffer1 = new mango.sep3.databaseaccess.Shared.OrderOffer();

        orderOffer1.setId(orderOffer.getId());
        orderOffer1.setUsername(orderOffer.getUsername());
        orderOffer1.setQuantity(orderOffer.getQuantity());
        orderOffer1.setId(orderOffer.getId());
        orderOffer1.setCollectionOption(orderOffer.getCollectionOption());
        orderOffer1.setOffer(convertToShared(orderOffer.getOffer()));

        return orderOffer1;
    }

    // endregion

    // region OrderOffers

    public List<mango.sep3.databaseaccess.Shared.OrderOffer> convertToGrpc(
            mango.sep3.databaseaccess.protobuf.OrderOffers orderOffers)
    {
        return orderOffers.getOrderOffersList().stream().map(this::convertToShared).toList();
    }


    // endregion

    // region Order

    public mango.sep3.databaseaccess.protobuf.Order convertToGrpc(
            mango.sep3.databaseaccess.Shared.Order order)
    {
        var orderOffers = order.getOrderOffers().stream().map(this::convertToGrpc).toList();

        return Order.newBuilder()
                .setId(order.getId())
                .setCollectionOption(order.getCollectionOption())
                .setFarmName(order.getFarmName())
                .setIsDone(order.isDone())
                .setUsername(order.getOrderOffers().stream().findFirst().get().getUsername())
                .addAllOrderOffers(orderOffers)
                .build();
    }

    public mango.sep3.databaseaccess.Shared.Order convertToShared(
            mango.sep3.databaseaccess.protobuf.Order order)
    {
        var item = new mango.sep3.databaseaccess.Shared.Order();
        item.setId(order.getId());
        item.setCollectionOption(order.getCollectionOption());
        item.setDone(order.getIsDone());
        item.setFarmName(order.getFarmName());
        item.setUsername(order.getUsername());

        var orderOffers = order.getOrderOffersList().stream().map(this::convertToShared).collect(Collectors.toSet());
        item.setOrderOffers(orderOffers);

        return item;
    }

    // endregion

    //region Orders

    public mango.sep3.databaseaccess.protobuf.Orders convertToGrpc(
            Collection<mango.sep3.databaseaccess.Shared.Order> orders)
    {
        var orderList = orders.stream().map(this::convertToGrpc).toList();

        return Orders.newBuilder()
                .addAllOrders(orderList)
                .build();
    }

    //endregion

    // region Notification

    public NotificationFarmer convertToSharedNF(Notification notification) {
        var farmer = userDaoInterface.getFarmer(notification.getToUsername());
        if(farmer == null) {
            return null;
        }

        var not = new NotificationFarmer();
        not.setId(notification.getId());
        not.setMessage(notification.getText());
        not.setFarmer(farmer);
        not.setCreatedAt(notification.getCreatedAt());

        return not;
    }

    public NotificationCustomer convertToSharedNC(Notification notification) {
        var customer = userDaoInterface.getCustomer(notification.getToUsername());
        if(customer == null) {
            return null;
        }

        var not = new NotificationCustomer();
        not.setId(notification.getId());
        not.setMessage(notification.getText());
        not.setCustomer(customer);
        not.setCreatedAt(notification.getCreatedAt());

        return not;
    }

    public Notification convertToGrpc(NotificationFarmer notification) {
        return Notification.newBuilder()
                .setId(notification.getId())
                .setToUsername(notification.getFarmer().getUsername())
                .setText(notification.getMessage())
                .setCreatedAt(notification.getCreatedAt())
                .build();
    }

    public Notification convertToGrpc(NotificationCustomer notification) {
        return Notification.newBuilder()
                .setId(notification.getId())
                .setToUsername(notification.getCustomer().getUsername())
                .setText(notification.getMessage())
                .setCreatedAt(notification.getCreatedAt())
                .build();
    }

    // endregion
}
