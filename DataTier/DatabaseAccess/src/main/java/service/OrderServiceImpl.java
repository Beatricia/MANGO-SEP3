package service;

import io.grpc.stub.StreamObserver;
import mango.sep3.databaseaccess.DAOInterfaces.OrderDaoInterface;
import mango.sep3.databaseaccess.protobuf.OrderServiceGrpc;
import mango.sep3.databaseaccess.protobuf.*;
import mango.sep3.databaseaccess.protobuf.Void;
import mango.sep3.databaseaccess.utils.GrpcConverter;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


@GRpcService
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase
{
  @Autowired private OrderDaoInterface orderDao;

  @Autowired private GrpcConverter grpcConverter;

  public OrderServiceImpl()
  {
  }

  /**
   * Creates an order in the database
   */

  @Override public void createOrderOffers(OrderOffers request,
      StreamObserver<Void> responseObserver)
  {
    //convert the grpc OrderOffers into normal Collection<OrderOffers>
    List<mango.sep3.databaseaccess.Shared.OrderOffer> orderOffers = grpcConverter.convertToGrpc(request);

    orderDao.createOrderOffers(orderOffers);

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();

  }


  @Override public void completeOrder(Id request,
      StreamObserver<Void> responseObserver)
  {
    orderDao.completeOrder(request.getId());

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();

  }

  @Override public void createOrders(Orders request,
      StreamObserver<Void> responseObserver)
  {
    List<mango.sep3.databaseaccess.Shared.Order> orders = request.getOrdersList().stream()
            .map(grpcConverter::convertToShared).toList();

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

    Orders ordersResponse = grpcConverter.convertToGrpc(orders);

    responseObserver.onNext(ordersResponse);
    responseObserver.onCompleted();
  }

  @Override public void deleteOrder(Text request,
      StreamObserver<Void> responseObserver)
  {
    orderDao.deleteOrder(Integer.parseInt(request.getText()));

    responseObserver.onNext(Void.newBuilder().build());
    responseObserver.onCompleted();
  }

  @Override public void getOrderById(Id request,
      StreamObserver<Order> responseObserver)
  {
    mango.sep3.databaseaccess.Shared.Order order = orderDao.getOrderById(request.getId());
    Order response = grpcConverter.convertToGrpc(order);

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}

