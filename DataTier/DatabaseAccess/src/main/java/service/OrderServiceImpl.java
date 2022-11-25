package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.OrderDaoInterface;
import mango.sep3.databaseaccess.Shared.OrderOffer;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase
{
  @Autowired private OrderDaoInterface orderDao;

  public OrderServiceImpl()
  {
  }

  @Override public void createOrderOffers(OrderOffers request,
      StreamObserver<Void> responseObserver)
  {
    super.createOrderOffers(request, responseObserver);
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

    OrderOffers orderOffersResponse = convertToGrpc(orderOffers);

    responseObserver.onNext(orderOffersResponse);
    responseObserver.onCompleted();
  }

  private OrderOffers convertToGrpc(Collection<OrderOffer> orderOffers)
  {
    List<mango.sep3.databaseaccess.protobuf.OrderOffer> orderOfferList = new ArrayList<>();
    for (var item: orderOffers)
    {
     Offer offer = Offer.newBuilder()
          .setId(item.getOffer().getId())
          .setDelivery(item.getOffer().isDelivery())
         .setPickUp(item.getOffer().isPickUp())
         .setPickYourOwn(item.getOffer().isPickUp())
         .setPrice(item.getOffer().getPrice())
         .setQuantity(item.getOffer().getQuantity())
         .setName(item.getOffer().getName())
         .setDescription(item.getOffer().getDescription())
         .setImagePath(item.getOffer().getImgPath())
         .setUnit(item.getOffer().getUnit())
      .build();

      orderOfferList.add(
          mango.sep3.databaseaccess.protobuf.OrderOffer.newBuilder()
              .setId(item.getId())
              .setOffer(offer)
              .setQuantity(item.getQuantity())
              .setCollectionOption(item.getCollectionOption())
              .setOffer(offer)
              .build()
      );
    }

    return OrderOffers.newBuilder().addAllOrderOffers(orderOfferList).build();
  }

  @Override public void createOrders(Orders request,
      StreamObserver<Void> responseObserver)
  {
    super.createOrders(request, responseObserver);
  }

  @Override public void getAllOrders(Text request,
      StreamObserver<Orders> responseObserver)
  {
    super.getAllOrders(request, responseObserver);
  }
}

