package service;

import com.google.common.collect.Sets;
import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.FarmDaoInterface;
import mango.sep3.databaseaccess.DAOInterfaces.OrderDaoInterface;
import mango.sep3.databaseaccess.protobuf.OrderServiceGrpc;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


@GRpcService
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase
{
  @Autowired private OrderDaoInterface orderDao;
  @Autowired private FarmDaoInterface farmDaoInterface;

  public OrderServiceImpl()
  {
  }

  /**
   * Creates an order in the database
   * @param request
   * @param responseObserver
   */

  @Override public void createOrderOffers(OrderOffers request,
      StreamObserver<Void> responseObserver)
  {
    //convert the grpc OrderOffers into normal Collection<OrderOffers>
    List<mango.sep3.databaseaccess.Shared.OrderOffer> orderOffers = convertOrderOffersFromGrpc(request.getOrderOffersList());

    orderDao.createOrderOffers(orderOffers);

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();

  }

  //TODO check if we actually need this method
  @Override public void getOrderOffers(Text request,
      StreamObserver<OrderOffers> responseObserver)
  {

    /*Collection<mango.sep3.databaseaccess.Shared.OrderOffer> orderOffers = orderDao.getOrderOffers(
        request.getText());

    if (orderOffers == null)
    {
      responseObserver.onError(new Exception("No Order Offers found"));
      return;
    }

    OrderOffers orderOffersResponse = convertOrderOffersToGrpc(orderOffers);

    responseObserver.onNext(orderOffersResponse);
    responseObserver.onCompleted();*/
  }

  /*@Override public void completeOrder(Id request,
      StreamObserver<Void> responseObserver)
  {
    orderDao.completeOrder(request.getId());

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();

  }*/

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

  private List<mango.sep3.databaseaccess.Shared.Order> ConvertOrdersFromGrpc(
      List<Order> ordersList)
  {
    List<mango.sep3.databaseaccess.Shared.Order> listToReturn = new ArrayList<>();

    for (var order : ordersList)
    {
      List<mango.sep3.databaseaccess.Shared.OrderOffer> orderOffers = convertOrderOffersFromGrpc(
          order.getOrderOffersList());
      Set<mango.sep3.databaseaccess.Shared.OrderOffer> orderOffersSet = Sets.newHashSet(orderOffers);

      var item = new mango.sep3.databaseaccess.Shared.Order();
      item.setId(order.getId());
      item.setCollectionOption(order.getCollectionOption());
      item.setDone(order.getIsDone());
      item.setFarmName(order.getFarmName());
      item.setOrderOffers(orderOffersSet);
      item.setUsername(order.getUsername());

      listToReturn.add(item);
    }
    return listToReturn;

  }

  private List<mango.sep3.databaseaccess.Shared.OrderOffer> convertOrderOffersFromGrpc(
      List<mango.sep3.databaseaccess.protobuf.OrderOffer> orderOffersList)
  {
    List<mango.sep3.databaseaccess.Shared.OrderOffer> orderOffers = new ArrayList<>();
    for (var orderOffer : orderOffersList)
    {
      mango.sep3.databaseaccess.Shared.OrderOffer item = new mango.sep3.databaseaccess.Shared.OrderOffer();
      item.setUsername(orderOffer.getUsername());
      item.setQuantity(orderOffer.getQuantity());
      item.setId(orderOffer.getId());
      item.setCollectionOption(orderOffer.getCollectionOption());
      item.setOffer(convertOfferFromGrpc(orderOffer.getOffer()));

      orderOffers.add(item);
    }
    return orderOffers;
  }

  private OrderOffers convertOrderOffersToGrpc(
      Collection<mango.sep3.databaseaccess.Shared.OrderOffer> orderOffers)
  {
    List<mango.sep3.databaseaccess.protobuf.OrderOffer> orderOfferList = new ArrayList<>();
    for (var item : orderOffers)
    {
      Offer offer = Offer.newBuilder().setId(item.getOffer().getId())
          .setPrice(item.getOffer().getPrice())
          .setQuantity(item.getOffer().getQuantity())
          .setName(item.getOffer().getName())
          .setDescription(item.getOffer().getDescription())
          .setUnit(item.getOffer().getUnit())
          .setFarmName(item.getOffer().getFarm().getName()).build();

      orderOfferList.add(
          mango.sep3.databaseaccess.protobuf.OrderOffer.newBuilder()
              .setId(item.getId()).setOffer(offer)
              .setQuantity(item.getQuantity())
              .setCollectionOption(item.getCollectionOption())
              .setOffer(offer)
              .build());
    }

    return OrderOffers.newBuilder().addAllOrderOffers(orderOfferList).build();
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
    item.setPickyourOwn(offer.getPickYourOwn());
    item.setUnit(item.getUnit());
    item.setPrice(offer.getPrice());

    var farm = farmDaoInterface.getFarmByName(offer.getFarmName());
    item.setFarm(farm);

    return item;
  }

  private Orders convertOrdersToGrpc(
      Collection<mango.sep3.databaseaccess.Shared.Order> orders)
  {
    List<Order> ordersListGrpc = new ArrayList<>();

    for (var order : orders)
    {
      OrderOffers orderOffersGrpc = convertOrderOffersToGrpc(
          orderDao.getOrderOffers(order));
      Set<mango.sep3.databaseaccess.protobuf.OrderOffer> orderOffersToAdd = Sets.newHashSet(
          orderOffersGrpc.getOrderOffersList());

      var orderGrpc = Order.newBuilder()
          .setId(order.getId())
          .setCollectionOption(order.getCollectionOption())
          .setFarmName(order.getFarmName()).addAllOrderOffers(orderOffersToAdd)
          .setIsDone(order.isDone())
          .setUsername(order.getOrderOffers().stream().findFirst().get().getUsername())
          .build();

      ordersListGrpc.add(orderGrpc);
    }

    return Orders.newBuilder().addAllOrders(ordersListGrpc)
        .build();
  }
}

