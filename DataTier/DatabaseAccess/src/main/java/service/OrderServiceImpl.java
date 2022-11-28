package service;

import com.google.common.collect.Sets;
import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.OrderDaoInterface;
import mango.sep3.databaseaccess.Shared.OrderOffer;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@GRpcService
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase
{
  @Autowired private OrderDaoInterface orderDao;

  public OrderServiceImpl()
  {
  }

  @Override public void createOrderOffers(OrderOffers request,
      StreamObserver<Void> responseObserver)
  {
    //convert the grpc OrderOffers into normal Collection<OrderOffers>
    Collection<OrderOffer> orderOffers = ConvertOrderOffersFromGrpc(request);

    orderDao.createOrderOffers(orderOffers);

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();

  }

  @Override public void getOrderOffers(Text request,
      StreamObserver<OrderOffers> responseObserver)
  {

    Collection<mango.sep3.databaseaccess.Shared.OrderOffer> orderOffers = orderDao.getOrderOffers(
        request.getText());

    if (orderOffers == null)
    {
      responseObserver.onError(new Exception("No Order Offers found"));
      return;
    }

    OrderOffers orderOffersResponse = convertOrderOffersToGrpc(orderOffers);

    responseObserver.onNext(orderOffersResponse);
    responseObserver.onCompleted();
  }

  @Override public void createOrders(Orders request,
      StreamObserver<Void> responseObserver)
  {
    List<mango.sep3.databaseaccess.Shared.Order> orders = ConvertOrdersFromGrpc(
        request.getOrdersList());

    orderDao.createOrders(orders);

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();
  }

  @Override public void getAllOrders(Text request,
      StreamObserver<Orders> responseObserver)
  {
    Collection<mango.sep3.databaseaccess.Shared.Order> orders = orderDao.getAllOrders(
        request.getText());

    if (orders == null)
    {
      responseObserver.onError(new Exception("No Orders found"));
      return;
    }

    Orders ordersResponse = convertOrdersToGrpc(orders);

    responseObserver.onNext(ordersResponse);
    responseObserver.onCompleted();
  }

  private Orders convertOrdersToGrpc(Collection<mango.sep3.databaseaccess.Shared.Order> orders)
  {
    List<Order> ordersListGrpc = new ArrayList<>();

    for (var order:orders)
    {
      OrderOffers orderOffersGrpc = convertOrderOffersToGrpc(order.getOrderOffers());
      Set<mango.sep3.databaseaccess.protobuf.OrderOffer> orderOffersToAdd  = Sets.newHashSet(orderOffersGrpc.getOrderOffersList());

      var orderGrpc = Order.newBuilder()
          .setId(order.getId())
          .setCollectionOption(order.getCollectionOption())
          .setFarmName(order.getFarmName())
          .addAllOrderOffers(orderOffersToAdd)
          .setIsDone(order.isDone())
          .build();

      ordersListGrpc.add(orderGrpc);
    }
    Orders ordersToReturn = Orders.newBuilder().addAllOrders(ordersListGrpc).build();

    return ordersToReturn;
  }

  private List<mango.sep3.databaseaccess.Shared.Order> ConvertOrdersFromGrpc(
      List<Order> ordersList)
  {
    List<mango.sep3.databaseaccess.Shared.Order> listToReturn = new ArrayList<>();

    for (var order : ordersList)
    {
      List<OrderOffer> orderOffers = convertOrderOffersFromGrpc(
          order.getOrderOffersList());
      Set<OrderOffer> orderOffersSet = Sets.newHashSet(orderOffers);

      var item = new mango.sep3.databaseaccess.Shared.Order();
      item.setId(order.getId());
      item.setCollectionOption(order.getCollectionOption());
      item.setDone(order.getIsDone());
      item.setFarmName(order.getFarmName());
      item.setOrderOffers(orderOffersSet);

      listToReturn.add(item);
    }
    return listToReturn;

  }

  private List<OrderOffer> convertOrderOffersFromGrpc(
      List<mango.sep3.databaseaccess.protobuf.OrderOffer> orderOffersList)
  {
    List<OrderOffer> orderOffers = new ArrayList<>();
    for (var orderOffer : orderOffersList)
    {
      OrderOffer item = new OrderOffer();
      item.setUsername(orderOffer.getUsername());
      item.setQuantity(orderOffer.getQuantity());
      item.setId(orderOffer.getId());
      item.setCollectionOption(orderOffer.getCollectionOption());
      item.setOffer(convertOfferFromGrpc(orderOffer.getOffer()));
      //todo might need to add setOrder here

      orderOffers.add(item);
    }
    return orderOffers;
  }

  private OrderOffers convertOrderOffersToGrpc(
      Collection<OrderOffer> orderOffers)
  {
    List<mango.sep3.databaseaccess.protobuf.OrderOffer> orderOfferList = new ArrayList<>();
    for (var item : orderOffers)
    {
      Offer offer = Offer.newBuilder().setId(item.getOffer().getId())
          .setDelivery(item.getOffer().isDelivery())
          .setPickUp(item.getOffer().isPickUp())
          .setPickYourOwn(item.getOffer().isPickUp())
          .setPrice(item.getOffer().getPrice())
          .setQuantity(item.getOffer().getQuantity())
          .setName(item.getOffer().getName())
          .setDescription(item.getOffer().getDescription())
          .setImagePath(item.getOffer().getImgPath())
          .setUnit(item.getOffer().getUnit()).build();

      orderOfferList.add(
          mango.sep3.databaseaccess.protobuf.OrderOffer.newBuilder()
              .setId(item.getId()).setOffer(offer)
              .setQuantity(item.getQuantity())
              .setCollectionOption(item.getCollectionOption()).setOffer(offer)
              .build());
    }

    return OrderOffers.newBuilder().addAllOrderOffers(orderOfferList).build();
  }

  private Collection<OrderOffer> ConvertOrderOffersFromGrpc(OrderOffers request)
  {
    List<mango.sep3.databaseaccess.protobuf.OrderOffer> orderOffersList = request.getOrderOffersList();

    List<OrderOffer> listToReturn = new ArrayList<>();
    for (var orderOffer : orderOffersList)
    {
      var item = new OrderOffer();
      item.setOffer(convertOfferFromGrpc(orderOffer.getOffer()));
      item.setQuantity(orderOffer.getQuantity());
      item.setId(orderOffer.getId());
      item.setUsername(orderOffer.getUsername());
      item.setCollectionOption(orderOffer.getCollectionOption());
      listToReturn.add(item);
    }

    return listToReturn;
  }

  private mango.sep3.databaseaccess.Shared.Offer convertOfferFromGrpc(
      Offer offer)
  {
    var item = new mango.sep3.databaseaccess.Shared.Offer();
    item.setDelivery(offer.getDelivery());
    item.setDescription(offer.getDescription());
    item.setName(offer.getName());
    item.setId(offer.getId());
    item.setQuantity(offer.getQuantity());
    item.setPickUp(offer.getPickUp());
    item.setImgPath(offer.getImagePath());
    item.setPickyourOwn(offer.getPickYourOwn());
    item.setUnit(item.getUnit());
    item.setPrice(offer.getPrice());

    return item;
  }
}

